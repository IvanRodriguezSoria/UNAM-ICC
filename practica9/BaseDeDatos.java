import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta para bases de datos genÃ©ricas. Provee mÃ©todos para agregar y
 * eliminar registros, y para guardarse y cargarse de una entrada y salida
 * dados.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el mÃ©todo {@link
 * #creaRegistro}, que crea un registro genÃ©rico en blanco. TambiÃ©n deben
 * implementar el mÃ©todo {@link #buscaRegistros} para hacer consultas en la base
 * de datos.
 */
public abstract class BaseDeDatos<T extends Registro> {

    /** Lista de registros en la base de datos. */
    protected Lista<T> registros;
    /** Lista de escuchas de la base de datos. */
    protected Lista<EscuchaBaseDeDatos<T>> escuchas;

    /**
     * Constructor Ãºnico.
     */
    public BaseDeDatos() {
      registros = new Lista<T>();
      escuchas = new Lista<EscuchaBaseDeDatos<T>>();
    }

    /**
     * Regresa el nÃºmero de registros en la base de datos.
     * @return el nÃºmero de registros en la base de datos.
     */
    public int getNumRegistros() {
      return registros.getLongitud();
    }

    /**
     * Regresa una lista con los registros en la base de datos. Modificar esta
     * lista no cambia a la informaciÃ³n en la base de datos.
     * @return una lista con los registros en la base de datos.
     */
    public Lista<T> getRegistros() {
      return registros.copia();
    }

    /**
     * Agrega el registro recibido a la base de datos.
     * @param registro el registro que hay que agregar a la base de datos.
     */
    public void agregaRegistro(T registro) {
      registros.agregaFinal(registro);
      for(EscuchaBaseDeDatos<T> escucha : escuchas)
        escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_AGREGADO, registro);
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(T registro) {
      registros.elimina(registro);
      for(EscuchaBaseDeDatos<T> escucha : escuchas)
        escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_ELIMINADO, registro);
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
      for(T registro : registros)
        registro.guarda(out);
    }

    /**
     * Guarda los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el mÃ©todo habÃ­a registros en la base de datos, estos son
     * eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
      registros.limpia();
      for(EscuchaBaseDeDatos<T> escucha : escuchas)
        escucha.baseDeDatosModificada(EventoBaseDeDatos.BASE_LIMPIADA, null);
      while(true){
        T r = creaRegistro();
        if(!r.carga(in))
          break;
        registros.agregaFinal(r);
        for(EscuchaBaseDeDatos<T> escucha : escuchas)
          escucha.baseDeDatosModificada(EventoBaseDeDatos.REGISTRO_AGREGADO, r);
      }
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     */
    protected abstract T creaRegistro();

    /**
     * Busca registros por un campo especÃ­fico.
     * @param campo el campo del registro por el cuÃ¡l buscar.
     * @param texto el texto a buscar.
     * @return una lista con los registros tales que en el campo especificado
     *         contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es vÃ¡lido.
     */
    public abstract Lista<T> buscaRegistros(String campo, String texto);

    /**
     * Limpia la base de datos.
     */
    public void limpia() {
      registros.limpia();
      for(EscuchaBaseDeDatos<T> escucha : escuchas)
        escucha.baseDeDatosModificada(EventoBaseDeDatos.BASE_LIMPIADA, null);
    }

    /**
     * Agrega un escucha a la base de datos.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaBaseDeDatos<T> escucha) {
      escuchas.agregaFinal(escucha);
    }
}

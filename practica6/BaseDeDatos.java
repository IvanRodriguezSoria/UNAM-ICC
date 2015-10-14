import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta para bases de datos genéricas. Provee métodos para agregar y
 * eliminar registros, y para guardarse y cargarse de una entrada y salida
 * dados.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el método {@link
 * #creaRegistro}, que crea un registro genérico en blanco. También deben
 * implementar el método {@link #buscaRegistros} para hacer consultas en la base
 * de datos.
 */
public abstract class BaseDeDatos<T extends Registro> {

    /** Lista de registros en la base de datos. */
    protected Lista<T> registros;

    /**
     * Constructor único.
     */
    public BaseDeDatos() {
      registros = new Lista<>();
    }

    /**
     * Regresa el número de registros en la base de datos.
     * @return el número de registros en la base de datos.
     */
    public int getNumRegistros() {
      int contador = 0;
      registros.primero();
      while(registros.iteradorValido()){
        contador++;
        registros.siguiente();
      }
      return contador;
    }

    /**
     * Regresa una lista con los registros en la base de datos. Modificar esta
     * lista no cambia a la información en la base de datos.
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
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(T registro) {
      registros.elimina(registro);
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
      registros.primero();
      while(registros.iteradorValido()){
        Registro r = registros.get();
        r.guarda(out);
        registros.siguiente();
      }
    }

    /**
     * Guarda los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el método había registros en la base de datos, estos son
     * eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
      registros.limpia();
      while(true){
        T r = creaRegistro();
        if(!r.carga(in))
          break;
        registros.agregaFinal(r);
      }
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     */
    protected abstract T creaRegistro();

    /**
     * Busca registros por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los registros tales que en el campo especificado
     *         contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es válido.
     */
    public abstract Lista<T> buscaRegistros(String campo, String texto);
}

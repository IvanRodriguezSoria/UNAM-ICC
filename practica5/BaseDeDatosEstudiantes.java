/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override protected Registro creaRegistro() {
      return new Estudiante(null, 0, 0.0, 0);
    }

    /**
     * Busca estudiantes por un campo específico.
     * @param campo el campo del registro por el cuál buscar; puede ser
     *              <ul>
     *               <li><tt>"nombre"</tt></li>
     *               <li><tt>"cuenta"</tt></li>
     *               <li><tt>"promedio"</tt></li>
     *               <li><tt>"edad"</tt></li>
     *              </ul>
     * @param texto el texto a buscar.
     * @return una lista con los estudiantes tales que en el campo especificado
     *         contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es ninguno de los
     *         especificados arriba.
     */
    @Override public Lista buscaRegistros(String campo, String texto) {
      Lista l = new Lista();
      registros.primero();
      switch(campo){
        case "nombre": return buscaNombre(texto, l);
        case "cuenta": return buscaCuenta(texto, l);
        case "promedio": return buscaPromedio(texto, l);
        case "edad": return buscaEdad(texto, l);
        default: throw new IllegalArgumentException();
      }
    }

    // Busca un nombre en un registro y regresa una lista que contenga todos
    // los registros con ese nombre.
    private Lista buscaNombre(String texto, Lista l){
      while(registros.iteradorValido()){
        Estudiante e = (Estudiante) registros.get();
        if(e.getNombre().equals(texto))
          l.agregaFinal(e);
        registros.siguiente();
      }
      return l;
    }

    // Busca una cuenta en un registro y regresa una lista que contenga todos
    // los registros con esa cuenta.
    private Lista buscaCuenta(String texto, Lista l){
      int i = texto.length();
      while(registros.iteradorValido()){
        Estudiante e = (Estudiante) registros.get();
        String s = String.valueOf(e.getCuenta());
        String mySubString = s.substring(0, i);
        if(mySubString.equals(texto))
          l.agregaFinal(e);
        registros.siguiente();
      }
      return l;
    }

    // Busca un promedio en un registro y regresa una lista que contenga todos
    // los registros con ese promedio.
    private Lista buscaPromedio(String texto, Lista l){
      while(registros.iteradorValido()){
        Estudiante e = (Estudiante) registros.get();
        String s = String.valueOf(e.getPromedio());
        if(s.equals(texto))
          l.agregaFinal(e);
        registros.siguiente();
      }
      return l;
    }

    // Busca una edad en un registro y regresa una lista que contenga todos
    // los registros con ese edad.
    private Lista buscaEdad(String texto, Lista l){
      while(registros.iteradorValido()){
        Estudiante e = (Estudiante) registros.get();
        String s = String.valueOf(e.getEdad());
        if(s.equals(texto))
          l.agregaFinal(e);
        registros.siguiente();
      }
      return l;
    }

}

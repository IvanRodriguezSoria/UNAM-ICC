/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos<Estudiante> {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override protected Estudiante creaRegistro() {
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
    @Override public Lista<Estudiante> buscaRegistros(String campo, String texto) {
      Lista<Estudiante> l = new Lista<>();
      registros.primero();
      while(registros.iteradorValido()){
        Estudiante e = registros.get();
        switch(campo){
          case "nombre":
            if(e.getNombre().contains(texto))
              l.agregaFinal(e);
              break;
          case "cuenta":
            if(String.valueOf( e.getCuenta() ).contains(texto))
              l.agregaFinal(e);
              break;
          case "promedio":
            if(String.valueOf( e.getPromedio() ).contains(texto))
              l.agregaFinal(e);
              break;
          case "edad":
            if(String.valueOf( e.getEdad() ).contains(texto))
              l.agregaFinal(e);
              break;
          default:
            throw new IllegalArgumentException();
        }
        registros.siguiente();
      }
      return l;
    }
  }

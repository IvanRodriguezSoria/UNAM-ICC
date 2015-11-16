import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, nÃºmero de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede cargarse y guardarse utilizando objetos de las clases {@link
 * BufferedReader} y {@link BufferedWriter} como entrada y salida
 * respectivamente.
 */
public class Estudiante implements Registro {

    /* Nombre del estudiante. */
    private StringProperty nombre;
    /* NÃºmero de cuenta. */
    private IntegerProperty cuenta;
    /* Pormedio del estudiante. */
    private DoubleProperty promedio;
    /* Edad del estudiante.*/
    private IntegerProperty edad;

    /**
     * Construye un estudiante con todas sus propiedades.
     * @param nombre el nombre del estudiante.
     * @param cuenta el nÃºmero de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
      this.nombre = new SimpleStringProperty(nombre);
      this.cuenta = new SimpleIntegerProperty(cuenta);
      this.promedio = new SimpleDoubleProperty(promedio);
      this.edad = new SimpleIntegerProperty(edad);
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
      return nombre.get();
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
      this.nombre.setValue(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty getNombreProperty() {
      return nombre;
    }

    /**
     * Regresa el nÃºmero de cuenta del estudiante.
     * @return el nÃºmero de cuenta del estudiante.
     */
    public int getCuenta() {
      return cuenta.get();
    }

    /**
     * Define el nÃºmero cuenta del estudiante.
     * @param cuenta el nuevo nÃºmero de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
      this.cuenta.setValue(cuenta);
    }

    /**
     * Regresa la propiedad del nÃºmero de cuenta.
     * @return la propiedad del nÃºmero de cuenta.
     */
    public IntegerProperty getCuentaProperty() {
      return cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
      return promedio.get();
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
      this.promedio.setValue(promedio);
    }

    /**
     * Regresa la propiedad del promedio.
     * @return la propiedad del promedio.
     */
    public DoubleProperty getPromedioProperty() {
      return promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
      return edad.get();
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
      this.edad.setValue(edad);
    }

    /**
     * Regresa la propiedad de la edad.
     * @return la propiedad de la edad.
     */
    public IntegerProperty getEdadProperty() {
      return edad;
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el mÃ©todo.
     * @param o el objeto con el que el estudiante se compararÃ¡.
     * @return <tt>true</tt> si el objeto o es un estudiante con las mismas
     *         propiedades que el objeto que manda llamar al mÃ©todo,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
      if( !(o instanceof Estudiante) )
        return false;
      Estudiante estObj = (Estudiante) o;
      return (this.getNombre().equals( estObj.getNombre() ) &&
              this.getCuenta() == estObj.getCuenta() &&
              this.getPromedio() == estObj.getPromedio() &&
              this.getEdad() == estObj.getEdad() );
    }

    /**
     * Regresa una representaciÃ³n en cadena del estudiante.
     * @return una representaciÃ³n en cadena del estudiante.
     */
    @Override public String toString() {
      return String.format("Nombre   : %s\n" +
                           "Cuenta   : %d\n" +
                           "Promedio : %2.2f\n" +
                           "Edad     : %d",
                           nombre.get(), cuenta.get(), promedio.get(), edad.get());
    }

    /**
     * Guarda al estudiante en la salida recibida.
     * @param out la salida dÃ³nde hay que guardar al estudiante.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public void guarda(BufferedWriter out) throws IOException {
      out.write(String.format("%s\t%d\t%2.2f\t%d\n",
                nombre.get(), cuenta.get(), promedio.get(), edad.get()));
    }

    /**
     * Carga al estudiante de la entrada recibida.
     * @param in la entrada de dÃ³nde hay que cargar al estudiante.
     * @return <tt>true</tt> si el mÃ©todo carga un estudiante vÃ¡lido,
     *         <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public boolean carga(BufferedReader in) throws IOException {
      String s = in.readLine();
      if(s == null)
        return false;
      s = s.trim();
      if(s.equals(""))
        return false;
      String[] splitedString = s.split("\t");
      if(splitedString.length != 4)
        throw new IOException();
      nombre.setValue( splitedString[0] );
      try{
        cuenta.setValue( Integer.parseInt( splitedString[1] ) );
        promedio.setValue( Double.parseDouble( splitedString[2] ) );
        edad.setValue( Integer.parseInt( splitedString[3] ) );
      }catch(NumberFormatException e){
        throw new IOException();
      }
      return true;
    }
}

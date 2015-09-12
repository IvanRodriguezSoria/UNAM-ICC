/**
 * <p>Clase para matrices de 2×2.</p>
 *
 * <p>Las matrices de 2×2 pueden sumarse, multiplicarse y sacar su
 * determinante.</p>
 *
 * <p>Las matrices se crean con cuatro dobles a, b, c y d, tales que representan
 * a la matriz:</p>
 *
<pre>
 ⎛ a  b ⎞
 ⎝ c  d ⎠
</pre>
 */
public class Matriz2x2 {

    /* La primera entrada de la matriz. */
    private double a;
    /* La segunda entrada de la matriz. */
    private double b;
    /* La tercera entrada de la matriz. */
    private double c;
    /* La cuarta entrada de la matriz. */
    private double d;

    /**
     * Constructor único. Dado que no proveemos <em>setters</em>, nuestras
     * matrices de 2×2 son <em>inmutables</em>; no podemos cambiar sus valores.
     * @param a la primera entrada de la matriz.
     * @param b la segunda entrada de la matriz.
     * @param c la tercera entrada de la matriz.
     * @param d la cuarta entrada de la matriz.
     */
    public Matriz2x2(double a, double b,
                     double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Regresa el elemento <tt>a</tt> de la matriz de 2×2.
     * @return El elemento <tt>a</tt> de la matriz de 2×2.
     */
    public double getA() {
        return a;
    }

    /**
     * Regresa el elemento <tt>b</tt> de la matriz de 2×2.
     * @return El elemento <tt>b</tt> de la matriz de 2×2.
     */
    public double getB() {
        return b;
    }

    /**
     * Regresa el elemento <tt>c</tt> de la matriz de 2×2.
     * @return El elemento <tt>c</tt> de la matriz de 2×2.
     */
    public double getC() {
        return c;
    }

    /**
     * Regresa el elemento <tt>d</tt> de la matriz de 2×2.
     * @return El elemento <tt>d</tt> de la matriz de 2×2.
     */
    public double getD() {
        return d;
    }

    /**
     * Suma la matriz de 2×2 con la matriz de 2×2 que recibe como parámetro.
     * @param m La matriz de 2×2 con la que hay que sumar.
     * @return La suma con la matriz de 2×2 <tt>m</tt>.
     */
    public Matriz2x2 suma(Matriz2x2 m) {
        double aSuma = a + m.getA();
        double bSuma = b + m.getB();
        double cSuma = c + m.getC();
        double dSuma = d + m.getD();
        Matriz2x2 matrizSuma = new Matriz2x2(aSuma, bSuma, cSuma, dSuma);
        return matrizSuma;
    }

    /**
     * Multiplica la matriz de 2×2 con la matriz de 2×2 que recibe como
     * parámetro.
     * @param m La matriz de 2×2 con la que hay que multiplicar.
     * @return La multiplicación con la matriz de 2×2 <tt>m</tt>.
     */
    public Matriz2x2 multiplica(Matriz2x2 m) {
        double aMult = (a * m.getA()) + (b * m.getC());
        double bMult = (a * m.getB()) + (b * m.getD());
        double cMult = (c * m.getA()) + (d * m.getC());
        double dMult = (c * m.getB()) + (d * m.getD());
        return new Matriz2x2(aMult, bMult, cMult, dMult);
    }

    /**
     * Multiplica la matriz de 2×2 con la constante que recibe como parámetro.
     * @param x La constante con la que hay que multiplicar.
     * @return La multiplicación con la constante <tt>x</tt>.
     */
    public Matriz2x2 multiplica(double x) {
        return new Matriz2x2(a * x, b * x, c * x, d * x);
    }

    /**
     * Calcula el determinante de la matriz de 2×2.
     * @return El determinante de la matriz de 2×2.
     */
    public double determinante() {
        double nuevaDet = (a * d) - (c * b);
        return nuevaDet;
    }

    /*
     * Metodo auxiliar para añadir espacios faltantes tomado de las
     * pruebas unitarias
     */
    private String agregarEspacio(String valor, int n){
      if(valor.length() >= n){
        return valor;
      }else{
        valor = " " + valor;
        return agregarEspacio(valor, n);
      }
    }

    /**
     * Regresa una cadena con la representación de la matriz.
     * @return una cadena con la representación de la matriz.
     */
    public String toString() {
      String aS = String.format("%2.3f", a);
      String bS = String.format("%2.3f", b);
      String cS = String.format("%2.3f", c);
      String dS = String.format("%2.3f", d);

      int valorMayor = Math.max(Math.max(aS.length(), bS.length()),
                                Math.max(cS.length(), dS.length()));

      aS = agregarEspacio(aS, valorMayor);
      bS = agregarEspacio(bS, valorMayor);
      cS = agregarEspacio(cS, valorMayor);
      dS = agregarEspacio(dS, valorMayor);

      return String.format("⎛ %s, %s ⎞\n", aS, bS) +
             String.format("⎝ %s, %s ⎠",   cS, dS);
    }

    /**
    * Calcula la inversa de la matriz de 2×2.
    *
    * Si multiplicamos una matriz de 2×2 con su inversa, obtenemos la matriz
    * identidad.
    * @return La inversa de la matriz de 2×2, o <tt>null</tt> si la matriz no
    *         es inversible.
    */
   public Matriz2x2 inversa() {
       double det = determinante();
       if(det == 0.0){
         return null;
       }
       return new Matriz2x2(d / det, -b / det, -c / det, a / det);
   }

   /**
    * Calcula la <em>n</em>-ésima potencia de la matriz de 2×2.
    *
    * La <em>n</em>-ésima potencia de una matriz de 2×2 es el resultado de
    * multiplicar la matriz consigo misma <em>n</em> veces.
    * @param n La potencia a la que hay que elevar la matriz; si <em>n</em> es
    *          menor que 2, regresa una copia de la matriz de 2×2.
    * @return la <em>n</em>-ésima potencia de la matriz de 2×2.
    */
   public Matriz2x2 potencia(int n) {
     Matriz2x2 m = new Matriz2x2(a, b, c, d);
     return potenciaR(m, 1, n);
   }

   // Auxiliar potencia
   private Matriz2x2 potenciaR(Matriz2x2 m, int i, int n){
     if(n < 2){
       return m;
     }else if(i == n){
       return m;
     }else{
       return potenciaR(m, ++i, n).multiplica(this);
     }
   }

}

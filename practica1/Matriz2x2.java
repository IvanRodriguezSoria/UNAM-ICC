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
        Matriz2x2 matrizMult = new Matriz2x2(aMult, bMult, cMult, dMult);
        return matrizMult;
    }

    /**
     * Multiplica la matriz de 2×2 con la constante que recibe como parámetro.
     * @param x La constante con la que hay que multiplicar.
     * @return La multiplicación con la constante <tt>x</tt>.
     */
    public Matriz2x2 multiplica(double x) {
        double aConst = a * x;
        double bConst = b * x;
        double cConst = c * x;
        double dConst = d * x;
        Matriz2x2 matrizConst = new Matriz2x2(aConst, bConst, cConst, dConst);
        return matrizConst;
    }

    /**
     * Calcula el determinante de la matriz de 2×2.
     * @return El determinante de la matriz de 2×2.
     */
    public double determinante() {
        double nuevaDet = (a * d) - (c * b);
        return nuevaDet;
    }

    /**
     * Regresa una cadena con la representación de la matriz.
     * @return una cadena con la representación de la matriz.
     */
    public String toString() {
        return String.format("⎛ %g, %g ⎞\n"+
                             "⎝ %g, %g ⎠",a, b, c, d);
    }
}

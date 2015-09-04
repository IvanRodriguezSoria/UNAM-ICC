package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas doblemente ligadas de cadenas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas tienen un iterador para poder recorrerlas.</p>
 */
public class ListaCadena {

    /* Clase Nodo privada para uso interno de la clase ListaCadena. */
    private class Nodo {
        public String elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(String elemento) {
            this.elemento = elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Nodo iterador. */
    private Nodo iterador;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        longitud = 0;
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          longitud++;
        }
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(String elemento) {
        if(cabeza == null){
          cabeza = new Nodo(elemento);
          rabo = cabeza;
          iterador = cabeza;

        }else{
          rabo.siguiente = new Nodo(elemento);
          rabo.siguiente.anterior = rabo;
          rabo = rabo.siguiente;
          iterador = cabeza;
        }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(String elemento) {
        if(cabeza == null){
          cabeza = new Nodo(elemento);
          rabo = cabeza;
          iterador = cabeza;

        }else{
          cabeza.anterior = new Nodo(elemento);
          cabeza.anterior.siguiente = cabeza;
          cabeza = cabeza.anterior;
          iterador = cabeza;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica. Si un elemento de la lista es
     * modificado, el iterador se mueve al primer elemento.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(String elemento) {
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          if(n.elemento.equals(elemento)){
            if(n.anterior == null){
              this.eliminaPrimero();
              iterador = cabeza;
              return;
            }else if(n.siguiente == null){
              this.eliminaUltimo();
              iterador = cabeza;
              return;
            }else{
              n.anterior.siguiente = n.siguiente;
              n.siguiente.anterior = n.anterior;
              iterador = cabeza;
              return;
            }
          }
        }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaPrimero() {
        if(cabeza == null){
          return null;
        }else{
          String elementoEliminado = cabeza.elemento;
          if(cabeza.siguiente == null){
            cabeza = null;
            return elementoEliminado;
          }else{
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            return elementoEliminado;
          }
        }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista está vacía.
     */
    public String eliminaUltimo() {
        if(cabeza == null){
          return null;
        }else{
          String elementoEliminado = rabo.elemento;
          if(cabeza.siguiente == null){
            cabeza = null;
            return elementoEliminado;
          }else{
            rabo = rabo.anterior;
            rabo.siguiente = null;
            return elementoEliminado;
          }
        }
    }

    /**
     * Nos dice si un elemento está en la lista. El iterador no se mueve.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(String elemento) {
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          if(n.elemento.equals(elemento)){
            return true;
          }
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaCadena reversa() {
        ListaCadena l = new ListaCadena();
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          l.agregaInicio(n.elemento);
        }
        return l;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaCadena copia() {
        ListaCadena l = new ListaCadena();
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          l.agregaFinal(n.elemento);
        }
        return l;
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {
        cabeza = null;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getPrimero() {
        if(cabeza == null){
          return null;
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public String getUltimo() {
        if(cabeza == null){
          return null;
        }
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public String get(int i) {
        if(i < 0 || i >= getLongitud()){
          return null;
        }
        int contador = 0;
        String s = null;
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          if(contador == i){
            s = n.elemento;
            break;
          }
          contador++;
        }
        return s;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(String elemento) {
        int contador = 0;
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          if(n.elemento.equals(elemento)){
            return contador;
          }
          contador++;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        String s = "[";
        for(Nodo n = cabeza; n != null; n = n.siguiente){
          if(n.siguiente == null){
            break;
          }
          s += String.format("%s, ", n.elemento);
        }
        s += String.format("%s]", this.getUltimo());
        return s;
    }

    /**
     * Mueve el iterador de la lista a su primer elemento.
     */
    public void primero() {
        iterador = cabeza;
    }

    /**
     * Mueve el iterador de la lista a su último elemento.
     */
    public void ultimo() {
        iterador = rabo;
    }

    /**
     * Mueve el iterador al siguiente elemento.
     */
    public void siguiente() {
        iterador = iterador.siguiente;
    }

    /**
     * Mueve el iterador al elemento anterior.
     */
    public void anterior() {
        iterador = iterador.anterior;
    }

    /**
     * Regresa el elemento al que el iterador apunta.
     * @return el elemento al que el iterador apunta, o <tt>null</tt> si el
     *         iterador es inválido.
     */
    public String get() {
        return iterador.elemento;
    }

    /**
     * Nos dice si el iterador es válido.
     * @return <tt>true</tt> si el iterador es válido; <tt>false</tt> en otro
     *         caso.
     */
    public boolean iteradorValido() {
        if(iterador == null){
          return false;
        }
        return true;
    }

}

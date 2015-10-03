import java.util.NoSuchElementException;
/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas tienen un iterador para poder recorrerlas, y no aceptan a
 * <code>null</code> como elemento.</p>
 */
public class Lista {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        public Object elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(Object elemento) {
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
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(Object elemento) throws IllegalArgumentException {
      if(elemento == null){
        throw new IllegalArgumentException();
      }else if(cabeza == null){
        cabeza = new Nodo(elemento);
        rabo = cabeza;
      }else{
        rabo.siguiente = new Nodo(elemento);
        rabo.siguiente.anterior = rabo;
        rabo = rabo.siguiente;
      }
      iterador = cabeza;
      longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(Object elemento) throws IllegalArgumentException {
      if(elemento == null){
        throw new IllegalArgumentException();
      }if(cabeza == null){
        cabeza = new Nodo(elemento);
        rabo = cabeza;
      }else{
        cabeza.anterior = new Nodo(elemento);
        cabeza.anterior.siguiente = cabeza;
        cabeza = cabeza.anterior;
      }
      iterador = cabeza;
      longitud++;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica. Si un elemento de la lista es
     * modificado, el iterador se mueve al primer elemento.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {
      for(Nodo n = cabeza; n != null; n = n.siguiente){
        if(n.elemento.equals(elemento)){
          if(n.anterior == null){
            eliminaPrimero();
          }else if(n.siguiente == null){
            eliminaUltimo();
          }else{
            n.anterior.siguiente = n.siguiente;
            n.siguiente.anterior = n.anterior;
            longitud--;
          }
          iterador = cabeza;
          break;
        }
      }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaPrimero() throws NoSuchElementException {
      if(cabeza == null)
        throw new NoSuchElementException();
      else{
        Object elementoEliminado = cabeza.elemento;
        if(cabeza.siguiente == null){
          cabeza = null;
          rabo = null;
        }else{
          cabeza = cabeza.siguiente;
          cabeza.anterior = null;
        }
        longitud--;
        return elementoEliminado;
      }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaUltimo() throws NoSuchElementException {
      if(cabeza == null)
        throw new NoSuchElementException();
      else{
        Object elementoEliminado = rabo.elemento;
        if(cabeza.siguiente == null){
          cabeza = null;
          rabo = null;
        }else{
          rabo = rabo.anterior;
          rabo.siguiente = null;
        }
        longitud--;
        return elementoEliminado;
      }
    }

    /**
     * Nos dice si un elemento está en la lista. El iterador no se mueve.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(Object elemento) {
      for(Nodo n = cabeza; n != null; n = n.siguiente){
        if(n.elemento.equals(elemento))
          return true;
      }
      return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
      Lista l = new Lista();
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
    public Lista copia() {
      Lista l = new Lista();
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
      rabo = null;
      longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getPrimero() throws NoSuchElementException {
      if(cabeza == null)
        throw new NoSuchElementException();
      else
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getUltimo() throws NoSuchElementException {
      if(cabeza == null)
        throw new NoSuchElementException();
      else
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public Object get(int i) throws ExcepcionIndiceInvalido {
      if(i < 0 || i >= getLongitud()){
        throw new ExcepcionIndiceInvalido();
      }
      int contador = 0;
      Object s = null;
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
     * @return el índice del elemento recibido en la lista, o -1 si
     *         el elemento no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
      int contador = 0;
      for(Nodo n = cabeza; n != null; n = n.siguiente){
        if(n.elemento.equals(elemento))
          return contador;
        contador++;
      }
      return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
      String s = "[";
      for(Nodo n = cabeza; n != null; n = n.siguiente){
        if(n.siguiente != null)
          s += String.format("%s, ", n.elemento);
      }
      if(rabo != null)
        return s += String.format("%s]", rabo.elemento);
      else
        return s += "]";
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
     * Mueve el iterador al elemento siguiente.
     * @throws NoSuchElementException si no hay elemento siguiente.
     */
    public void siguiente() throws NoSuchElementException {
      if(iterador != null){
        iterador = iterador.siguiente;
      }else{
        throw new NoSuchElementException();
      }
    }

    /**
     * Mueve el iterador al elemento anterior.
     * @throws NoSuchElementException si no hay elemento anterior.
     */
    public void anterior() throws NoSuchElementException {
      if(iterador != null){
        iterador = iterador.anterior;
      }else{
        throw new NoSuchElementException();
      }
    }

    /**
     * Regresa el elemento al que el iterador apunta.
     * @return el elemento al que el iterador apunta.
     * @throws NoSuchElementException si el iterador es inválido.
     */
    public Object get() throws NoSuchElementException {
      if(iteradorValido()){
        return iterador.elemento;
      }else{
        throw new NoSuchElementException();
      }
    }

    /**
     * Nos dice si el iterador es válido.
     * @return <tt>true</tt> si el iterador es válido; <tt>false</tt> en otro
     *         caso.
     */
    public boolean iteradorValido() {
      return iterador != null;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
      if(o instanceof Lista){
        Lista listaObj = (Lista) o;
        Nodo nodoObj = listaObj.cabeza;
        Nodo n = cabeza;
        while(nodoObj != null && n != null){
          if( !(nodoObj.elemento.equals(n.elemento)) )
            return false;
          nodoObj = nodoObj.siguiente;
          n = n.siguiente;
        }
        return true;
      }else
        return false;
    }
}

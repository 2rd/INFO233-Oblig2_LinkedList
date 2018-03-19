import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * En lenket implementasjon av interfacet IList.
 *
 * @author Tord Kvifte (tkv015), 12.03.2018.
 * @param <E> typen til listen.
 */
public class LinkedList<E> implements IList<E> {

    private Node firstNode;
    private int size;

    public LinkedList(){
    }

    public LinkedList(E elem){
        this.put(elem);
    }

    public LinkedList(E elem, LinkedList<? extends E> list){
        this.put(elem);
        this.append(list);

    }

    /**
     * ,* Gir det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E first() throws NoSuchElementException {
        if (this.size() == 0){
            throw new NoSuchElementException();
        }

        return firstNode.getData();

    }

    /**
     * ,* Returnerer alle elementene i listen bortsett fra det
     * ,* første.
     * ,*
     * ,* @return Resten av listen.
     * ,
     */
    @Override
    public IList<E> rest() {
        if (this.size() == 0 || this.size() == 1){
            return null;
        }
        else {
            IList<E> list = new LinkedList<>();
            Node element = firstNode.getNext();
            for (int i = 0; i < this.size() - 1; i++) {
                list.add(element.getData());
                element = element.getNext();
            }
            return list;
        }
    }

    /**
     * ,* Legger til et element på slutten av listen.
     * ,
     */
    @Override
    public boolean add(E elem){
        Node newNode = new Node(elem);
        Node node = this.firstNode;

        if (this.size() == 0){
            this.firstNode = newNode;
        }
        else if(this.size() == 1){
            this.firstNode.setNext(newNode);
        }
        else{
            for (int i = 1; i< this.size(); i++){
                node = node.getNext();
            }
            node.setNext(newNode);
        }
        this.size++;
        return true;
    }

    /**
     * ,* Legger til et element på begynnelsen av listen.
     * ,
     */
    @Override
    public boolean put(E elem){
        Node newNode = new Node(elem);

        if(this.size() != 0){
            newNode.setNext(this.firstNode);
        }
        this.firstNode = newNode;
        this.size++;
        return true;
    }

    /**
     * ,* Fjerner det første elementet i listen.
     * ,*
     * ,* @return Det første elementet i listen.
     * ,* @throws NoSuchElementException Hvis listen er tom.
     * ,
     */
    @Override
    public E remove() throws NoSuchElementException {
        if(this.size() == 0){
            throw new NoSuchElementException();
        }

        E first = firstNode.getData();
        if(this.size() != 1) {
            firstNode = firstNode.getNext();
        }
        else{
            firstNode = null;
        }
        size--;
        return first;
    }

    /**
     * ,* Fjerner det angitte objektet fra listen.
     * ,*
     * ,* @param o Objektet som skal fjernes.
     * ,* @return true hvis et element ble fjernet, false
     * ,* ellers.
     * ,
     */
    @Override
    public boolean remove(Object o) {
        if (this.contains(o)){
            Node node = this.firstNode;
            Node previousNode = null;

            while (node != null){
                if(o.equals(node.getData())){
                    if(previousNode == null){
                        this.firstNode = node.getNext();
                    }
                    else {
                        previousNode.setNext(node.getNext());
                    }
                    size--;
                    return true;
                }

                previousNode = node;
                node = node.getNext();

            }
        }
        return false;
    }

    /**
     * ,* Sjekker om et element er i listen.
     * ,*
     * ,* @param o objektet vi sjekker om er i listen.
     * ,* @return true hvis objektet er i listen, false ellers.
     * ,
     */
    @Override
    public boolean contains(Object o) {
        Node node = this.firstNode;
        if(this.size() >= 1) {
            while(node != null) {
                if (o.equals(node.getData())) {
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }

    /**
     * ,* Sjekker om listen er tom.
     * ,*
     * ,* @return true hvis listen er tom, false ellers.
     * ,
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* slutten av listen.
     * ,*
     * ,* @param listen som blir lagt til.
     * ,
     */
    @Override
    public void append(IList<? extends E> list) {

        for (int i = list.size(); i > 0; i--) {
            this.add( list.remove());
        }
    }

    /**
     * ,* Legger til alle elementene i den angitte listen på
     * ,* begynnelsen av listen.
     * ,*
     * ,* @param list listen som blir lagt til
     * ,
     */
    @Override
    public void prepend(IList<? extends E> list) {

        for (int i = list.size(); i > 0; i--) {
            this.put(list.remove());
        }
    }

    /**
     * ,* Slår sammen flere lister
     * ,*
     * ,* @param lists listene som skal slås sammen
     * ,* @return Ny liste med alle elementene fra listene som
     * ,* skal slås sammen.
     * ,
     */
    @Override
    public IList<E> concat(IList<? extends E>... lists) {
        IList<E> newList = new LinkedList<>();
        for(IList<? extends E> list : lists){
            while (!list.isEmpty()){
                newList.put(list.remove());
            }
        }
        return newList;
    }

    /**
     * ,* Sorterer listen ved hjelp av en
     * ,* sammenligningsfunksjon
     * ,* @param c sammenligningsfunksjon som angir rekkefølgen
     * ,* til elementene i listen
     * ,
     */
    @Override
    public void sort(Comparator<? super E> c) {
    }

    /**
     * ,* Fjerner elementer fra listen som svarer til et
     * ,* predikat.
     * ,* @param filter predikat som beskriver hvilken
     * ,* elementer som skal fjernes.
     * ,
     */
    @Override
    public void filter(Predicate<? super E> filter) {
        Iterator iterator = this.iterator();
        while (iterator.hasNext()){
            E next = (E) iterator.next();
            if (filter.test(next)){
                this.remove(next);
            }

        }
    }


    /**
     * ,* Kjører en funksjon over hvert element i listen
     * ,*
     * ,* @param f en funksjon fra typen til elementene i
     * ,* listen til en annen type
     * ,* @return En liste over elementene som funksjonen
     * ,* returnerer
     * ,
     */
    @Override
    public <U> IList<U> map(Function<? super E, ? extends U> f) {
        IList<U> newList = new LinkedList<>();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()){
            E next = (E) iterator.next();
            newList.add(f.apply(next));
        }
        return newList;
    }

    /**
     * ,* Slår sammen alle elementene i listen ved hjelp av en
     * ,* kombinasjonsfunksjon.
     * ,*
     * ,* @param t Det første elementet i sammenslåingen
     * ,* @param accum Funksjonen som holder styr på de
     * ,* sammenslåtte elementene
     * ,* @param combiner funksjonen som slår sammen to
     * ,* elementer
     * ,* @return Den akkumulerte verdien av sammenslåingene
     * ,
     */
    @Override
    public <T> T reduce(T t, BiFunction<T, ? super E, T> f) {
        Iterator iterator = this.iterator();

        while (iterator.hasNext()){
            t = f.apply(t, (E) iterator.next());
        }

        return t;
    }

    /**
     * ,* Gir størrelsen på listen
     * ,*
     * ,* @return Størrelsen på listen
     * ,
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * ,* Fjerner alle elementene i listen.
     * ,
     */
    @Override
    public void clear() {
        this.firstNode = null;
        this.size = 0;
    }

    /**
     * @return en iterator for listen.
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }



    /**
     * En implementasjon av Iterator for LinkedList.
     */
    private class ListIterator implements Iterator<E>{

        private Node current = firstNode;

        /**
         * @return false om current var sist i listen.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * returnerer dataen til current, og setter current til neste node.
         * @return dataen til current.
         */
        @Override
        public E next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            E elem = (E) current.getData();
            current = current.getNext();
            return elem;
        }
    }

    /**
     * Anonym klasse Node.
     * En Node holder på en dataverdi og en peker til neste Node i listen.
     */
    private class Node{
        private E data;
        private Node next;

        /**
         * Oppretter en ny node med verdin elem.
         * @param elem Verdien som lagres i Noden.
         */
        public Node(E elem){
            this.data = elem;
        }

        /**
         * @return verdien Noden holder på.
         */
        public E getData(){
            return this.data;
        }

        /**
         * Setter en peker til neste Node i listen.
         * @param nextNode neste Node i listen.
         */
        public void setNext(Node nextNode){
            this.next = nextNode;
        }

        /**
         * @return neste Node i listen.
         */
        public Node getNext() {
            return this.next;
        }
    }
}



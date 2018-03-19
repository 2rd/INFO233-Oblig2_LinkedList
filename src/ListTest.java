

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    //Oppgave 1.1

    /**
     * Tester first-metoden i en tom LinkedList.
     * Skal kaste en NoSuchElementException.
     */
    @Test
    void oppg1_1_testEmptyFirst(){
        IList<String> list = new LinkedList<>();

        assertThrows(NoSuchElementException.class, list::first);
    }

    /**
     * Tester rest-metoden i en tom LinkedList.
     * Skal returnere null.
     */
    @Test
    void oppg1_1_testRestOfEmpty(){
        IList<String> list = new LinkedList<>();

        assertNull(list.rest());
    }

    /**
     * Tester add-metoden i en tom LinkedList.
     * Skal legge til et element i listen og returnere true.
     */
    @Test
    void oppg1_1_testAddToEmpty(){
        IList<String> list = new LinkedList<>();

        assertTrue(list.add("one"));

        assertEquals("one", list.first());
    }

    /**
     * Tester put-metoden i en tom LinkedList.
     * Skal legge til et element i listen og returnere true.
     */
    @Test
    void oppg1_1_testPutToEmpty(){
        IList<String> list = new LinkedList<>();


        assertTrue(list.put("one"));
        list.put("one");
        assertEquals("one", list.first());
    }

    /**
     * Tester remove-metoden i en tom LinkedList.
     * Skal kaste en NoSuchElementException.
     */
    @Test
    void oppg1_1_testRemoveFromEmpty(){
        IList<String> list = new LinkedList<>();

        assertThrows(NoSuchElementException.class, list::remove);
    }

    //Oppgave 1.2

    /**
     * @return en liste som inneholder 1 element.
     */
    private IList<String> listWithOneElem(){
        IList<String> list = new LinkedList<>();
        list.put("element");
        return list;
    }

    /**
     * Tester first-metoden i en LinkedList med 1 element.
     * Skal returnere det første elementet i listen.
     */
    @Test
    void oppg1_2_testFirstOne(){
        IList<String> list = listWithOneElem();

        assertEquals("element", list.first().toString());
    }

    /**
     * Tester rest-metoden i en LinkedList med 1 element.
     * Skal returnere en tom LinkedList.
     */
    @Test
    void oppg1_2_testRestOfOne(){
        IList<String> list = listWithOneElem();

        assertNull(list.rest());
    }

    /**
     * Tester add-metoden i en LinkedList med 1 element..
     * Skal legge til et element sist i listen og returnere true.
     */
    @Test
    void oppg1_2_testAddToOne(){
        IList<String> list = listWithOneElem();

        assertTrue(list.add("element2"));
        assertNotEquals("element2", list.first());
        assertTrue(list.contains("element2"));
    }

    /**
     * Tester put-metoden i en LinkedList med 1 element..
     * Skal legge til et element først i listen og returnere true.
     */
    @Test
    void oppg1_2_testPutToOne(){
        IList<String> list = listWithOneElem();

        assertTrue(list.put("element2"));
        list.put("element2");
        assertEquals(list.first(), "element2");
    }

    /**
     * Tester remove-metoden i en LinkedList med 1 element.
     * Skal returnere og fjerne elementet fra listen.
     */
    @Test
    void oppg1_2_testRemoveFromOne(){
        IList<String> list = listWithOneElem();

        assertEquals(list.remove(), "element");

        assertThrows(NoSuchElementException.class, list::first);
    }

    //Oppgave 1.3

    /**
     * @return en liste som inneholder 1 element.
     */
    private IList<String> listWithMoreElem(){
        IList<String> list = new LinkedList<>();
        list.add("element");
        list.add("element2");
        list.add("element3");
        list.add("element4");
        return list;
    }

    /**
     * Tester first-metoden i en LinkedList med flere element.
     * Skal returnere det første elementet i listen.
     */
    @Test
    void oppg1_3_testFirstMany(){
        IList<String> list = listWithMoreElem();

        assertEquals("element", list.first());
    }

    /**
     * Tester rest-metoden i en LinkedList med flere element.
     * Skal returnere en liste med alle elementene unntatt det første.
     */
    @Test
    void oppg1_3_testRestOfMany(){
        IList<String> list = listWithMoreElem();
        IList<String> comparisonList = new LinkedList<>();
        comparisonList.add("element2");
        comparisonList.add("element3");
        comparisonList.add("element4");

        list = list.rest();

        for (int i = 0; i < comparisonList.size(); i++) {
            assertEquals(list.remove(), comparisonList.remove());
        }


    }

    /**
     * Tester add-metoden i en LinkedList med flere element.
     * Skal legge til et element sist i listen og returnere true.
     */
    @Test
    void oppg1_3_testAddToMany(){
        IList<String> list = listWithMoreElem();

        assertTrue(list.add("element5"));
        assertNotEquals("element5", list.first());
        assertTrue(list.contains("element5"));
    }

    /**
     * Tester put-metoden i en LinkedList med flere element.
     * Skal legge til et element først i listen og returnere true.
     */
    @Test
    void oppg1_3_testPutToMany(){
        IList<String> list = listWithMoreElem();

        assertTrue(list.put("element5"));
        list.put("element5");
        assertEquals(list.first(), "element5");
    }

    /**
     * Tester remove-metoden i en LinkedList med flere element.
     * Skal returnere og fjerne det første elementet fra listen.
     */
    @Test
    void oppg1_3_testRemoveFromMany(){
        IList<String> list = listWithMoreElem();
        String[] a = {"element", "element2", "element3", "element4"};

        assertEquals(list.remove(), "element");
        assertFalse(list.contains("element"));
        assertEquals(list.remove(), "element2");
        assertEquals(list.remove(), "element3");
        assertEquals(list.remove(), "element4");
        assertEquals(0, list.size());
    }

    /**
     * Oppgave 2.1
     *
     * Tester for remove(object) i ulike scenarioer.
     */

    /**
     * Tester contains(Object) for en tom liste.
     */
    @Test
    void oppg_2_testRemoveObject0(){
        IList<String> emptyList = new LinkedList<>();
        assertFalse(emptyList.remove("something"));
    }

    /**
     * Tester contains(Object) for en liste med ett element.
     */
    @Test
    void oppg_2_testRemoveObject1(){
        IList<String> aList = new LinkedList<>("something");
        assertTrue(aList.remove("something"));
        assertFalse(aList.remove("something"));
        assertThrows(NoSuchElementException.class, aList::first);
    }

    /**
     * Tester contains(Object) for en liste med flere element.
     */
    @Test
    void oppg_2_testRemoveObjectn(){
        IList<String> list = listWithMoreElem();
        assertTrue(list.remove("element4"));
        assertFalse(list.remove("element4"));

    }

    /**
     * Oppgave 3.1
     *
     * Tester for contains(Object o), isEmpty og size.
     */

    /***
     * Tester contains(Object) for en tom liste.
     */

    @Test
    void oppg_3_testContainsObject0(){
        IList<String> emptyList = new LinkedList<>();
        assertFalse(emptyList.contains("something"));
    }

    /**
     * Tester contains(Object) for en liste med ett element.
     */
    @Test
    void oppg_3_testContainsObject1(){
        IList<String> aList = new LinkedList<>("something");
        assertTrue(aList.contains("something"));
    }
    /**
    * Tester contains(Object) for en liste med flere element.
    */
    @Test
    void oppg_3_testContainsObjectn(){
        IList<String> list = listWithMoreElem();
        assertTrue(list.contains("element4"));
        assertFalse(list.contains("element5"));
    }

    /**
     * Tester isEmpty når den er tom og når den ikke er det.
     */
    @Test
    void oppg_3_testIsEmpty(){
        IList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.add("something");
        assertFalse(list.isEmpty());
    }

    /**
     * Tester at size-metoden returnerer riktig størrelse.
     */
    @Test
    void oppg_3_testSize(){
        IList<String> list = new LinkedList<>("something");
        assertEquals(1, list.size());
        list.put("somethingElse");
        assertEquals(2, list.size());
    }

    //Oppgave 4

    /**
     * Tester append-metoden.
     */
    @Test
    void oppg_4_testAppend(){
        IList<String> list1 = listWithMoreElem();
        IList<String> list2 = new LinkedList<>();

        String[] strings = {"element5", "element6", "element7"};
        String[] comparison = {"element7", "element6", "element5", "element4", "element3", "element2", "element"};

        //legger til strings i en ny liste
        for (String aString : strings) {
            list2.add(aString);
        }

        //bruker append til å legge til list2 på slutten av list1
        list1.append(list2);
        assertEquals(7, list1.size());
        assertEquals("element", list1.first());

        //sjekker at append har lagt elementene på rett plass.
        for (int i = list1.size() - 1; i > 0; i--) {
            assertEquals(comparison[i], list1.remove());
        }

    }

    /**
     * Tester prepend-metoden.
     */
    @Test
    void oppg_4_testPrepend(){
        IList<String> list1 = listWithMoreElem();
        IList<String> list2 = new LinkedList<>();


        String[] strings = {"element5", "element6", "element7"};
        String[] comparison = {"element4", "element3", "element2", "element", "element5", "element6", "element7"};
        for (String aString : strings) {
            list2.add(aString);
        }

        //bruker prepend til å legge til list2 på starten av list1
        list1.prepend(list2);
        assertEquals(7, list1.size());
        assertEquals("element7", list1.first());

        //sjekker at prepend har lagt elementene på rett plass.
        for (int i = list1.size() - 1; i > 0; i--) {
            assertEquals(comparison[i], list1.remove());
        }

    }

    /**
     * Oppgave 5.1
     *
     * Tester av Concat-metoden.
     */
    @Test
    void testConcat(){
        IList<String> theList = new LinkedList<>();

        //Lager listene som skal slås sammen.
        IList<String> list1 = listWithMoreElem();
        IList<String> list2 = new LinkedList<>();
        IList<String> list3 = new LinkedList<>();

        list2.add("yada");
        list2.add("svada");
        list2.add("jabb");
        list3.add("something");
        list3.add("nothing");

        //Slår sammen listene list1, list2, og list3. Sjekker at den har riktig size.
        assertEquals(9, theList.concat(list1, list3, list2).size());
    }

    /**
     * Tester slå sammen en tom liste med en liste med flere elementer
     * ved bruk av concat.
     */
    @Test
    void testConcat0(){
        IList<String> theList = new LinkedList<>();

        //Lager listene som skal slås sammen.
        IList<String> list1 = listWithMoreElem();
        IList<String> list2 = new LinkedList<>();

        //Slår sammen listene list1, list2. Sjekker at den har riktig size.
        assertEquals(4, theList.concat(list1, list2).size());
    }

    /**
     * Oppgave 7
     *
     * Tester iterator i en liste med flere elementer.
     */
    @Test
    void testIterator(){
        IList<String> list = listWithMoreElem();
        Iterator iterator = list.iterator();
        String[] comparison = {"element", "element2", "element3", "element4"};
        int i = 0;
        while (iterator.hasNext()){
            Object string = iterator.next();
            assertEquals(comparison[i], string);
            i++;
        }
    }

    /**
     * Tester iterator for en tom LinkedList.
     * Skal kaste en NoSuchElementException.
     */
    @Test
    void testIterator0(){
        IList<String> list = new LinkedList<>();
        Iterator iterator = list.iterator();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void oppg8_sortIntegers() {
        // Se oppgave 8
        IList<Integer> list = new LinkedList<>();
        List<Integer> values = Arrays.asList(3, 8, 4, 7, 10, 6,
                1, 2, 9, 5);

        for (Integer value : values) {
            list.add(value);
        }
        list.sort(Comparator.comparingInt(x -> x));


        int n = list.remove();
        while (list.size() > 0) {
            int m = list.remove();
            System.out.println(m);
            if (n > m) {
                fail("Integer list is not sorted.");
            }
            n = m;
        }
    }

    @Test
    void oppg8_sortStrings() {
        // Se oppgave 8
        IList<String> list = new LinkedList<>();
        List<String> values = Arrays.asList(
                "g", "f", "a", "c", "b", "d", "e", "i", "j", "h"
        );
        for (String value : values) {
            list.add(value);
        }

        list.sort(Comparator.naturalOrder());

        String n = list.remove();
        while (list.size() > 0) {
            String m = list.remove();
            if (n.compareTo(m) > 0) {
                fail("String list is not sorted.");
            }
            n = m;
        }
    }

    /**
     * Oppgave 9
     *
     * tester at listen filtrerer ut elementer basert på predikat.
     */
    @Test
    void oppg_9_testFilter1(){
        IList<String> list = new LinkedList<>("elem");
        list.add("elem2");
        list.add("another2");
        list.filter(n -> n.endsWith("2"));
        assertFalse(list.contains("elem2"));
        assertFalse(list.contains("another2"));
        assertEquals("elem", list.first());
    }

    @Test
    void testFilter() {
        // Se oppgave 9
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        IList<Integer> list = new LinkedList<>();
        for (Integer value : values) {
            list.add(value);
        }

        list.filter(n -> n % 2 == 1);


        while(list.size() > 0) {
            int n = list.remove();
            if (n % 2 == 1) {
                fail("List contains filtered out elements.");
            }
        }

    }

    @Test
    void oppg10_map() {
        // Se oppgave 10
        List<String> values = Arrays.asList("1", "2", "3", "4", "5");

        IList<String> list = new LinkedList<>();
        for (String value : values) {
            list.add(value);
        }

        IList<Integer> result = list.map(Integer::parseInt);

        List<Integer> target = Arrays.asList(1, 2, 3, 4, 5);


        for (int t : target) {
            if (result.remove() != t) {
                fail("Result of map gives the wrong value.");
            }
        }
    }

    @Test
    void oppg11_reduceInts() {
        // Se oppgave 11
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);

        IList<Integer> list = new LinkedList<>();
        for (Integer value : values) {
            list.add(value);
        }

        int result = list.reduce(0, Integer::sum);

        assertEquals(5*((1 + 5)/2), result);
    }

    @Test
    void oppg11_reduceStrings() {
        List<String> values = Arrays.asList("e", "s", "t");
        IList<String> list = new LinkedList<>();
        for (String s : values) {
            list.add(s);
        }

        String result = list.reduce("t", (acc, s) -> acc + s);

        assertEquals("test", result);
    }

    @Test
    void ex1_FastSort() {
        // Se ekstraoppgave 1
        Random r = new Random();
        IList<Integer> list = new LinkedList<>();
        for (int n = 0; n < 1000000; n++) {
            list.add(r.nextInt());
        }

        assertTimeout(Duration.ofSeconds(2), () -> list.sort(Integer::compare));

        int n = list.remove();
        for(int m = list.remove(); !list.isEmpty(); m = list.remove()) {
            if (n > m) {
                fail("List is not sorted");
            }
            n = m;
        }
    }


}

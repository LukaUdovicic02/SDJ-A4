import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.collection.ArrayList;
import utility.collection.ListADT;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    ListADT<Character> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void addIndexZero(){
        list.add(0,'a');
        assertEquals('a',list.get(0));
    }

    @Test
    void addIndexOne(){
        list.add(0,null);
        list.add(1,'b');
        assertEquals('b',list.get(1));
    }

    @Test
    void addIndexMany(){
        list.add(0,'a');
        list.add(1,'a');
        list.add(2,null);
        list.add(2,'d');
        assertEquals('d',list.get(2));
        assertNull(list.get(3));
    }

    @Test
    void addIndexBoundary(){
        // add 0 already tested
        assertThrows(IndexOutOfBoundsException.class, ()->list.add(-1,'a'));

        //testing upper "boundaries" and auto expansion of list
        for (int i = 0; i <= 15; i++){
            list.add(i,(char)(i+65));
        }
        assertEquals((char)(15+65),list.get(15));
        list.add(15,null);
        assertEquals((char)(15+65),list.get(16));
    }

    @Test
    void addIndexException(){
        assertThrows(IndexOutOfBoundsException.class, ()->list.add(5,'a'));
        assertThrows(IndexOutOfBoundsException.class, ()->list.add(20,null));
    }

    @Test
    void addZero(){
        list.add(null);
        assertNull(list.get(0));
    }

    @Test
    void addOne(){
        list.add('a');
        list.add(null);
        assertNull(list.get(1));
    }

    @Test
    void addMany(){
        list.add('a');
        list.add('a');
        list.add(null);
        list.add('d');
        assertNull(list.get(2));
        assertEquals('d',list.get(3));
    }

    @Test
    void addBoundary(){
        // add 0 already tested

        //testing upper "boundaries" and auto expansion of list
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertEquals((char)(15+65),list.get(15));
        list.add(null);
        assertNull(list.get(16));
    }

    @Test
    void addException(){
        //
    }

    @Test
    void containsZero(){
        assertFalse(list.contains('a'));
    }

    @Test
    void containsOne(){
        list.add(null);
        list.add('d');
        assertTrue(list.contains('d'));
        assertTrue(list.contains(null));
    }

    @Test
    void containsMany(){
        list.add('a');
        list.add('a');
        list.add(null);
        list.add('d');
        assertTrue(list.contains('a'));
        assertTrue(list.contains(null));
    }

    @Test
    void containsBoundary(){
        // lower boundary already tested in containsZero

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertTrue(list.contains('P'));

        // Testing upper right boundary (index 16)
        list.add('Q');
        assertTrue(list.contains('Q'));
    }

    @Test
    void containsException(){
        //
    }

    @Test
    void getZero(){
        list.add('B');
        assertEquals('B',list.get(0));
    }

    @Test
    void getOne(){
        list.add(null);
        list.add(null);
        assertNull(list.get(1));
    }

    @Test
    void getMany(){
        list.add('A');
        list.add('B');
        list.add('B');
        list.add(null);
        list.add(null);
        list.add('A');
        assertNull(list.get(3));
        assertEquals('A',list.get(5));
    }

    @Test
    void getBoundary(){
        // left lower boundary tested in getOne

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertEquals('P',list.get(15));

        // Testing upper right boundary (index 16)
        list.add('Q');
        assertEquals('Q',list.get(16));
    }

    @Test
    void getException(){
        list.add(null);
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(1));
        for (int i = 0; i < 15; i++){
            list.add((char)(i+65));
        }
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(16));
        list.add('A');
        assertThrows(IndexOutOfBoundsException.class, ()->list.get(17));
    }

    @Test
    void indexOfZero(){
        // documentation is lacking, not found returns -1 but that is not described
        assertEquals(-1,list.indexOf(null));
        assertEquals(-1,list.indexOf('i'));
    }

    @Test
    void indexOfOne(){
        list.add('H');
        assertEquals(0, list.indexOf('H'));
    }

    @Test
    void indexOfMany(){
        list.add('A');
        list.add('B');
        list.add('B');
        list.add(null);
        list.add(null);
        list.add('A');
        assertEquals(0, list.indexOf('A'));
        assertEquals(3, list.indexOf(null));
    }

    @Test
    void indexOfBoundary(){
        // lower right boundary tested in indexOfOne

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertEquals(15,list.indexOf('P'));

        // Testing upper right boundary (index 16)
        list.add('Q');
        assertEquals(16,list.indexOf('Q'));
    }

    @Test
    void indexOfException(){
        //
    }

    @Test
    void isEmptyZero(){
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmptyOne(){
        list.add(null);
        assertFalse(list.isEmpty());
    }

    @Test
    void isEmptyMany(){
        list.add('G');
        list.add('H');
        list.add('D');
        list.add(null);
        list.add(null);
        list.add('B');
        assertFalse(list.isEmpty());
    }

    @Test
    void isEmptyBoundary(){
        // lower right boundary tested in isEmptyOne

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertFalse(list.isEmpty());

        // Testing upper right boundary (index 16)
        list.add('Q');
        assertFalse(list.isEmpty());
    }

    @Test
    void isEmptyException(){
        //
    }

    @Test
    void isFullZero(){
        assertFalse(list.isFull());
    }

    @Test
    void isFullOne(){
        list.add(null);
        assertFalse(list.isFull());
    }

    @Test
    void isFullMany(){
        list.add('G');
        list.add('H');
        list.add('D');
        list.add(null);
        list.add(null);
        list.add('B');
        assertFalse(list.isFull());
    }

    @Test
    void isFullBoundary(){
        // lower right boundary tested in isFullOne

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertFalse(list.isFull());

        // Testing upper right boundary (index 16)
        list.add('Q');
        assertFalse(list.isFull());

        list.add('R');
        list.add('S');
        list.add('T');
        assertFalse(list.isFull());
    }

    @Test
    void isFullException(){
        //
    }

    @Test
    void removeIndexZero(){
        list.add(null);
        assertNull(list.remove(0));
    }

    @Test
    void removeIndexOne(){
        list.add(null);
        list.add('A');
        assertEquals('A', list.remove(1));
    }

    @Test
    void removeIndexMany(){
        list.add('G');
        list.add('H');
        list.add('D');
        list.add(null);
        list.add(null);
        list.add('B');
        assertEquals('B', list.remove(5));
        assertNull(list.remove(4));
        assertNull(list.remove(3));
        assertEquals('D', list.remove(2));
    }

    @Test
    void removeIndexBoundary(){
        // lower right boundary tested in removeIndexZero

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertEquals('P',list.remove(15));

        // Testing upper right boundary (index 16)
        list.add('P');
        list.add('Q');
        assertEquals('Q',list.remove(16));
    }

    @Test
    void removeIndexException(){
        assertThrows(IndexOutOfBoundsException.class, ()-> list.remove(5));
        list.add(null);
        list.add('B');
        assertThrows(IndexOutOfBoundsException.class, ()-> list.remove(16));

    }

    @Test
    void removeElementZero(){
        list.add(null);
        assertNull(list.remove(null));
    }

    @Test
    void removeElementOne(){
        list.add(null);
        list.add('H');
        assertEquals('H', list.remove(Character.valueOf('H')));
    }

    @Test
    void removeElementMany(){
        list.add('G');
        list.add('H');
        list.add('D');
        list.add(null);
        list.add(null);
        list.add('B');
        assertEquals('D', list.remove(Character.valueOf('D')));
        assertNull(list.remove(null));
    }

    @Test
    void removeElementBoundary(){
        // lower right boundary tested in removeElementZero

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertEquals('P',list.remove(Character.valueOf('P')));

        // Testing upper right boundary (index 16)
        list.add('P');
        list.add('Q');
        assertEquals('Q',list.remove(Character.valueOf('Q')));
    }

    @Test
    void removeElementException(){
        assertThrows(IllegalStateException.class, ()-> list.remove(Character.valueOf('G')));
        list.add('G');
        assertThrows(IllegalStateException.class, ()-> list.remove(Character.valueOf('Q')));
        list.add('Q');
        assertThrows(IllegalStateException.class, ()-> list.remove(null));
    }

    @Test
    void setZero(){
        list.add(null);
        list.set(0,'A');
        assertEquals('A',list.get(0));
    }

    @Test
    void setOne(){
        list.add(null);
        list.add('A');
        list.set(1,null);
        assertNull(list.get(1));
    }

    @Test
    void setMany(){
        list.add('G');
        list.add('H');
        list.add('D');
        list.add(null);
        list.add(null);
        list.add('B');
        list.set(2,null);
        assertNull(list.get(2));
        list.set(4,'N');
        assertEquals('N',list.get(4));
    }

    @Test
    void setBoundary(){
        // lower right boundary tested in setZero

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        list.set(15,'Q');
        assertEquals('Q',list.get(15));

        // Testing upper right boundary (index 16)
        list.add('P');
        list.set(16,null);
        assertNull(list.get(16));
    }

    @Test
    void setException(){
        assertThrows(IndexOutOfBoundsException.class, ()->list.set(0,'A'));
        assertThrows(IndexOutOfBoundsException.class, ()->list.set(0,null));
        list.add('G');
        list.add('H');
        list.add('D');
        assertThrows(IndexOutOfBoundsException.class, ()->list.set(46,null));
    }

    @Test
    void sizeZero(){
        assertEquals(0,list.size());
    }

    @Test
    void sizeOne(){
        list.add(null);
        assertEquals(1,list.size());
    }

    @Test
    void sizeMany(){
        list.add('G');
        list.add('H');
        list.add('D');
        list.add(null);
        list.add(null);
        list.add('B');
        assertEquals(6,list.size());
    }

    @Test
    void sizeBoundary(){
        // lower right boundary tested in sizeZero

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }

        assertEquals(16,list.size());

        // Testing upper right boundary (index 16)
        list.add('P');
        assertEquals(17,list.size());
    }

    @Test
    void sizeException(){
        //
    }

    @Test
    void toStringZero(){
        assertEquals("{}", list.toString());
    }

    @Test
    void toStringOne(){
        list.add(null);
        assertEquals("{null}",list.toString());
    }

    @Test
    void toStringMany(){
        list.add('A');
        list.add('B');
        list.add('B');
        list.add(null);
        list.add(null);
        list.add('A');
        assertEquals("{A, B, B, null, null, A}", list.toString());
    }

    @Test
    void toStringBoundary(){
        // lower right boundary tested in toStringZero

        // Testing upper left boundary (index 15)
        for (int i = 0; i <= 15; i++){
            list.add((char)(i+65));
        }
        assertEquals("{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P}",list.toString());

        // Testing upper right boundary (index 16)
        list.add('Q');
        assertEquals("{A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q}",list.toString());
    }

    @Test
    void toStringException(){
        //
    }

    @Test
    void testAutoExpansionElement(){
        for (int i = 0; i<456;i++){
            list.add((char)i);
        }
    }

    @Test
    void testAutoExpansionIndex(){
        for (int i = 0; i<456;i++){
            list.add(i,(char)i);
        }
    }
}
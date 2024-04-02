import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.deque.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

public class DoubleLinkedListTest {

    @Nested
    @DisplayName("Test para el método prepend()")
    class PrependTests {
        
        @Test
        @DisplayName("Debe agregar un elemento al inicio de la cola vacía")
        void testAddElementToEmptyQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.prepend("Hola");

            int expectedSize = 1;
            int obtainedSize = queue.size();
            assertEquals(expectedSize, obtainedSize);

            String expectedValue = "Hola";
            String obtainedValue1 = queue.first();
            String obtainedValue2 = queue.last();
            assertEquals(expectedValue, obtainedValue1);
            assertEquals(expectedValue, obtainedValue2);
        }

        @Test
        @DisplayName("Debe agregar un elemento al inicio de la cola no vacía")
        void testAddElementToNonEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.prepend(3);
            queue.prepend(2);
            queue.prepend(1);

            int expectedSize = 3;
            int obtainedSize = queue.size();
            assertEquals(expectedSize, obtainedSize);

            int obtainedValue1 = queue.first();
            int expectedValue1 = 1;
            int obtainedValue2 = queue.last();
            int expectedValue2 = 3;
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
        }
      
    }

    @Nested
    @DisplayName("Tests para el método append()")
    class AppendTests {
        @Test
        @DisplayName("Debe agregar un elemento al final de la cola vacía")
        void testAddElementToEmptyQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hola");

            int expectedSize = 1;
            int obtainedSize = queue.size();
            assertEquals(expectedSize, obtainedSize);

            String expectedValue = "Hola";
            String obtainedValue1 = queue.first();
            String obtainedValue2 = queue.last();
            assertEquals(expectedValue, obtainedValue1);
            assertEquals(expectedValue, obtainedValue2);
        }

        @Test
        @DisplayName("Debe agregar un elemento al final de la cola no vacía")
        void testAddElementToNonEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            int expectedSize = 3;
            int obtainedSize = queue.size();
            assertEquals(expectedSize, obtainedSize);

            int obtainedValue1 = queue.first();
            int expectedValue1 = 1;
            int obtainedValue2 = queue.last();
            int expectedValue2 = 3;
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteFirst()")
    class DeleteFirstTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void testThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::deleteFirst);
        }

        @Test
        @DisplayName("Debe eliminar el primer elemento de una cola de un solo elemento")
        void testDeleteElementFromSingleElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);            
            int expectedSize1 = 1;
            int obtainedSize1 = queue.size();
            assertEquals(expectedSize1, obtainedSize1);
            
            queue.deleteFirst();

            int expectedSize2 = 0;
            int obtainedSize2 = queue.size();
            assertEquals(expectedSize2, obtainedSize2);
            
            boolean obtainedValueEmpty = queue.size()==0;
            assertTrue(obtainedValueEmpty);
        } 

        


        @Test
        @DisplayName("Debe eliminar el primer elemento de una cola con múltiples elementos")
        void testDeleteFirstElementFromMultiElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            queue.deleteFirst();

            int expectedSize = 2;
            int obtainedSize = queue.size();
            assertEquals(expectedSize, obtainedSize);
            
            int obtainedValue1 = queue.first();
            int expectedValue1 = 2;
            int obtainedValue2 = queue.last();
            int expectedValue2 = 3;
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteLast()")
    class DeleteLastTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void testThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::deleteLast);
        }

         @Test
        @DisplayName("Debe eliminar el último elemento de una cola de un solo elemento")
        void testDeleteElementFromSingleElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);            
            
            int expectedSize1 = 1;
            int obtainedSize1 = queue.size();
            assertEquals(expectedSize1, obtainedSize1);
            
            queue.deleteLast();
            
            int expectedSize2 = 0;
            int obtainedSize2 = queue.size();
            assertEquals(expectedSize2, obtainedSize2);

            boolean obtainedValueEmpty = queue.size()==0;
            assertTrue(obtainedValueEmpty);
        } 

        @Test
        @DisplayName("Debe eliminar el último elemento de una cola con múltiples elementos")
        void testDeleteLastElementFromMultiElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            queue.deleteLast();

            int expectedSize = 2;
            int obtainedSize = queue.size();
            assertEquals(expectedSize, obtainedSize);
            
            int obtainedValue1 = queue.first();
            int expectedValue1 = 1;
            int obtainedValue2 = queue.last();
            int expectedValue2 = 2;
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
        }
    }

    @Nested
    @DisplayName("Tests para el método first()")
    class FirstTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void testThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::first);
        }

        @Test
        @DisplayName("Debe devolver el primer elemento de la cola")
        void testReturnFirstElement() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hola");
            queue.append("Mundo");
            
            String expectedValue = "Hola";
            String obtainedValue = queue.first();
            assertEquals(expectedValue, obtainedValue);
        }
    }

    @Nested
    @DisplayName("Tests para el método last()")
    class LastTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void testThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::last);
        }

        @Test
        @DisplayName("Debe devolver el último elemento de la cola")
        void testReturnLastElement() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            int expectedValue = 3;
            int obtainedValue = queue.last();
            assertEquals(expectedValue, obtainedValue);
        }
    }

    @Nested
    @DisplayName("Tests para el método size()")
    class SizeTests {
        @Test
        @DisplayName("Debe devolver 0 para una cola vacía")
        void testReturnZeroForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();
            
            int expectedValue = 0;
            int obtainedValue = queue.size();
            assertEquals(expectedValue, obtainedValue);
        }

        @Test
        @DisplayName("Debe devolver el tamaño correcto para una cola no vacía")
        void testReturnCorrectSizeForNonEmptyQueue() {
            DoubleLinkedList<Double> queue = new DoubleLinkedList<>();
            queue.append(1.0);
            queue.append(2.0);
            queue.prepend(3.0);

            int expectedValue = 3;
            int obtainedValue = queue.size();
            assertEquals(expectedValue, obtainedValue);
        }
    }

    @Nested
    @DisplayName("Tests para el método get")
    class GetTests{
        @Test 
        @DisplayName("Debe lanzar una excepcion (indice negativo)")
        void testThrowsException(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);
            assertThrows(IndexOutOfBoundsException.class, ()->queue.get(-1));            
        }
        @Test 
        @DisplayName("Debe lanzar una excepcion (indice inexistente)")
        void testThrowsException2(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);
            assertThrows(IndexOutOfBoundsException.class, ()->queue.get(5));            
        }
        @Test 
        @DisplayName("Debe devolver el elemento correcto empleando el metodo get")
        void testReturnCorrectElement(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            int expectedValue = 2;
            int obtainedValue = queue.get(1);
            assertEquals(expectedValue, obtainedValue);
        }
    }

    @Nested
    @DisplayName("Tests para el método contains")
    class ContainsTests{
        @Test
        @DisplayName("Tests para el método contains de un elemento que sí existe en la lista")
        void testContainsResultIsTrue(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            boolean obtainedResult = queue.contains(2);
            assertTrue(obtainedResult);
        }
        @Test
        @DisplayName("Tests para el método contains de un elemento que no existe en la lista")
        void testContainsResultIsFalse(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            boolean obtainedResult = queue.contains(4);
            assertFalse(obtainedResult);
        }
    }

    @Nested
    @DisplayName("Tests para el método remove")
    class RemoveTests{
        @Test
        @DisplayName("Tests para el método remove de un elemento que no existe en la lista de un elemento")
        void testRemoveExistingElementUni(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);            

            int sizeBeforeRemove = queue.size();
            queue.remove(4);
            int sizeAfterRemove = queue.size();

            assertEquals(sizeAfterRemove, sizeBeforeRemove);
        }
        @Test
        @DisplayName("Tests para el método remove de un elemento que no existe en la lista de un elemento")
        void testRemoveNonExistingElementUni(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);            

            int sizeBeforeRemove = queue.size();
            queue.remove(1);
            int sizeAfterRemove = queue.size();

            assertNotEquals(sizeAfterRemove, sizeBeforeRemove);
        }
        @Test
        @DisplayName("Tests para el método remove de un elemento que no existe en la lista múltiple")
        void testRemoveNonExistingElementMulti(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            int sizeBeforeRemove = queue.size();
            queue.remove(4);
            int sizeAfterRemove = queue.size();

            assertEquals(sizeAfterRemove, sizeBeforeRemove);
        }
        @Test
        @DisplayName("Tests para el método remove de un elemento que sí existe en la lista múltiple")
        void testRemoveExistingElementMulti(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            int sizeBeforeRemove = queue.size();
            queue.remove(2);
            int sizeAfterRemove = queue.size();

            assertNotEquals(sizeAfterRemove, sizeBeforeRemove);
        }
        @Test
        @DisplayName("Tests para el método remove de un elemento que sí existe en la lista múltiple")
        void testRemoveExistingLastElementMulti(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            int sizeBeforeRemove = queue.size();
            queue.remove(3);
            int sizeAfterRemove = queue.size();

            assertNotEquals(sizeAfterRemove, sizeBeforeRemove);
        }
    }

    @Nested 
    @DisplayName("Tests para el metodo sort")
    class SortTests{
        @Test
        @DisplayName("Tests para el metodo sort con unico elemento en la lista")
        void testSortSingleElementList(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
                        
            queue.sort(Comparator.naturalOrder());

            int obtainedValue = queue.get(0);
            int expectedValue = 1;
            
            assertEquals(expectedValue, obtainedValue);
            
        }
        @Test
        @DisplayName("Tests para el metodo sort con varios elementos en la lista que ya esta ordenada")
        void testSortMultiElementListTrue(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);
            
            queue.sort(Comparator.naturalOrder());

            int obtainedValue1 = queue.get(0);
            int expectedValue1 = 1;
            int obtainedValue2 = queue.get(1);
            int expectedValue2 = 2;
            int obtainedValue3 = queue.get(2);
            int expectedValue3 = 3;
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
            assertEquals(expectedValue3, obtainedValue3);
        }
        @Test
        @DisplayName("Tests para el metodo sort con varios elementos en la lista que no esta ordenada")
        void testSortMultiElementListFalse(){
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(3);
            queue.append(2);
            queue.append(1);
                        
            queue.sort(Comparator.naturalOrder());

            int obtainedValue1 = queue.get(0);
            int expectedValue1 = 1;
            int obtainedValue2 = queue.get(1);
            int expectedValue2 = 2;
            int obtainedValue3 = queue.get(2);
            int expectedValue3 = 3;
            assertEquals(expectedValue1, obtainedValue1);
            assertEquals(expectedValue2, obtainedValue2);
            assertEquals(expectedValue3, obtainedValue3);
        }
    }
}
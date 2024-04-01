import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.deque.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase DoubleLinkedList
 * Guía: https://junit.org/junit5/docs/current/user-guide/
 */

public class DoubleLinkedListTest {

    @Nested
    @DisplayName("Test para el método prepend()")
    class PrependTests {
        @Test
        @DisplayName("Debe agregar un elemento al inicio de la cola vacía")
        void shouldAddElementToEmptyQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.prepend("Hola");
            assertEquals(1, queue.size());
            assertEquals("Hola", queue.first());
            assertEquals("Hola", queue.last());
        }

      /*   @Test
        @DisplayName("Debe agregar un elemento al inicio de la cola no vacía")
        void shouldAddElementToNonEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.prepend(3);
            assertEquals(3, queue.size());
            assertEquals(3, queue.first().intValue());
            assertEquals(1, queue.last().intValue());
        } */
    }

    @Nested
    @DisplayName("Tests para el método append()")
    class AppendTests {
        @Test
        @DisplayName("Debe agregar un elemento al final de la cola vacía")
        void shouldAddElementToEmptyQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hola");
            assertEquals(1, queue.size());
            assertEquals("Hola", queue.first());
            assertEquals("Hola", queue.last());
        }

        @Test
        @DisplayName("Debe agregar un elemento al final de la cola no vacía")
        void shouldAddElementToNonEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);
            assertEquals(3, queue.size());
            assertEquals(1, queue.first().intValue());
            assertEquals(3, queue.last().intValue());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteFirst()")
    class DeleteFirstTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::deleteFirst);
        }

      /*   @Test
        @DisplayName("Debe eliminar el primer elemento de una cola de un solo elemento")
        void shouldDeleteElementFromSingleElementQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hola");
            queue.deleteFirst();

            assertTrue(queue.isEmpty());
            assertNull(queue.first());
            assertNull(queue.last());
        } */

        @Test
        @DisplayName("Debe eliminar el primer elemento de una cola con múltiples elementos")
        void shouldDeleteFirstElementFromMultiElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            queue.deleteFirst();

            assertEquals(2, queue.size());
            assertEquals(2, queue.first().intValue());
            assertEquals(3, queue.last().intValue());
        }
    }

    @Nested
    @DisplayName("Tests para el método deleteLast()")
    class DeleteLastTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::deleteLast);
        }

       /*  @Test
        @DisplayName("Debe eliminar el último elemento de una cola de un solo elemento")
        void shouldDeleteElementFromSingleElementQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hola");
            queue.deleteLast();

            assertTrue(queue.isEmpty());
            assertNull(queue.first());
            assertNull(queue.last());
        } */

        @Test
        @DisplayName("Debe eliminar el último elemento de una cola con múltiples elementos")
        void shouldDeleteLastElementFromMultiElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            queue.deleteLast();

            assertEquals(2, queue.size());
            assertEquals(1, queue.first().intValue());
            assertEquals(2, queue.last().intValue());
        }
    }

    @Nested
    @DisplayName("Tests para el método first()")
    class FirstTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::first);
        }

        @Test
        @DisplayName("Debe devolver el primer elemento de la cola")
        void shouldReturnFirstElement() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hola");
            queue.append("Mundo");

            assertEquals("Hola", queue.first());
        }
    }

    @Nested
    @DisplayName("Tests para el método last()")
    class LastTests {
        @Test
        @DisplayName("Debe lanzar excepción si la cola está vacía")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::last);
        }

        @Test
        @DisplayName("Debe devolver el último elemento de la cola")
        void shouldReturnLastElement() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            assertEquals(3, queue.last().intValue());
        }
    }

    @Nested
    @DisplayName("Tests para el método size()")
    class SizeTests {
        @Test
        @DisplayName("Debe devolver 0 para una cola vacía")
        void shouldReturnZeroForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Debe devolver el tamaño correcto para una cola no vacía")
        void shouldReturnCorrectSizeForNonEmptyQueue() {
            DoubleLinkedList<Double> queue = new DoubleLinkedList<>();
            queue.append(1.0);
            queue.append(2.0);
            queue.prepend(3.0);

            assertEquals(3, queue.size());
        }
    }

    @Nested
    @DisplayName("Tests para el método reverseElements()")
    class ReverseElementsTests {
        @Test
        @DisplayName("No debe hacer nada si la cola está vacía")
        void shouldDoNothingForEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.reverseElements();

            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Debe invertir el orden de los elementos en la cola")
        void shouldReverseElementOrder() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("A");
            queue.append("B");
            queue.append("C");

            queue.reverseElements();

            assertEquals("C", queue.first());
            assertEquals("A", queue.last());
        }
    }

    @Nested
    @DisplayName("Tests para el método removeDuplicates()")
    class RemoveDuplicatesTests {
        @Test
        @DisplayName("Debe devolver una cola vacía si la cola original está vacía")
        void shouldReturnEmptyQueueForEmptyOriginalQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();
            DoubleLinkedList<Object> newQueue = queue.removeDuplicates();

            assertTrue(newQueue.isEmpty());
        }
    }
}
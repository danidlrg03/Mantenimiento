import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.deque.*;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {

    @Nested
    @DisplayName("Tests for prepend() method")
    class PrependTests {

        @Test
        @DisplayName("Should add an element to the beginning of an empty queue")
        void shouldAddElementToEmptyQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            String elementToAdd = "Hello";

            queue.prepend(elementToAdd);

            assertEquals(1, queue.size());
            assertEquals(elementToAdd, queue.first());
            assertEquals(elementToAdd, queue.last());
        }

        @Test
        @DisplayName("Should add an element to the beginning of a non-empty queue")
        void shouldAddElementToNonEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(2); // The queue already has one element before the test
            int elementToAdd = 1;

            queue.prepend(elementToAdd);

            assertEquals(2, queue.size());
            assertEquals(elementToAdd, queue.first());
        }
    }

    @Nested
    @DisplayName("Tests for append() method")
    class AppendTests {
        @Test
        @DisplayName("Should add an element to the end of an empty queue")
        void shouldAddElementToEmptyQueue() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            String elementToAdd = "Hello";

            queue.append(elementToAdd);

            assertEquals(1, queue.size());
            assertEquals(elementToAdd, queue.first());
            assertEquals(elementToAdd, queue.last());
        }

        @Test
        @DisplayName("Should add an element to the end of a non-empty queue")
        void shouldAddElementToNonEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1); // The queue already has one element before the test
            int elementToAdd = 2;

            queue.append(elementToAdd);

            assertEquals(2, queue.size());
            assertEquals(elementToAdd, queue.last());
        }
    }

    @Nested
    @DisplayName("Tests for deleteFirst() method")
    class DeleteFirstTests {
        @Test
        @DisplayName("Should throw exception if the queue is empty")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::deleteFirst);
        }

        @Test
        @DisplayName("Should delete the first element of a single-element queue")
        void shouldDeleteElementFromSingleElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);

            queue.deleteFirst();

            assertEquals(0, queue.size());
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Should delete the first element of a multi-element queue")
        void shouldDeleteFirstElementFromMultiElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            queue.deleteFirst();

            assertEquals(2, queue.size());
            assertEquals(2, queue.first());
            assertEquals(3, queue.last());
        }
    }

    @Nested
    @DisplayName("Tests for deleteLast() method")
    class DeleteLastTests {
        @Test
        @DisplayName("Should throw exception if the queue is empty")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::deleteLast);
        }

        @Test
        @DisplayName("Should delete the last element of a single-element queue")
        void shouldDeleteElementFromSingleElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);

            queue.deleteLast();

            assertEquals(0, queue.size());
            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Should delete the last element of a multi-element queue")
        void shouldDeleteLastElementFromMultiElementQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            queue.deleteLast();

            assertEquals(2, queue.size());
            assertEquals(1, queue.first());
            assertEquals(2, queue.last());
        }
    }

    @Nested
    @DisplayName("Tests for first() method")
    class FirstTests {
        @Test
        @DisplayName("Should throw exception if the queue is empty")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::first);
        }

        @Test
        @DisplayName("Should return the first element of the queue")
        void shouldReturnFirstElement() {
            DoubleLinkedList<String> queue = new DoubleLinkedList<>();
            queue.append("Hello");
            queue.append("World");

            assertEquals("Hello", queue.first());
        }
    }

    @Nested
    @DisplayName("Tests for last() method")
    class LastTests {
        @Test
        @DisplayName("Should throw exception if the queue is empty")
        void shouldThrowExceptionForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertThrows(DoubleLinkedQueueException.class, queue::last);
        }

        @Test
        @DisplayName("Should return the last element of the queue")
        void shouldReturnLastElement() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            queue.append(3);

            assertEquals(3, queue.last().intValue());
        }
    }

    @Nested
    @DisplayName("Tests for size() method")
    class SizeTests {
        @Test
        @DisplayName("Should return 0 for an empty queue")
        void shouldReturnZeroForEmptyQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();

            assertEquals(0, queue.size());
        }

        @Test
        @DisplayName("Should return the correct size for a non-empty queue")
        void shouldReturnCorrectSizeForNonEmptyQueue() {
            DoubleLinkedList<Double> queue = new DoubleLinkedList<>();
            queue.append(1.0);
            queue.append(2.0);
            queue.prepend(3.0);

            assertEquals(3, queue.size());
        }
    }

    @Nested
    @DisplayName("Tests for reverseElements() method")
    class ReverseElementsTests {
        @Test
        @DisplayName("Should do nothing if the queue is empty")
        void shouldDoNothingForEmptyQueue() {
            DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
            queue.reverseElements();

            assertTrue(queue.isEmpty());
        }

        @Test
        @DisplayName("Should reverse the order of elements in the queue")
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
    @DisplayName("Tests for removeDuplicates() method")
    class RemoveDuplicatesTests {
        @Test
        @DisplayName("Should return an empty queue if the original queue is empty")
        void shouldReturnEmptyQueueForEmptyOriginalQueue() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();
            DoubleLinkedList<Object> newQueue = queue.removeDuplicates();
            assertTrue(newQueue.isEmpty());
        }

        @Test
        @DisplayName("Should return a queue with the same size if no duplicates")
        void testRemoveDuplicatesOriginal() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(2);
            DoubleLinkedList<Object> newQueue = queue.removeDuplicates();
            assertEquals(queue.size(), newQueue.size());
        }

        @Test
        @DisplayName("Should return a smaller queue if there were duplicates")
        void testRemoveDuplicatesNotOriginal() {
            DoubleLinkedList<Object> queue = new DoubleLinkedList<>();
            queue.append(1);
            queue.append(1);
            DoubleLinkedList<Object> newQueue = queue.removeDuplicates();
            assertNotEquals(queue.size(), newQueue.size());
        }
    }
}

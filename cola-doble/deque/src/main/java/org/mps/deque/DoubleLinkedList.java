package org.mps.deque;

import java.util.Comparator;

public class DoubleLinkedList<T> implements DoubleLinkedQueue<T> {
    private LinkedNode<T> first;
    private LinkedNode<T> last;
    private int size;

    public DoubleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, null, first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrevious(newNode);
        }
        first = newNode;
        size++;
    }

    @Override
    public void append(T value) {
        LinkedNode<T> newNode = new LinkedNode<>(value, last, null);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    @Override
    public void deleteFirst() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Cannot delete from an empty queue");
        }
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }
        size--;
    }

    @Override
    public void deleteLast() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Cannot delete from an empty queue");
        }
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.getPrevious();
            last.setNext(null);
        }
        size--;
    }

    @Override
    public T first() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Cannot retrieve from an empty queue");
        }
        return first.getItem();
    }

    @Override
    public T last() {
        if (isEmpty()) {
            throw new DoubleLinkedQueueException("Cannot retrieve from an empty queue");
        }
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public void reverseElements() {
        if (isEmpty()) {
            return;
        }

        LinkedNode<T> currentNode = first;
        LinkedNode<T> temp = null;

        while (currentNode != null) {
            temp = currentNode.getPrevious();
            currentNode.setPrevious(currentNode.getNext());
            currentNode.setNext(temp);
            currentNode = currentNode.getPrevious();
        }

        temp = first;
        first = last;
        last = temp;
    }
    
    public DoubleLinkedList<T> removeDuplicates() {
        DoubleLinkedList<T> newQueue = new DoubleLinkedList<>();
        LinkedNode<T> currentNode = first;

        while (currentNode != null) {
            if (!newQueue.contains(currentNode.getItem())) {
                newQueue.append(currentNode.getItem());
            }
            currentNode = currentNode.getNext();
        }

        return newQueue;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        LinkedNode<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getItem();
    }

    @Override
    public boolean contains(T value) {
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) {
        LinkedNode<T> current = first;
        while (current != null) {
            if (current.getItem().equals(value)) {
                if (current == first) {
                    deleteFirst();
                } else if (current == last) {
                    deleteLast();
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    size--;
                }
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if (size > 1) {
            boolean wasChanged;
            do {
                LinkedNode<T> current = first;
                LinkedNode<T> previous = null;
                LinkedNode<T> next = first.getNext();
                wasChanged = false;

                while (next != null) {
                    if (comparator.compare(current.getItem(), next.getItem()) > 0) {
                        wasChanged = true;
                        if (previous != null) {
                            LinkedNode<T> sig = next.getNext();

                            previous.setNext(next);
                            next.setNext(current);
                            current.setNext(sig);
                        } else {
                            LinkedNode<T> sig = next.getNext();

                            first = next;
                            next.setNext(current);
                            current.setNext(sig);
                        }

                        previous = next;
                        next = current.getNext();
                    } else {
                        previous = current;
                        current = next;
                        next = next.getNext();
                    }
                }
            } while (wasChanged);
        }
    }
}
package deque;

import Interface_Form.Queue;

public class LinkedListDeque<E> implements Queue <E> {

    private dNode<E> head;
    private dNode<E> tail;
    private int size;

    public LinkedListDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean offerFirst(E value) {
        dNode<E> newNode = new dNode<>(value);
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if(head.next == null) {
            tail = head;
        }

        return true;
    }

    @Override
    public boolean offer(E value) {


        if (size == 0) {
            offerFirst(value);
            return true;
        }

        dNode<E> newNode = new dNode<>(value);

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;

        size++;

        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    private E pollFirst() {

        if (size == 0) {
            return null;
        }

        E element = head.data;

        dNode<E> nextNode = head.next;

        head.data = null;
        head.prev = null;
        head.next = null;

        head =nextNode;

        size--;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        E element = tail.data;

        dNode<E> prevNode = tail.prev;

        tail.data = null;
        tail.next = null;
        tail.prev = null;

        tail = prevNode;
        size--;
        if(size == 0) {
            head = null;
        }

        return element;

    }


    @Override
    public E peek() {
        return peekFirst();
    }

    private E peekFirst() {
        if(size == 0) {
            return null;
        }
        return head.data;
    }

    public E peekLast() {
        if (size == 0) {
            return null;
        }

        return tail.data;
    }
}

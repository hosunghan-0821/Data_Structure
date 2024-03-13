package queue;

import Interface_Form.Queue;

public class LinkedListQueue<E> implements Queue<E> {

    private sNode<E> head;
    private sNode<E> tail;

    private int size;

    public LinkedListQueue () {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean offer(E value) {

        sNode<E> newNode = new sNode<E>(value);

        if (size == 0) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;

        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        E element = head.data;

        sNode<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        return element;
    }



    @Override
    public E peek() {

        if(size == 0) {
            return null;
        }
        return head.data;
    }
}

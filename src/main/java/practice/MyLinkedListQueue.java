package practice;

import queue.sNode;

/*
 * 자료구조 연습
 * 조건 0. (추가(offer), 삭제(poll), 최상단(peek)) 기능 구현
 * */
public class MyLinkedListQueue<E> {

    private sNode<E> head;
    private sNode<E> tail;

    private int size;

    public MyLinkedListQueue() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public boolean offer(E value) {

        sNode<E> newNode = new sNode<E>(value);
        // 비어있을 경우
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;

        return true;
    }


    public E poll() {
        if (size == 0) {
            return null;
        }
        E element = head.data;

        head = head.next;
        size--;

        return element;
    }

    public E peek() {

        if (size == 0) {
            return null;
        }

        return head.data;
    }
}

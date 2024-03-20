package practice;


/*
 * 조건1. 노드 구현
 * 조건2. 생성자 구현
 * 조건3. 삽입,삭제, 앞뒤 값 조회, 값 존재여부 확인
 * */
public class MyLinkedListDeque<E> {

    private dNode<E> head;
    private dNode<E> tail;

    private int size;

    public MyLinkedListDeque() {
        this.size = 0;
        head = null;
        tail = null;
    }


    public boolean offerFirst(E value) {

        dNode<E> newNode = new dNode<E>(value);
        //size = 0 일 때,
        if (size == 0) {

            head = newNode;
            tail = newNode;
            size++;
            return true;
        }

        dNode<E> nextNode = head;
        head = newNode;
        nextNode.prev = newNode;
        newNode.next = nextNode;
        size++;

        return true;
    }

    public boolean offerLast(E value) {

        dNode<E> newNode = new dNode<E>(value);

        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }

        dNode<E> prevNode = tail;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        size++;
        tail = newNode;


        return false;
    }

    public E pollFirst() {

        if (size == 0) {
            return null;
        }

        E element = (E) head.data;

        dNode<E> nextNode = head.next;
        if(nextNode != null) {
            nextNode.prev = null;
            head = nextNode;
        }


        size--;
        return element;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        E element = (E) tail.data;

        dNode<E> prevNode = tail.prev;

        if(prevNode != null) {
            prevNode.next = null;
            tail = prevNode;
        }



        size--;
        return element;
    }

    public E peekFirst(){
        if (size == 0) {
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

    public static void main(String[] args) {

    }

}

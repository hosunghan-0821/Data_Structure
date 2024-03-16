package practice;

import Interface_Form.List;
import LiinkedList.dNode;

import java.util.NoSuchElementException;


/*
 * 조건1 : 맴버변수 생성자 구현
 * 조건2 : 삽입, 삭제, 검색, Get,Set 구현
 *
 *
 * */
public class MyDoubleLinkedList<E> {

    private dNode<E> head;
    private dNode<E> tail;
    private int size;

    public MyDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean add(E value) {

        dNode<E> newNode = new dNode<>(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }

        dNode<E> prevNode = tail;

        newNode.prev = prevNode;
        prevNode.next = newNode;
        tail = newNode;
        size++;

        return true;
    }

    public boolean addFirst(E value) {

        dNode<E> newNode = new dNode<>(value);

        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return true;
        }

        dNode<E> nextNode = head;

        head = newNode;
        newNode.next = nextNode;
        newNode.prev=newNode;
        size++;

        return true;
    }
    public void add(int index, E value) {

        if (index <0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            add(value);
            return;
        }

        dNode<E> prevNode = search(index - 1);
        dNode<E> nextNode = prevNode.next;

        dNode<E> newNode = new dNode<E>(value);


        prevNode.next = newNode;
        newNode.prev = prevNode;

        newNode.next = nextNode;
        nextNode.prev = newNode;
        size++;

    }
    private dNode<E> search(int index) {
        if (index <0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index + 1 > size /2) {
            dNode<E> x = tail;
            for (int i = size -1; i >index; --i) {
                x= x.prev;
            }
            return x;
        } else {
            dNode<E> x = head;
            for (int i = 0; i <index; ++i) {
                x=x.next;
            }
            return x;
        }
    }

    public E remove() {
        if (size == 0){
            throw new NoSuchElementException();
        }

        E element = tail.data;
        tail = tail.prev;
        size--;
        return element;
    }

    public E remove(int index) {
        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return remove();
        }

        dNode<E> removedNode = search(index);
        E element = removedNode.data;
        removedNode.prev.next = removedNode.next;
        removedNode.next.prev = removedNode.prev;
        size--;

        return element;

    }

    public E removeFirst() {
        if (size == 0){
            throw new NoSuchElementException();
        }

        E element = head.data;


        if (head.next == null) {
            tail = null;
        } else {
            head = head.next;
        }

        size--;
        return element;
    }

    public E get(int index) {
        return search(index).data;
    }

    public boolean set(int index, E value) {
        search(index).data = value;
        return true;
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> linkedList = new MyDoubleLinkedList<>();

        linkedList.add(1);
        linkedList.add(2);

        linkedList.add(1,3);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));

        linkedList.remove(1);
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));

        linkedList.remove();
        System.out.println(linkedList.get(0));

    }

}

package LiinkedList;

import Interface_Form.List;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E>, Cloneable {

    private sNode<E> head; //노드의 첫 부분
    private sNode<E> tail; //노드의 마지막 부분
    private int size; // 요소개수


    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(E value) {
        sNode<E> newNode = new sNode<E>(value);
        newNode.next = head;
        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(E value) {

        sNode<E> newNode = new sNode<E>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;


    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }

        sNode<E> newNode = new sNode<E>(value);
        sNode<E> prevNode = search(index - 1);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        size++;


    }

    /*가장 앞에 있는 요소를 제거하는 메소드*/

    public E remove() {
        sNode<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        E element = headNode.data;

        sNode<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        if (index == 0) {
            return remove();
        }

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        sNode<E> prevNode = search(index - 1);
        sNode<E> removeNode = prevNode.next;
        sNode<E> nextNode = removeNode.next;

        E element = removeNode.data;

        prevNode.next = nextNode;

        if (prevNode.next == null) {
            tail = prevNode;
        }

        removeNode.next = null;
        removeNode.data = null;
        size--;
        return element;

    }

    @Override
    public boolean remove(Object value) {

        sNode<E> prevNode = head;
        boolean hasValue = false;
        sNode<E> x = head;

        //value 와 일치하는 노드를 찾느낟.
        for (; x != null; x = x.next) {

            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }

        if (x.equals(head)) {
            remove();
            return true;
        } else {
            prevNode.next = x.next;

            if (prevNode.next == null) {
                tail = prevNode;
            }

            x.data = null;
            x.next = null;
            size--;
            return true;
        }
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        sNode<E> replaceNode = search(index);
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;
        for (sNode<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        for (sNode<E> x = head; x != null; ) {
            sNode<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;

        }
        head = tail = null;
        size = 0;
    }

    private sNode<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        sNode<E> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        SLinkedList<? super E> clone = (SLinkedList<? super E>) super.clone();
        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (sNode<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }
        return clone;
    }

}

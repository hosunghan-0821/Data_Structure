package LiinkedList;

import Interface_Form.List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class DLinkedList<E> implements List<E>, Cloneable {

    private dNode<E> head, tail;
    private int size;

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DLinkedList(dNode<E> head, dNode<E> tail, int size) {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private dNode<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index > size / 2) {
            dNode<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        } else {
            dNode<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }

    public void addFirst(E value) {
        dNode<E> newNode = new dNode<E>(value);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }

        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }


    }


    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        dNode<E> newNode = new dNode<E>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size - 1) {
            addLast(value);
            return;
        }
        //추가 이전 노드
        dNode<E> prev_Node = search(index - 1);
        //추가 이후 노드
        dNode<E> next_Node = prev_Node.next;
        //추가 노드
        dNode<E> newNode = new dNode<E>(value);

        //링크 끊기
        prev_Node.next = null;
        next_Node.prev = null;

        prev_Node.next = newNode;

        newNode.prev = prev_Node;
        newNode.next = next_Node;

        next_Node.prev = newNode;

        size++;

    }

    public E remove() {
        dNode<E> headNode = head;
        if (headNode == null) {
            throw new NoSuchElementException();
        }

        E element = headNode.data;
        dNode<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        if (nextNode != null) {
            nextNode.prev = null;
        }
        head = nextNode;
        size--;

        if (size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {


        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            E element = head.data;
            remove();
            return element;
        }

        dNode<E> prevNode = search(index - 1);
        dNode<E> removeNode = prevNode.next;
        dNode<E> nextNode = removeNode.next;

        E element = prevNode.next.data;

        prevNode.next = null;
        removeNode.next = null;
        removeNode.prev = null;
        removeNode.data = null;

        if (nextNode != null) {
            nextNode.prev = null;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;

        } else {
            tail = prevNode;
        }
        size--;

        return element;


    }

    @Override
    public boolean remove(Object value) {

        dNode<E> prevNode = head;
        dNode<E> x = head;

        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                break;
            }
            prevNode = x;
        }

        if (x == null) {
            return false;
        }

        if (x.equals(head)) {
            remove();
            return true;
        } else {
            dNode<E> nextNode = x.next;
            prevNode.next = null;
            x.data = null;
            x.next = null;
            x.prev = null;
            if (nextNode != null) {

                nextNode.prev = null;
                prevNode.next = nextNode;
            } else {
                tail = prevNode;
            }
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
        dNode<E> replaceNode = search(index);

        replaceNode.data = value;

    }

    @Override
    public boolean contains(Object item) {


        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (dNode<E> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {

        int index = size;

        for (dNode<E> x = tail; x != null; x = x.prev) {

            index--;
            if (o.equals(x.data)) {
                return index;
            }

        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

        for (dNode<E> x = head; x != null; ) {
            dNode<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;

        }
        head = tail = null;
        size = 0;

    }

    @Override
    public DLinkedList<E> clone() {
        try {
            DLinkedList clone = (DLinkedList) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            clone.head = null;
            clone.tail = null;
            clone.size = 0;

            for (dNode<E> x = head; x != null; x = x.next) {
                clone.addLast(x.data);
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (dNode<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {

        if (a.length < size) {
            // Array.newInstance(컴포넌트 타입, 생성할 크기)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0 ;
        Object[] result = a;
        for(dNode<E> x=head ; x !=null; x=x.next){
            result[i++]=x.data;
        }
        return a;
    }


    public void sort(){

        sort(null);
    }

    @SuppressWarnings({"unchecked","rawtypes"})
    public void sort(Comparator<? super E> c){
        Object[] a = this.toArray();
        Arrays.sort(a,(Comparator) c);

        int i = 0 ;
        for(dNode<E> x = head ; x!=null; x=x.next , i++){
            x.data = (E) a[i];
        }
    }
}

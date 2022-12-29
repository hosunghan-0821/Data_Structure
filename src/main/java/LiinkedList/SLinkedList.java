package LiinkedList;

import Interface_Form.List;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E>,Cloneable {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }




    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value); //새 노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
        head = newNode; //head가 가리키는 노드를 새 노드 변경
        size++;

        /*
         * 다음에 가리킬 노드가 없는 경우
         * 데이터가 한 개 밖에 없으므로, 새 노드는 처음 시작노드이다, 마지막 노드
         * tail = head 이다.
         * */

        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if (size == 0) { //첫 노드일 경우, addFirst 로 추가
            addFirst(value);
            return;
        }

        /*
         * 마지막 노드 tail 의 다음 노드가 새노드를 가리키도록 하고
         * tail 이 가리키는 노드를 새 노드로 바꿔준다
         * */
        tail.next = newNode;
        tail = newNode;
        size++;

    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return false;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index >= size) {
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

        Node<E> newNode = new Node<>(value);
        Node<E> prevNode = search(index - 1);
        Node<E> nextNode = prevNode.next;
        prevNode.next = newNode;
        newNode.next = nextNode;
        size++;
    }


    public E remove() {

        Node<E> headNode = head;
        if (headNode == null) throw new NoSuchElementException();

        //삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;
        Node<E> nextNode = head.next;

        //head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        //head가 다음 노드를 가리키도록 업데이트
        head = nextNode;
        size--;

        /*
         *  삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail 이었으므로
         * 삭제되면서 tail 도 null 처리 해야함
         * */
        if (size == 0) {
            tail = null;
        }
        return element;
    }

    @Override
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {

            return remove();
        }
        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;
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

        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head; // removedNode

        for (; x != null; x = x.next) {

            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevNode = x;
        }

        //일치요소 없을 경우
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
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            if (value.equals(x.data)) {
                return index;
            }
            index++;
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

        for (Node<E> x = head; x != null; ) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }



    public Object clone() throws CloneNotSupportedException{

        @SuppressWarnings("unchecked")
        SLinkedList<? super E> clone =(SLinkedList<? super E>)  super.clone();
        clone.head=null;
        clone.tail=null;
        clone.size=0;

        for(Node<E> x= head ; x != null ; x=x.next){
            clone.addLast(x.data);
        }
        return clone;
    }
}

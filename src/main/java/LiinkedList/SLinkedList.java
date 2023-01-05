package LiinkedList;

import Interface_Form.List;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E>,Cloneable {

    private sNode<E> head;
    private sNode<E> tail;
    private int size;

    public SLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
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

    public void addFirst(E value) {
        sNode<E> newSNode = new sNode<E>(value); //새 노드 생성
        newSNode.next = head; // 새 노드의 다음 노드로 head 노드를 연결
        head = newSNode; //head가 가리키는 노드를 새 노드 변경
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
        sNode<E> newSNode = new sNode<E>(value);

        if (size == 0) { //첫 노드일 경우, addFirst 로 추가
            addFirst(value);
            return;
        }

        /*
         * 마지막 노드 tail 의 다음 노드가 새노드를 가리키도록 하고
         * tail 이 가리키는 노드를 새 노드로 바꿔준다
         * */
        tail.next = newSNode;
        tail = newSNode;
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

        sNode<E> newSNode = new sNode<>(value);
        sNode<E> prevSNode = search(index - 1);
        sNode<E> nextSNode = prevSNode.next;
        prevSNode.next = newSNode;
        newSNode.next = nextSNode;
        size++;
    }


    public E remove() {

        sNode<E> headSNode = head;
        if (headSNode == null) throw new NoSuchElementException();

        //삭제된 노드를 반환하기 위한 임시 변수
        E element = headSNode.data;
        sNode<E> nextSNode = head.next;

        //head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        //head가 다음 노드를 가리키도록 업데이트
        head = nextSNode;
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
        sNode<E> prevSNode = search(index - 1);
        sNode<E> removeSNode = prevSNode.next;
        sNode<E> nextSNode = removeSNode.next;
        E element = removeSNode.data;
        prevSNode.next = nextSNode;

        if (prevSNode.next == null) {
            tail = prevSNode;
        }

        removeSNode.next = null;
        removeSNode.data = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {

        sNode<E> prevSNode = head;
        boolean hasValue = false;
        sNode<E> x = head; // removedNode

        for (; x != null; x = x.next) {

            if (value.equals(x.data)) {
                hasValue = true;
                break;
            }
            prevSNode = x;
        }

        //일치요소 없을 경우
        if (x == null) {
            return false;
        }
        if (x.equals(head)) {
            remove();
            return true;
        } else {
            prevSNode.next = x.next;

            if (prevSNode.next == null) {
                tail = prevSNode;
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
        sNode<E> replaceSNode = search(index);
        replaceSNode.data = null;
        replaceSNode.data = value;
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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {

        for (sNode<E> x = head; x != null; ) {
            sNode<E> nextSNode = x.next;
            x.data = null;
            x.next = null;
            x = nextSNode;
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

        for(sNode<E> x = head; x != null ; x=x.next){
            clone.addLast(x.data);
        }
        return clone;
    }
}

package deque;

public class dNode <E> {

    E data;
    dNode<E> prev;
    dNode<E> next;

    dNode(E data) {
        this.data = data;
        this.prev =null;
        this.next = null;
    }
}

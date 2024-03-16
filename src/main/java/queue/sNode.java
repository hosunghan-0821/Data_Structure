package queue;

public class sNode<E> {
    public E data;
    public sNode<E> next;

    public sNode(E data) {
        this.data = data;
        this.next = null;
    }
}

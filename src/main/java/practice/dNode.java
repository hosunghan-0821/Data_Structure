package practice;

public class dNode <E>{
    E data;
    dNode<E> prev;
    dNode<E> next;


    public dNode(E value) {
        this.data = value;
        this.prev = null;
        this.next = null;
    }
}

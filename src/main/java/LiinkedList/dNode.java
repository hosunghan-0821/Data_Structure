package LiinkedList;

public class dNode <E>{

    public E data;
    public dNode<E> next;
    public dNode<E> prev;

    public dNode(E data){
        this.data=data;
        this.prev=null;
        this.next=null;

    }

}

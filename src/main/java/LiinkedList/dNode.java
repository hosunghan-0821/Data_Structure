package LiinkedList;

public class dNode <E>{

    E data;
    dNode<E> next;
    dNode<E> prev;

    dNode(E data){
        this.data=data;
        this.prev=null;
        this.next=null;

    }

}

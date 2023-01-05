package LiinkedList;

public class sNode<E>{
    E data;
    sNode<E> next; // 다음 노드 객체를 가리키는 레퍼런스 변수

    sNode(E data){
        this.data = data;
        this.next = null;
    }
}

package practice;


/*
 * 조건1. 생성자 및 동적관리 할 수 있도록
 * 조건2. 삽입(앞,뒤), 삭제(앞,뒤), get,set 구현
 *
 * */
public class MyArrayDeque<E> {
    private static final int DEFAULT_CAPACITY = 3;
    private Object[] deque;
    private int size;
    private int front;
    private int rear;


    public MyArrayDeque() {
        this.deque = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity) {

        Object[] newArray = new Object[newCapacity];

        for (int i = 0, j = front + 1; i < size; i++, j++) {
            newArray[i] = deque[j % deque.length];
        }
        front = 0;
        rear = size;

        deque = null;
        deque = newArray;

    }

    public boolean offerFirst(E value) {

        if ((rear + 1) % deque.length == front) {
            resize(deque.length * 2);
        }

        deque[front] = value;

        size++;
        front = (front - 1 + deque.length) % deque.length;

        return true;
    }

    public boolean offer(E value) {

        if ((rear + 1) % deque.length == front) {
            resize(deque.length * 2);
        }

        deque[rear] = value;
        size++;
        rear = (rear + 1) % deque.length;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E poll() {

        if (size == 0) {
            return null;
        }

        E element = (E) deque[front + 1];

        deque[front + 1] = null;
        front = front + 1;

        size--;

        if (deque.length > DEFAULT_CAPACITY && size <= deque.length / 4) {
            resize(Math.max(deque.length / 2, DEFAULT_CAPACITY));
        }

        return element;

    }

    @SuppressWarnings("unchecked")
    public E pollLast() {

        if (size == 0) {
            return null;
        }

        E element = (E) deque[rear];
        deque[rear] = null;
        rear = (rear - 1 + deque.length) % deque.length;

        size--;

        if (deque.length > DEFAULT_CAPACITY && size <= deque.length / 4) {
            resize(Math.max(deque.length / 2, DEFAULT_CAPACITY));
        }
        return element;
    }



    public int getTotalCapacity() {
        return deque.length;
    }

    public static void main(String[] args) {
        MyArrayDeque<Integer> data = new MyArrayDeque<Integer>();

        data.offerFirst(1);
        System.out.println(data.getTotalCapacity());
        data.offerFirst(2);
        System.out.println(data.getTotalCapacity());
        data.offerFirst(3);
        System.out.println(data.getTotalCapacity());


    }


}

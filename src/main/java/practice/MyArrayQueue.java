package practice;

/*
 *  조건1. 생성자 (Object[]로 구현) , circular_queue 를 개념활용
 *  조건2. 가변적으로 배열크기가 달라지도록 구현
 *  조건3. 데이터 삽입, 삭제, 데이터검색
 * */
public class MyArrayQueue<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] queue;

    private int size;
    private int front;
    private int rear;

    public MyArrayQueue() {
        this.queue = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public boolean offer(E item) {

        assert item != null;

        if (front == (rear + 1) % queue.length) {
            resize(queue.length * 2);
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = item;
        size++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public E poll() {
        if (size == 0) {
            return null;
        }

        front = (front + 1) % queue.length;
        E element = (E) queue[front];
        queue[front] = null;
        size--;

        if (queue.length > DEFAULT_CAPACITY && size < (queue.length / 4)) {
            resize(Math.max(DEFAULT_CAPACITY, queue.length / 2));
        }

        return element;
    }

    @SuppressWarnings("unchecked")
    public E peek() {

        if (size == 0) {
            return null;
        }
        return (E) queue[(front + 1) % queue.length];
    }

    private void resize(int newCapacity) {

        Object[] newArray = new Object[newCapacity];
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = queue[j % queue.length];
        }
        this.queue = newArray;
        front = 0;
        rear = size;

    }

}

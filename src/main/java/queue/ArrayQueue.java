package queue;

import Interface_Form.Queue;

import java.util.LinkedList;

public class ArrayQueue<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 3;
    private Object[] array; // 요소를 담을 배열

    private int size; //요소 개수
    private int front; // 시작인덱스를 가리키는 변수
    private int rear; //마지막 요소의 인덱스를 가리키는 변수


    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    private void resize(int newCapacity) {

        int arrayCapacity = array.length;
        Object[] newArray = new Object[newCapacity];

        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % arrayCapacity];
        }
        this.array = newArray;

        front = 0;
        rear = size;

    }

    @Override
    public boolean offer(E item) {

        // 용적이 가득 찼을 경우
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }
        rear = (rear + 1) % array.length;

        array[rear] = item;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        front = (front + 1) % array.length;

        @SuppressWarnings("unchecked")
        E item = (E) array[front];

        array[front] = null;
        size--;

        if (array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

        return item;
    }

    @Override
    public E peek() {

        if (size == 0) {
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[(front + 1) % array.length];
        return item;
    }

    public boolean contains(Object value) {
        int start = (front + 1) % array.length;

        for (int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
            if (array[idx].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        front = rear = size = 0;
    }

    public int getCapacity(){
        return array.length;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        System.out.println(queue.getCapacity());
        queue.offer(1);
        System.out.println(queue.getCapacity());
        queue.offer(2);
        System.out.println(queue.getCapacity());
        queue.offer(3);
        System.out.println(queue.getCapacity());



    }
}

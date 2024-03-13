package stack;

import Interface_Form.StackInterface;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> implements StackInterface<E>, Cloneable {

    private static final int DEFAULT_CAPACITY = 10;
    public static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public Stack() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Stack<?> clone = (Stack<?>) super.clone();
        System.out.println(clone.array);
        clone.array= Arrays.copyOf(this.array,this.size);


        return clone;
    }

    public Stack(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        //현재 용적 크기
        int arrayCapacity = array.length;

        //용적이 가득찬 경우
        if (size == arrayCapacity) {
            int newSize = arrayCapacity * 2;

            array = Arrays.copyOf(array, newSize);
            return;
        }

        //용적의 절반 미만으료 요소가 차지하고 있을 경우

        if (size < arrayCapacity / 2) {
            int newCapacity = arrayCapacity / 2;

            array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
            return;
        }
    }


    @Override
    public E push(E item) {

        if (size == array.length) {
            resize();
        }

        array[size] = item;
        size++;

        return item;
    }

    @Override
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        E element = (E) array[size - 1];

        array[size - 1] = null;

        size--;
        resize();

        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        return (E) array[size - 1];
    }

    @Override
    public int search(Object value) {

        assert value != null;

        for (int idx = size -1; idx >= 0; idx--) {
            if(array[idx].equals(value)) {
                return size - idx;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i <size; i++) {
            array[i] = null;
        }

        size = 0;
        resize();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        @SuppressWarnings("unchecked")
        Stack<Integer> newStack=(Stack<Integer>) stack.clone();
        newStack.push(2);

        System.out.println(stack.pop());
        System.out.println(newStack.peek());
    }
}

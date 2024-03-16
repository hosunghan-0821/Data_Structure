package practice;


import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * 자료구조 연습
 * 조건 0. (동적생성, 삽입(push), 삭제(pop), 검색(search), get(peek)) 기능 구현
 * 조건 1. 동적할당으로 사이즈가 늘어나고 줄어듬
 * */
public class MyStack<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ARRAY = {};
    private Object[] stack;
    private int size;


    public MyStack() {
        this.stack = EMPTY_ARRAY;
        this.size = 0;
    }

    public MyStack(int capacity) {
        this.size = 0;
        this.stack = new Object[capacity];
    }

    private void resize() {

        // 빈 stack일 경우
        if (Arrays.equals(EMPTY_ARRAY, stack)) {
            this.stack = new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }

        //꽉 찾을 경우
        if (size == stack.length) {
            stack = Arrays.copyOf(stack, stack.length * 2);
        }

        // 빈배열이 2배이상으로 안쓸 경우
        else if (size < stack.length / 2) {
            stack = Arrays.copyOf(stack, Math.max(stack.length / 2, DEFAULT_CAPACITY));
        }
    }

    public E push(E value) {

        assert value != null;

        resize();
        stack[size] = value;
        size++;
        return value;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        // 아무것도 없을 경우
        if (size == 0) {
            throw new EmptyStackException();
        }

        E element = (E) stack[size - 1];
        stack[size - 1] = null;
        size--;
        resize();

        return element;
    }

    @SuppressWarnings("unchecked")
    public E peek() {

        if (size == 0) {
            return null;
        }

        return (E) stack[size - 1];
    }

    public int search(Object value) {
        assert value != null;

        for (int idx = size - 1; idx >= 0; --idx) {
            if (stack[idx].equals(value)) {
                return idx;
            }
        }
        return -1;
    }
}

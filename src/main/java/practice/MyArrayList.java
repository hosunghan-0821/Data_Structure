package practice;


import java.util.Arrays;

/*
 * 자료구조 연습
 * 조건 0. (생성, 삽입, 삭제, 검색, get, set) 기능 구현
 * 조건 1. 동적할당으로 사이즈가 늘어나고 줄어듬
 * */
public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 5;

    private static final Object[] EMPTY_ARRAY = {};
    private Object[] array;
    private int size;

    public MyArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }


    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    private void resize() {

        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용량이 꽉 찾을 때.
        if (array.length == size) {
            array = Arrays.copyOf(array, size * 2);

        }
        // 용량이 1/2도 안쓰고 있을 때.
        else if (size < array.length / 2) {
            int resizeCapacity = Math.max(DEFAULT_CAPACITY, array.length / 2);
            array = Arrays.copyOf(array, resizeCapacity);
        }


    }

    private void addLast(E element) {
        if (size == array.length) {
            resize();
        }
        array[size] = element;
        size++;
    }

    public void add(E element) {
        assert element != null;
        addLast(element);
    }

    public void add(int index, E element) {
        assert element != null;
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == index) {
            addLast(element);
        } else {
            if (size == array.length) {
                resize();
            }
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = element;
            size++;
        }
    }

    public E remove(E value) {
        assert value != null;
        int findIndex = indexOf(value);
        if (findIndex == -1) {
            return null;
        }
        return remove(findIndex);

    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;

        return element;
    }

    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return array.length;
    }

    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

}

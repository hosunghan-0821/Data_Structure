package ArrayList;

import Interface_Form.List;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class ArrayList<E> implements List<E> {

    // 정적변수
    private static final int DEFAULT_CAPACITY = 10; // 최소 용적 크기
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size; //요소 개수

    Object[] array;

    //기본 생성시
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return false;
    }

    public void addLast(E value) {
        if (size == array.length) {
            resize();
        }
        array[size] = value;
        size++;
    }

    public void addFirst(E value) {
        add(0,value);
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            addLast(value);
        }
        else {
            if (size == array.length) {
                resize();
            }

            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];
        array[index] = null;

        for (int i = size - 1; i > index ; --i) {
            array[i - 1] = array[i];
            array[i] = null;
        }

        size--;
        resize();
        return element;
    }

    @Override
    public boolean remove(Object value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        else {
            array[index] = value;
        }

    }

    @Override
    public boolean contains(Object value) {

        if (indexOf(value) >= 0) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        for (i = 0; i< size ; i++) {
            if (array[i].equals(value)) {
                return  i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0 ; i < size ; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }

    private void resize() {
        int array_capacity = array.length;

        // 빈 배열일 경우
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        //용량이 꽉찬 경우
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            //copy
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        // 용량이 전발 미만으로 요소가 차지하고 있을 경우
        if (size < ((array_capacity) / 2)) {
            int new_capacity = array_capacity / 2;

            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }
}

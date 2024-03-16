package heap;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class Heap <E>{

    private final Comparator<? super E> comparator;

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] array;

    private Heap() {
        this(null);
    }
    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    public Heap(int capacity) {
        this(capacity,null);
    }

    public Heap(int capacity, Comparator<? super E> comparator ) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }
    private int getParent(int index) {
        return index / 2;
    }

    private int getLeftChild(int index) {
        return index * 2;
    }
    private int getRightChild(int index) {
        return index * 2 + 1;
    }


    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];

        for (int i = 1; i <=size ; i ++) {
            newArray[i] = array[i];
        }

        this.array = newArray;
    }


    public void add(E value) {
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }
        siftUp(size + 1, value);
        size++;
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }
        E result = (E) array[1];
        E target;

        if(size == 1) {
            target = null;
        }
        else {
            target = (E) array[size];
        }

        array[size] = null;

        siftDown(1,target);

        return result;

    }

    private void siftDown(int idx, E target) {
        if (comparator != null) {
            siftDownComparator(idx, target, comparator);
        }
        else {
            siftDownComparable(idx, target);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target) {

        Comparable<? super E> comp = (Comparable<? super E>)target;
        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        while((child = getLeftChild(parent)) <= size) {
            int right = getRightChild(parent);
            Object childVal = array[child];

            if (right <= size && ((Comparable<? super E>)childVal).compareTo((E)array[right]) > 0) {
                child = right;
                childVal = array[child];
            }

            if (comp.compareTo((E) childVal) <= 0) {
                break;
            }
            array[parent] = childVal;
            parent = child;
        }

        array[parent] = comp;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

    }

    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comparator) {

        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        while((child = getLeftChild(parent)) <= size) {

            int right = getRightChild(parent);

            Object childVal = array[child];


            if (right <= size && comparator.compare((E) childVal, (E) array[right]) > 0) {
                child = right;
                childVal = array[child];
            }

            if (comparator.compare(target, (E) childVal) <= 0) {
                break;
            }

            array[parent] = childVal;
            parent = child;
        }

        array[parent] = target;

        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

    }

    private void siftUp(int idx, E value) {
        if (comparator != null) {
            siftUpComparator(idx, value, comparator);
        } else {
            siftUpComparable(idx, value);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E value) {
        Comparable<? super E> comp = (Comparable<? super E>) value;

        while(idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if (comp.compareTo((E)parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parent;
        }

        array[idx] = comp;
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx, E value, Comparator<? super E> comp) {
        while (idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if(comp.compare(value, (E)parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parent;
        }

        array[idx] = value;
    }
}

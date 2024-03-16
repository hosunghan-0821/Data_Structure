package heap;

import Interface_Form.Queue;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements Queue<E> {


    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;    // 최소(기본) 용적 크기

    private int size;    // 요소 개수
    private Object[] array;    // 요소를 담을 배열

    // 생성자 Type 1 (초기 공간 할당 X)
    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    // 생성자 Type 2 (초기 공간 할당 O)
    public PriorityQueue(int capacity) {
        this(capacity, null);
    }

    public PriorityQueue(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }


    @Override
    public boolean offer(E value) {

        if (size + 1 == array.length) {
            resize(array.length * 2);
        }
        siftUp(size + 1, value);
        size++;
        return false;
    }

    private void siftUp(int idx, E target) {
        if (comparator != null) {
            siftUpComparator(idx, target, comparator);
        } else {
            siftUpComparable(idx, target);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E target) {

        Comparable<? super E> comp = (Comparable<? super E>) target;

        while (idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if (comp.compareTo((E) parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parent;
        }
        array[idx] = comp;
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx, E target, Comparator<? super E> comparator) {

        while (idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if (comparator.compare(target, (E) parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parent;
        }

        array[idx] = target;
    }

    @Override
    public E poll() {

        if (array[1] == null) {
            return null;
        }
        return remove();

    }

    @SuppressWarnings("unchecked")
    private E remove() {
        if (array[1] == null) {
            throw new NoSuchElementException();
        }
        E result = (E) array[1];
        E target;
        if (size == 1) {
            target = null;
        } else {
            target = (E) array[size];
        }

        array[size] = null;
        size--;
        siftDown(1, target);

        return result;
    }

    private void siftDown(int idx, E target) {
        if (comparator != null) {
            siftDownComparator(idx, target, comparator);
        } else {
            siftDownComparable(idx, target);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comparator) {

        array[idx] = null;
        int parent = idx;
        int child;

        while ((child = getLeftChild(parent)) <= size) {

            int right = getRightChild(parent);
            Object childVal = array[child];

            if (right <= size && comparator.compare((E) childVal, (E) array[right]) > 0) {
                child = right;
                childVal = (E) array[right];
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

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target) {

        Comparable<? super E> comp = (Comparable<? super E>) target;

        array[idx] = null;

        int parent = idx;
        int child;

        while ((child = (parent << 1)) <= size) {
            int right = child + 1;
            Object c = array[child];

            if (right <= size && ((Comparable<? super E>) c).compareTo((E) array[right]) > 0) {
                child = right;
                c = array[child];
            }

            if (comp.compareTo((E) c) <= 0) {
                break;
            }

            array[parent] = c;
            parent = child;
        }

        array[parent] = comp;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    @Override
    public E peek() {
        return null;
    }


    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];

        for (int i = 1; i <= size; ++i) {
            newArray[i] = array[i];
        }

        this.array = null;
        this.array = newArray;
    }

    // 받은 인덱스의 부모 노드 인덱스를 반환
    private int getParent(int index) {
        return index / 2;
    }

    // 받은 인덱스의 왼쪽 자식 노드 인덱스를 반환
    private int getLeftChild(int index) {
        return index * 2;
    }

    // 받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
    private int getRightChild(int index) {
        return index * 2 + 1;
    }


}

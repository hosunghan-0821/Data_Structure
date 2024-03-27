package practice;

import java.util.Comparator;

public class PracticePriorityQueue<E> {

    private final Comparator<? super E> comparator;
    public static final int DEFAULT_CAPACITY = 10;

    private int size;
    private Object[] array;


    public PracticePriorityQueue() {
        this(null);
    }

    public PracticePriorityQueue(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
        this.size = 0;
    }

    public PracticePriorityQueue(int capacity) {
        this(capacity, null);
    }

    public PracticePriorityQueue(int capacity, Comparator<? super E> comparator) {
        this.size = 0;
        this.array = new Object[capacity];
        this.comparator = comparator;
    }


    public boolean offer(E value) {
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }
        siftUp(size + 1, value);
        size++;
        return true;
    }

    public E poll() {
        if (array[1] == null) {
            return null;
        }

        return remove();
    }

    @SuppressWarnings("unchecked")
    private E remove() {
        assert (array[1] != null);

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
    private void siftDownComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;

        array[idx] = null;
        int parent = idx;
        int child;
        while ((child = getLeftChild(parent)) <= size) {
            int right = child + 1;
            Object c = array[child];

            if (right <= size && ((Comparable<? super E>) c).compareTo((E) array[right]) > 0) {
                child = right;
                c = array[child];
            }

            if (comp.compareTo((E)c) <= 0) {
                break;
            }
            array[parent] = c;
            parent = child;
        }

        array[parent] = comp;

        if (array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }

    }

    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {

        array[idx] = null;    // 삭제 할 인덱스의 노드를 삭제

        int parent = idx;    // 삭제 노드부터 시작 할 부모 노드 인덱스를 가리키는 변수
        int child;    // 교환 될 자식 인덱스를 가리키는 변수

        while ((child = getLeftChild(parent)) <= size) {
            int right = getRightChild(parent);
            Object childVal = array[child];

            if (right <= size && comp.compare((E) childVal, (E) array[right]) > 0) {
                child = right;
                childVal = array[child];
            }

            if (comp.compare(target, (E) childVal) <= 0) {
                break;
            }

            array[parent] = childVal;
            parent = child;
        }

        // 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어준다.
        array[parent] = target;

        /*
         * 용적 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4 미만일 경우
         * 용적을 반으로 줄임 (단, 최소용적보단 커야 함)
         */
        if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }


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
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
        while (idx > 1) {
            int parent = getParent(idx);
            Object parentVal = array[parent];
            //compare >= 0 클 경우 보통 첫번째 파라미터가 앞에께 크다는 의미
            if (comp.compare(target, (E) parentVal) >= 0) {
                break;
            }

            array[idx] = parentVal;
            idx = parent;
        }

        array[idx] = target;
    }


    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];

        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }

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

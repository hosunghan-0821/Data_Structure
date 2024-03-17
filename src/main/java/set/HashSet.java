package set;

import Interface_Form.Set;

public class HashSet<E> implements Set<E> {


    private final static int DEFAULT_CAPACITY = 1 << 4;

    private final static float LOAD_FACTOR = 0.75f;

    Node<E>[] table; // 요소의 정보를 담고 있는 Node 저장할 배열
    private int size;

    @SuppressWarnings("unchecked")
    public HashSet() {
        table = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private static int hash(Object key) {
        int hash;
        if (key == null) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    @Override
    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    private E add(int hash, E key) {

        int idx = hash % table.length;

        if (table[idx] == null) {
            table[idx] = new Node<E>(hash, key, null);
        } else {
            Node<E> temp = table[idx];
            Node<E> prev = null;

            while (temp != null) {
                if ((temp.hash == hash) && (temp.key == key || temp.key.equals(key))) {
                    return key;
                }

                prev = temp;
                temp = temp.next;
            }

            prev.next = new Node<E>(hash, key, null);
        }
        size++;

        if (size >= LOAD_FACTOR * table.length) {
            resize();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;

        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        for (int i = 0; i < table.length; i++) {
            Node<E> value = table[i];

            if (value == null) {
                continue;
            }

            table[i] = null;

            Node<E> nextNode;

            while (value != null) {
                int idx = value.hash % newCapacity;

                if (newTable[idx] != null) {
                    Node<E> tail = newTable[idx];

                    while (tail.next != null) {
                        tail = tail.next;
                    }
                    nextNode = value.next;
                    value.next = null;
                    tail.next = value;
                } else {

                    nextNode = value.next;
                    value.next = null;
                    newTable[idx] = value;
                }

                value = nextNode;
            }
        }

        table = newTable;
    }

    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;

    }

    private Object remove(int hash, Object key) {

        int idx = hash % table.length;

        Node<E> node = table[idx];
        Node<E> removedNode = null;
        Node<E> prev = null;

        if (node == null) {
            return null;
        }

        while (node != null) {
            //같은 노드가 있다면.
            if (node.hash == hash && (node.key == key || node.key.equals(key))) {
                removedNode = node;

                if (prev == null) {
                    table[idx] = node.next;
                    node = null;
                } else {
                    prev.next = node.next;
                    node = null;
                }
                size--;
                break;
            }
            prev = node;
            node = node.next;
        }

        return removedNode;
    }

    @Override
    public boolean contains(Object o) {

        assert o != null;
        int idx = hash(o) % table.length;
        Node<E> temp = table[idx];

        while (temp != null) {
            if (o == temp.key || (o.equals(temp.key))) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        if (table != null && size > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }

    }
}

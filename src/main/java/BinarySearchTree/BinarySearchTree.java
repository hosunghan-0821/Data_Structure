package BinarySearchTree;

import java.util.Comparator;

public class BinarySearchTree<E> {

    private Node<E> root;
    private int size;

    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    public boolean add(E value) {
        if (comparator == null) {
            return addUsingComparable(value) == null;
        }
        return addUsingComparator(value, comparator) == null;
    }

    private E addUsingComparable(E value) {

        Node<E> current = root;

        if (current == null) {
            root = new Node<E>(value);
            size++;
            return null;
        }

        Node<E> currentParent;

        @SuppressWarnings("unchecked") Comparable<? super E>
                compValue = (Comparable<? super E>) value;

        int compResult;

        do {
            currentParent = current;

            compResult = compValue.compareTo(current.value);

            if (compResult < 0) {
                current = current.left;
            } else if (compResult > 0) {
                current = current.right;
            } else {
                return value;
            }

        } while (current != null);

        Node<E> newNode = new Node<E>(value, currentParent);

        if (compResult < 0) {
            currentParent.left = newNode;
        } else {
            currentParent.right = newNode;
        }

        size++;
        return null;


    }

    private E addUsingComparator(E value, Comparator<? super E> comparator) {

        Node<E> current = root;

        if (current == null) {
            root = new Node<E>(value);
            size++;
            return null;
        }
        Node<E> currentParent;
        int compResult;
        do {
            currentParent = current;
            compResult = comparator.compare(value, current.value);

            if (compResult < 0) {
                current = current.left;
            } else if (compResult > 0) {
                current = current.right;
            } else {
                return value;
            }

        } while (current != null);

        Node<E> newNode = new Node<E>(value, currentParent);

        if (compResult < 0) {
            currentParent.left = newNode;
        } else {
            currentParent.right = newNode;
        }
        size++;
        return null;
    }

    public E remove(Object o) {
        if (root == null) {
            return null;
        }
        if (comparator == null) {
            return removeUsingComparable(o);
        } else {
            return removeUsingComparator(o, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private E removeUsingComparable(Object value) {
        E oldVal = (E) value;
        Node<E> parent = null;
        Node<E> current = root;

        boolean hasLeft = false;

        if (root == null) {
            return null;
        }

        @SuppressWarnings("unchecked")
        Comparable<? super E> compValue = (Comparable<? super E>) value;

        do {
            int resComp = compValue.compareTo(current.value);
            if (resComp == 0) {
                break;
            }

            parent = current;
            if (resComp < 0) {
                hasLeft = true;
                current = current.left;
            } else {
                hasLeft = false;
                current = current.right;
            }

        } while (current != null);

        //만약 탐색 끝에 삭제해야할 노드를 못찾았다면 Null 반환
        if (current == null) {
            return null;
        }

        // 부모 노드가 없을 경우 == 삭제하는 노드가 root인 경우
        if (parent == null) {
            deleteNode(current);
            size--;
            return oldVal;
        }

        //삭제 노드가 부모 노드의 왼쪽 자식
        if (hasLeft) {
            parent.left = deleteNode(current);

            if (parent.left != null) {
                parent.left.parent = parent;
            }
        } else {
            parent.right = deleteNode(current);

            if (parent.right != null) {
                parent.right.parent = parent;
            }
        }

        size--;

        return oldVal;
    }

    private E removeUsingComparator(Object value, Comparator<? super E> comparator) {
        return null;
    }


    /*
     * @param node = 삭제되는 노드
     * @return 대체할 노드
     */
    private Node<E> getSuccessorAndUnlink(Node<E> node) {

        Node<E> currentParent = node;
        Node<E> current = node.right;

        //첫 오른쪽 자식 중 왼쪽 자식이 없을 경우 처리
        if (current.left == null) {
            currentParent.right = current.right;
            if (currentParent.right != null) {
                currentParent.right.parent = currentParent;
            }
            current.right = null;
            return current;
        }

        // 첫 오른쪽 자식에서 가장 왼쪽에 있는 자식 찾기
        while (current.left != null) {
            currentParent = current;
            current = current.left;
        }

        //가장 오른쪽 자식의 부모가 오른쪽 자식이 있는지 체크
        currentParent.left = current.right;
        if (currentParent.left != null) {
            currentParent.left.parent = currentParent;
        }

        current.right = null;
        return current;

    }

    /**
     * 삭제 할 노드에 대해 삭제를 수행하는 메소드
     * <p>
     * 1. 삭제하는 노드가 자식노드를 갖고 있지 않을 때
     * 2. 삭제하는 노드가 왼쪽 또는 오른쪽 자식 노드를 갖고 있을 때
     * 3. 삭제하는 노드가 왼쪽, 오른쪽 자식 노드 모두 갖고 있을 때
     *
     * @param node 삭제 할 노드
     * @return 삭제 후 대체 되고 난 뒤의 해당 위치의 노드를 반환
     */
    private Node<E> deleteNode(Node<E> node) {


        if (node != null) {
            //1번의 경우
            if (node.left == null && node.right == null) {
                if (node == root) {
                    root = null;
                } else {
                    node = null;
                }
                return null;
            }

            //2번의 경우
            if (node.left != null && node.right != null) {
                //대체 노드를 찾아온다.

                Node<E> replacement = getSuccessorAndUnlink(node);
                node.value = replacement.value;

            }
            //왼쪽 노드만 존재할 경우
            else if (node.left != null) {

                if (node == root) {
                    node = node.left;
                    root = node;
                    root.parent = null;
                } else {
                    node = node.left;
                }
            }
            // 오른쪽 노드만 존재할 경우
            else {
                if (node == root) {
                    node = node.right;
                    root = node;
                    root.parent = null;
                } else {
                    node = node.right;
                }
            }

        }


        return node;
    }

    /**
     * BinarySearchTree에 있는 원소의 개수를 반환해주는 메소드
     *
     * @return BinarySearchTree에 있는 원소의 개수를 반환
     */
    public int size() {
        return this.size;
    }

    /**
     * BinarySearchTree가 비어있는지를 판단하는 메소드
     *
     * @return BinarySearchTree가 비어있을 경우 true, 아닐경우 false를 반환
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 해당 객체가 BinarySearchTree에 존재하는지를 판단하는 메소드
     *
     * @param o 찾고자 하는 객체
     * @return 해당 객체가 존재 할 경우 true, 아닐 경우 false를 반환
     */
    public boolean contains(Object o) {
        if (comparator == null) {
            return containsUsingComparable(o);
        }
        return containsUsingComparator(o, comparator);
    }

    /**
     * Comparable을 이용한 객체 존재 여부를 판단하는 메소드
     *
     * @param o 찾고자 하는 객체
     * @return 해당 객체가 존재 할 경우 true, 아닐 경우 false를 반환
     */
    private boolean containsUsingComparable(Object o) {

        @SuppressWarnings("unchecked")
        Comparable<? super E> value = (Comparable<? super E>) o;

        Node<E> node = root;
        while (node != null) {
            int res = value.compareTo(node.value);
            if (res < 0) {
                node = node.left;
            } else if (res > 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Comparable을 이용한 객체 존재 여부를 판단하는 메소드
     *
     * @param o 찾고자 하는 객체
     * @param comparator 사용자에 의해 BinarySearchTree에 지정 된 비교기
     * @return 해당 객체가 존재 할 경우 true, 아닐 경우 false를 반환
     */
    private boolean containsUsingComparator(Object o, Comparator<? super E> comparator) {
        @SuppressWarnings("unchecked")
        E value = (E) o;
        Node<E> node = root;
        while (node != null) {
            int res = comparator.compare(value, node.value);
            if (res < 0) {
                node = node.left;
            } else if (res > 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * BinarySearchTree를 초기화 하는 메소드
     */
    public void clear() {
        size = 0;
        root = null;
    }
}

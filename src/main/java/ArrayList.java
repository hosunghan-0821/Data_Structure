import Interface_Form.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {


    private static final int DEFAULT_CAPACITY =10 ; // 최소 기본 용적크기
    private static final Object[] EMPTY_ARRAY = {} ; // 빈 배열

    private int size;

    Object[] array;

    // 생성자 1 (초기 공간할당 x)
    public ArrayList(){
        this.array=EMPTY_ARRAY;
        this.size=0;
    }

    // 생성자 2 (초기 공간할당 O)
    public ArrayList(int capacity){
        this.array = new Object[capacity];
        this.size=0;
    }

    private void resize(){
        int array_capacity = array.length;

        //if array's capacity is 0
        if(Arrays.equals(array,EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }
        // 용량이 꽉 차 경우
        if(size == array_capacity){
            int new_capacity = array_capacity * 2;

            //copy
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        //용적의절반 미만으로 요소가 차지하고 있을 경우
        if(size < (array_capacity/2)){
            int new_capacity = array_capacity/2;

            //copy
            array = Arrays.copyOf(array,Math.max(new_capacity,DEFAULT_CAPACITY));
            return;
        }

    }


    //기본적인 삽입
    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }
    public void addLast(E value){
        if (size == array.length){
            resize();
        }
        array[size]=value;
        size++;
    }

    //데이터 중간에 삽입
    @Override
    public void add(int index, E value) {
        if(index > size || index< 0 ){
            throw new IndexOutOfBoundsException();
        }
        if(index==size){
            addLast(value);
        }
        else{
            if(size == array.length){
                resize();
            }
            for(int i = size ; i> index; i--){
                array[i]= array[i-1];
            }

            array[index] = value;
            size++;
        }
    }
    public void addFirst(E value){
        add(0,value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E element = (E) array[index]; // 삭제될 요소를 반환하기 위해임시로 암아둠
        array[index] = null;

        for(int i = index; i <size-1; i++){
            array[i]=array[i+1];
            array[i+1]=null;
        }
        size--;
        resize();
        return element;
    }

    @Override
    public boolean remove(Object value) {

        //삭제하고자 하는 요소의 인덱스 찾기
        int index = indexOf(value);

        // -1 이라면 array에 요소가 없다는 의미이므로 false 반환
        if(index == -1 ){
            return false;
        }

        // index 위치에 있는 요소를 삭제
        remove(index);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if(index >= size || index < 0) {	// 범위 벗어나면 예외 발생
            throw new IndexOutOfBoundsException();
        }

        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if(index >= size || index < 0) {	// 범위 벗어나면 예외 발생
            throw new IndexOutOfBoundsException();
        }
        else{
            array[index] =value;
        }
    }

    @Override
    public boolean contains(Object value) {

        if(indexOf(value)>=0) return true;
        else return false;

    }

    @Override
    public int indexOf(Object value) {
        int i ;

        // value 와 같은 객체 일 경우 i(위치) 반환
        for( i =0 ; i < size ; i++){
            if(array[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object value){
        for(int i = size-1; i >= 0 ; i--){
            if(array[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    @Override
    public void clear() {
        for(int i = 0 ; i < size ; i ++){
            array[i] = null;
        }
        size = 0 ;
        resize();
    }
}

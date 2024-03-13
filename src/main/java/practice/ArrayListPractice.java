package practice;




public class ArrayListPractice {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();

        myArrayList.add(1);
        assert myArrayList.get(0) == 1;

        myArrayList.add(2);
        assert myArrayList.get(1) == 2;

        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        assert myArrayList.getCapacity() == 5;

        myArrayList.add(6);
        assert myArrayList.getCapacity() == 10;
        assert myArrayList.get(5) == 6;

        myArrayList.add(6,7);
        assert myArrayList.get(6) == 7;

        myArrayList.add(0,3);
        assert myArrayList.get(0) == 3;
        assert myArrayList.get(1) == 1;
        assert myArrayList.get(7) == 7;

        assert myArrayList.indexOf(1) == 1;
        assert myArrayList.indexOf(11) == -1;

        assert myArrayList.get(0) == 3;

        myArrayList.remove(0);
        assert myArrayList.get(0) == 1;
        assert myArrayList.get(1) == 2;
        assert myArrayList.get(6) == 7;
        myArrayList.printArray();

    }
}

package ArrayList;


import Interface_Form.List;

public class ArrayList_Test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        System.out.println(list.remove(1));
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

}

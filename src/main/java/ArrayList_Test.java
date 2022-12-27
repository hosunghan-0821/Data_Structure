import java.util.ArrayList;

public class ArrayList_Test {

    public static void main(String[] args) {

        ArrayList<Integer> original = new ArrayList<>();
        original.add(10);

        ArrayList<Integer> copy =original;

        copy.add(20);

        System.out.println("original list");
        for(int i = 0; i < original.size(); i++) {
            System.out.println("index " + i + " data = " + original.get(i));
        }

        System.out.println("\ncopy list");
        for(int i = 0; i < copy.size(); i++) {
            System.out.println("index " + i + " data = " + copy.get(i));
        }

    }

}

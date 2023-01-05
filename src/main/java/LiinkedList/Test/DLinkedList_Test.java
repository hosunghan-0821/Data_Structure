package LiinkedList.Test;

import LiinkedList.DLinkedList;

import java.util.Comparator;

public class DLinkedList_Test {

    public static void main(String[] args) {
        DLinkedList<Integer> original = new DLinkedList<>();

        original.add(10);

        DLinkedList<Integer> copy = original;
        DLinkedList<Integer> clone = original.clone();

        copy.add(20);
        clone.add(30);

        DLinkedList<Student> list = new DLinkedList<>();

        list.add(new Student("김자바",92));
        list.add(new Student("이시플",555));
        list.add(new Student("한호성",203));


        list.sort();
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


        System.out.println("original list");
        for(int i = 0; i < original.size(); i++) {
            System.out.println("index " + i + " data = " + original.get(i));
        }

        System.out.println("\ncopy list");
        for(int i = 0; i < copy.size(); i++) {
            System.out.println("index " + i + " data = " + copy.get(i));
        }

        System.out.println("\nclone list");
        for(int i = 0; i < clone.size(); i++) {
            System.out.println("index " + i + " data = " + clone.get(i));
        }


        System.out.println("\noriginal list reference : " + original);
        System.out.println("copy list reference : " + copy);
        System.out.println("clone list reference : " + clone);


    }


}
class Student implements Comparable<Student>{
    String name;
    int score;

    Student (String name , int score){
        this.name = name;
        this.score= score;
    }

    public String toString(){
        return "이름 : " +name + "\t성적 : "+score;
    }

    @Override
    public int compareTo(Student o) {
        return o.score-this.score;
    }
}

package demo.java8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        // iterating list with external iterators

        // with conventional for loop
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }

        // with enhanced for loop
        for (Integer i : list) {
            System.out.print(i);
        }

        // with iterators

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }


        // iterating with internal iterators


        Consumer<Integer> consumer = new Consumer<Integer>() {
            public void accept(Integer i) {
                System.out.println(i);
            }
        };

        list.forEach(consumer);


        // above anonymous consumer class can be written as lambda expression


        Consumer<Integer> consumer1 = i -> System.out.println(i);
        list.forEach(consumer1);

        // above lambda expression can be shorten by using method reference for printing


        Consumer<Integer> consumer2 = System.out::print;
        list.forEach(consumer2);

        // above reference variable consumer2 does not need to be declared, hence whole printing code can be
        // shorten by passing method reference in forEach


        list.forEach(System.out::println);
    }
}

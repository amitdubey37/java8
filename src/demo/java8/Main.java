package demo.java8;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(135, 21, 34, 44, 55, 71, 5);


        // suppose we want to find the first number in list that is divisible by 5 and then print its double value

        // in java 7 or lower we would do

        for (Integer i : list) {
            if (i % 5 == 0) {
                System.out.println(i * 2);
                break;
            }
        }

        // lets try to achieve the same with java 8

        Integer result = list.stream()
                .map(i -> i * 2)
                .filter(i -> i % 5 == 0)
                .findFirst()
                .orElse(0);

        System.out.println("result: " + result);

        /* one might think that the above java 7 code that we wrote is more efficient than lambda one
         * because we're stoping right there once we've found the value that is divisible by 5 and
         * printing the double of it. On the other hand in later code we're multiplying each element with 2
         * then filtering out the elements that are divisible by 5 and then printing the first result.
          *
          * But later code (lambda one) is more efficient because stream are lazily processed so even if it looks
          * like it's not efficient, it is. To prove my point let's revisit the above code and rewrite it in chunks
          * */

        Function<Integer, Integer> mapFunction = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return doubleIt(integer);
            }
        };

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) {
                return isDivisibleBy5(i);
            }
        };


        Stream<Integer> stream = list.stream();
        Integer result1 = stream.map(mapFunction).filter(predicate).findFirst().orElse(0);
        System.out.println("result1: " + result1);

        // if you see the console doubleIt and isDivisibleBy5 methods are called only once
    }

    static Integer doubleIt(Integer i) {
        System.out.println("doubleIt called with" + i);
        return i * 2;
    }

    static Boolean isDivisibleBy5(Integer i) {
        System.out.println("isDivisible called with" + i);
        return i % 5 == 0;
    }
}

package demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        // to print the doubled the value of each item in the list conventionally we'll have to do


        for (Integer i : list) {
            System.out.println(i * 2);
        }

        // what if we want to double each item and then add them
        Integer result = 0;
        for (Integer i : list) {
            result += i * 2;
        }
        System.out.println(result);

        // with java 8 above can be achieved in smarter and efficient way


        Integer sum = list.stream().map(i -> i * 2).reduce(0, (a, e) -> a + e);
        System.out.println("sum = " + sum);

        // to understand the above code let's divide it into more chunks and understand each of them


        Stream<Integer> stream = list.stream();

        Function<Integer, Integer> mapFunction = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };

        BinaryOperator<Integer> binaryOperator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };

        Integer sum1 = stream.map(mapFunction).reduce(0, binaryOperator);

        System.out.println("sum1 = " + sum1);
    }
}

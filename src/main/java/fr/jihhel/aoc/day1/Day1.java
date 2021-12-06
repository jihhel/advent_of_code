package fr.jihhel.aoc.day1;

import fr.jihhel.aoc.utils.Utils;
import io.vavr.Tuple;
import io.vavr.collection.List;

public class Day1 {

    public static void execute() {
        List<Integer> input = readInput();

        System.out.println("There are " + measure(input) + " measurements that are larger than the previous one in the first method.");

        List<Integer> windows = input.sliding(3, 1).map(l -> l.sum().intValue()).toList();

        System.out.println("There are " + measure(windows) + " measurements that are larger than the previous one in the second method.");
    }

    private static int measure(List<Integer> list) {
        return list.foldLeft(Tuple.of(0, Integer.MAX_VALUE), (acc, currentValue) -> {
            if (acc._2 < currentValue) {
                return acc.map(counter -> counter + 1, v -> currentValue);
            } else {
                return acc.update2(currentValue);
            }
        })._1;
    }

    private static List<Integer> readInput() {
        return Utils.readFile(1).map(Integer::parseInt);
    }
}

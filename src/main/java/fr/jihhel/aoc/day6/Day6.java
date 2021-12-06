package fr.jihhel.aoc.day6;

import fr.jihhel.aoc.utils.Utils;
import io.vavr.collection.Array;
import io.vavr.collection.List;

import java.util.Arrays;

public class Day6 {

    public static void execute() {
        System.out.println("Q1: " + nbFish(80));
        System.out.println("Q2: " + nbFish(256));
    }

    private static double nbFish(int nbTurns) {
        Array<Double> current = init();
        for (int i = 0; i < nbTurns; i++) {
            current = update(current);
        }

        return current.get(0) +
                current.get(1) +
                current.get(2) +
                current.get(3) +
                current.get(4) +
                current.get(5) +
                current.get(6) +
                current.get(7) +
                current.get(8);
    }

    private static Array<Double> init() {
        List<Double> list = readInput();
        Array<Double> array = Array.fill(9, 0d);

        double count1 = list.count(i -> i == 1);
        double count2 = list.count(i -> i == 2);
        double count3 = list.count(i -> i == 3);
        double count4 = list.count(i -> i == 4);
        double count5 = list.count(i -> i == 5);
        double count6 = list.count(i -> i == 6);

        array = array.update(1, count1);
        array = array.update(2, count2);
        array = array.update(3, count3);
        array = array.update(4, count4);
        array = array.update(5, count5);
        array = array.update(6, count6);

        return array;
    }

    private static Array<Double> update(Array<Double> current) {
        Array<Double> next = Array.fill(9, 0d);
        next = next.update(8, current.get(0));
        next = next.update(7, current.get(8));
        next = next.update(6, current.get(7) + current.get(0));
        next = next.update(5, current.get(6));
        next = next.update(4, current.get(5));
        next = next.update(3, current.get(4));
        next = next.update(2, current.get(3));
        next = next.update(1, current.get(2));
        next = next.update(0, current.get(1));

        return next;
    }

    private static List<Double> readInput() {
        return Utils.readFile(4)
                .map(str -> str.split(","))
                .flatMap(d -> List.ofAll(Arrays.stream(d)))
                .map(Double::parseDouble);
    }
}

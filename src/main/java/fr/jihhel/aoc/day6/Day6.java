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

        return current.sum().doubleValue();
    }

    private static Array<Double> init() {
        List<Double> list = readInput();
        Array<Double> array = Array.fill(9, 0d);

        for (double i = 1; i < 7; i++) {
            double finalI = i;
            double count = list.count(nb -> nb == finalI);
            array = array.update((int) i, count);
        }

        return array;
    }

    private static Array<Double> update(Array<Double> current) {
        Array<Double> next = Array.fill(9, 0d);

        for (int i = 7; i >= 0; i--) {
            next = next.update(i, current.get(i + 1));
        }

        next = next.update(8, current.get(0));
        next = next.update(6, current.get(7) + current.get(0));

        return next;
    }

    private static List<Double> readInput() {
        return Utils.readFile(6)
                .map(str -> str.split(","))
                .flatMap(d -> List.ofAll(Arrays.stream(d)))
                .map(Double::parseDouble);
    }
}

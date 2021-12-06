package fr.jihhel.aoc.day2;

import fr.jihhel.aoc.utils.Utils;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.List;

import static io.vavr.API.*;


public class Day2 {
    public static void execute() {
        List<Tuple2<String, Integer>> input = readInput();

        Tuple2<Integer, Integer> q1 = doQ1(input);

        System.out.println("Q1: The horizontal position is " + q1._1 + " and the depth is " + q1._2);
        System.out.println("Q2: So product is " + q1._1 * q1._2);

        Tuple3<Integer, Integer, Integer> q2 = doQ2(input);

        System.out.println("Q2: The horizontal position is " + q2._1 + " and the depth is " + q2._2);
        System.out.println("Q2: So product is " + q2._1 * q2._2);
    }

    private static Tuple2<Integer, Integer> doQ1(List<Tuple2<String, Integer>> input) {
        // _1 is horizontal position
        // _2 is depth
        Tuple2<Integer, Integer> tuple = new Tuple2<>(0, 0);

        for (Tuple2<String, Integer> t: input) {

            tuple = Match(t._1).of(
                    Case($("forward"), tuple.update1(tuple._1 + t._2)),
                    Case($("up"), tuple.update2(tuple._2 - t._2)),
                    Case($("down"), tuple.update2(tuple._2 + t._2)),
                    Case($(), tuple)
            );
        }

        return tuple;
    }

    private static Tuple3<Integer, Integer, Integer> doQ2(List<Tuple2<String, Integer>> input) {
        // _1 is horizontal position
        // _2 is depth
        // _3 is aim
        Tuple3<Integer, Integer, Integer> tuple = new Tuple3<>(0, 0, 0);
        for (Tuple2<String, Integer> t: input) {

            tuple = Match(t._1).of(
                    Case($("forward"), tuple.update1(tuple._1 + t._2).update2(tuple._2 + t._2 * tuple._3)),
                    Case($("up"), tuple.update3(tuple._3 - t._2)),
                    Case($("down"), tuple.update3(tuple._3 + t._2)),
                    Case($(), tuple)
            );
        }
        return tuple;
    }

    private static List<Tuple2<String, Integer>> readInput() {
        return Utils.readFile(2)
                .map(str -> str.split(" "))
                .map(array -> new Tuple2<>(array[0], Integer.parseInt(array[1])));
    }
}

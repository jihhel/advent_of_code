package fr.jihhel.aoc.day3;

import fr.jihhel.aoc.utils.Utils;
import io.vavr.Function2;
import io.vavr.collection.Array;
import io.vavr.collection.List;

import java.util.Arrays;

public class Day3 {

    public static void execute() {
        List<Array<Integer>> arrays = readInput();
        Array<Integer> report = makeReport(arrays);

        q1(report);

        Array<Integer> o2Result = o2GeneratorRating(arrays, report);
        int o2 = binaryArrayToInt(o2Result);
        Array<Integer> co2Result = co2ScrubberRating(arrays, report);
        int co2 = binaryArrayToInt(co2Result);
        System.out.println("O2 = " + o2 + " CO2 = " + co2 + " result " + o2 * co2);
    }

    private static Array<Integer> makeReport(List<Array<Integer>> arrays) {
        int arraySize = arrays.head().size();
        return arrays.foldLeft(Array.fill(arraySize, 0), (acc, current) -> {
            Array<Integer> tmp = current.map(nb -> nb == 1 ? 1 : -1);
            return acc.zipWith(tmp, Integer::sum);
        });
    }

    private static Array<Integer> o2GeneratorRating(List<Array<Integer>> arrays, Array<Integer> report) {
        Function2<Array<Integer>, Integer, Boolean> f = (r, idx) -> r.get(idx) >= 0;
        return makeRating(arrays, report, 0, f);
    }

    private static Array<Integer> co2ScrubberRating(List<Array<Integer>> arrays, Array<Integer> report) {
        Function2<Array<Integer>, Integer, Boolean> f = (r, idx) -> r.get(idx) < 0;
        return makeRating(arrays, report, 0, f);
    }

    private static Array<Integer> makeRating(List<Array<Integer>> arrays,
                                             Array<Integer> report,
                                             int idx,
                                             Function2<Array<Integer>, Integer, Boolean> reportFunction) {
        if (arrays.size() == 1) {
            return arrays.get(0);
        }
        List<Array<Integer>> leftArrays = arrays.filter(array -> {
            if (reportFunction.apply(report, idx)) {
                return array.get(idx) == 1;
            } else {
                return array.get(idx) == 0;
            }
        });

        return makeRating(leftArrays, makeReport(leftArrays), idx + 1, reportFunction);
    }

    private static void q1(Array<Integer> report) {
        Array<Integer> gammaArray = report.map(nb -> nb > 0 ? 1 : 0);
        int gamma = binaryArrayToInt(gammaArray);

        Array<Integer> epsilonArray = report.map(nb -> nb > 0 ? 0 : 1);
        int epsilon = binaryArrayToInt(epsilonArray);
        System.out.println("Gamma " + gamma + " epsilon " + epsilon + " result " + gamma * epsilon);
    }

    private static int binaryArrayToInt(Array<Integer> array) {
        StringBuilder builder = new StringBuilder();
        for (Integer i: array) {
            builder.append(i.toString());
        };

        return Integer.parseInt(builder.toString(), 2);
    }

    private static List<Array<Integer>> readInput() {
        return Utils.readFile(3)
                .map(str -> str.split(""))
                .map(array -> Array.ofAll(Arrays.stream(array)))
                .map(array -> array.map(Integer::parseInt));
    }
}

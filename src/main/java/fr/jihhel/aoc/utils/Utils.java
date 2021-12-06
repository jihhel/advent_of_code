package fr.jihhel.aoc.utils;

import io.vavr.collection.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {
    public static List<String> readFile(int day) {
        List<String> list = List.of();
        try {
            File myObj = new File("inputs/day" + day + ".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                list = list.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }
}

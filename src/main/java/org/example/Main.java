package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;;

public class Main {

    public static void main(String[] args) {

        while (true) {

            System.out.println("Enter the path: ");
            Path path = Paths.get(getPath());

            if (!Files.exists(path)) {
                continue;
            }

            if (!Files.isDirectory(path)) {
                //throw new IllegalArgumentException("Path is not a folder!");
                System.out.println("Path is not a folder!");
                continue;
            }

            try {
                List<Path> sortedFiles = Files.walk(path)
                        .filter(p -> isOverSizeThreshold(p))
                        .collect(Collectors.toList());
                for (Path file : sortedFiles) {
                    System.out.println(file);
                }
                break;
            } catch (SecurityException | IOException exc) {
                exc.getCause();
            }
        }

    }

    Integer in = 5;


    private static String getPath() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = "";
        try {
            path = reader.readLine();
        } catch (IOException e) {
            e.getMessage();
        }
        return path;
    }

    private static boolean isOverSizeThreshold(Path path) {
        boolean isMoreThan10MB = false;
        try {
            double result = Files.size(path) / (double)(1024 * 1024);
            if (result > 10)
                isMoreThan10MB = true;
        } catch (IOException e) {
            e.getMessage();
        }
        return isMoreThan10MB;
    }
}

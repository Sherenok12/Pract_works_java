package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionReader {

    public static BufferedReader openReader(String source)
            throws IOException {

        URL url = new URL(source);

        return new BufferedReader(
                new InputStreamReader(
                        url.openStream(),
                        StandardCharsets.UTF_8));
    }

    public static List<String> readLines(String source) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = openReader(source)) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Помилка читання даних: " + source, e);
        }

        return lines;
    }
}

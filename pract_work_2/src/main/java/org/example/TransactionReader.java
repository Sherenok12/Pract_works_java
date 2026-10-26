package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionReader {

    public static List<String> readLines(String source) {
        List<String> lines = new ArrayList<>();

        try {
            URL url = new URL(source);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            url.openStream(),
                            StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.isBlank()) {
                        lines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Помилка читання даних: " + source, e);
        }

        return lines;
    }
}

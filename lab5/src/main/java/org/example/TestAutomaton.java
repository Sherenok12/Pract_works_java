package org.example;

import java.util.Objects;

public class TestAutomaton {

    private static final String WORD = "TEST";

    private static final int[] PREFIX = buildPrefix();

    public State transition(State currentState, char symbol) {

        Objects.requireNonNull(currentState);

        if (currentState == State.F) {
            return State.F;
        }

        int matched = currentState.getMatchedLength();


        while (matched > 0 && WORD.charAt(matched) != symbol) {
            matched = PREFIX[matched - 1];
        }

        if (WORD.charAt(matched) == symbol) {
            matched++;
        }

        if (matched == WORD.length()) {
            return State.F;
        }

        return State.fromMatchedLength(matched);
    }

    public State process(String input) {

        Objects.requireNonNull(input, "Не можна передавати порожнє значення");

        State currentState = State.S;
        for (char symbol : input.toCharArray()) {
            currentState = transition(currentState, symbol);
        }
        return currentState;
    }

    private static int[] buildPrefix() {

        int[] prefix = new int[WORD.length()];
        int j = 0;
        for (int i = 1; i < WORD.length(); i++) {
            while (j > 0 && WORD.charAt(i) != WORD.charAt(j)) {
                j = prefix[j - 1];
            }
            if (WORD.charAt(i) == WORD.charAt(j)) {
                j++;
            }
            prefix[i] = j;
        }
        return prefix;
    }
}

package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAutomatonTest {

    private final TestAutomaton automaton = new TestAutomaton();

    @ParameterizedTest
    @CsvSource({
            "S, T, ONE",
            "S, E, S",
            "S, X, S",

            "ONE, E, TWO",
            "ONE, T, ONE",
            "ONE, X, S",

            "TWO, S, THREE",
            "TWO, T, ONE",
            "TWO, X, S",

            "THREE, T, F",
            "THREE, E, S",
            "THREE, X, S",

            "F, T, F",
            "F, E, F",
            "F, X, F"
    })
    void testTransitions(
            State current,
            char symbol,
            State expected) {

        assertEquals(expected,
                automaton.transition(current, symbol));
    }

    @ParameterizedTest
    @CsvSource({
            "'', S",
            "'abc', S",
            "'T', ONE",
            "'TE', TWO",
            "'TES', THREE",
            "'TEST', F",
            "'abcTESTabc', F",
            "'abcTES', THREE",
            "'TESTabc', F",
            "'abcTEST', F",
            "'TTTT', ONE",
            "'TESE', S",
            "'12345', S",
            "'test', S",
            "'TEX', S"
    })
    void testProcess(
            String input,
            State expected) {

        assertEquals(expected, automaton.process(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'TTEST', F",
            "'TETEST', F",
            "'TTTEST', F",
            "'TTTTTEST', F",
            "'abcTTESTabc', F",
            "'TTESTabc', F",
            "'TESTTEST', F"
    })
    void testOverlappingSequences(String input, State expected) {
        assertEquals(expected, automaton.process(input));
    }
}

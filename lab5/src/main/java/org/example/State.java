package org.example;

public enum State {

    S(0),
    ONE(1),
    TWO(2),
    THREE(3),
    F(4);

    private final int matchedLength;

    State(int matchedLength) {
        this.matchedLength = matchedLength;
    }

    public int getMatchedLength() {
        return matchedLength;
    }

    public static State fromMatchedLength(int length) {

        for (State state : values()) {
            if (state.matchedLength == length) {
                return state;
            }
        }

        throw new IllegalArgumentException(
                "Неправильна довжина збігу: " + length
        );
    }
}

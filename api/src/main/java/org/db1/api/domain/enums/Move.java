package org.db1.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Move {
    ROCK,
    PAPER,
    SCISSORS;

    @JsonCreator
    public static Move fromString(String value) {
        for (Move move : Move.values()) {
            if (move.name().equalsIgnoreCase(value)) {
                return move;
            }
        }

        throw new IllegalArgumentException("Invalid move: " + value);
    }

    public boolean beats(Move m) {
        return (this == ROCK && m == SCISSORS) ||
                (this == PAPER && m == ROCK) ||
                (this == SCISSORS && m == PAPER);
    }
}
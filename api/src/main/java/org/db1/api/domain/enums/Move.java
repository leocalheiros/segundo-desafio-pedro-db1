package org.db1.api.domain.enums;

public enum Move {
    ROCK,
    PAPER,
    SCISSORS;

    public boolean beats(Move m) {
        return (this == ROCK && m == SCISSORS) ||
                (this == PAPER && m == ROCK) ||
                (this == SCISSORS && m == PAPER);
    }
}
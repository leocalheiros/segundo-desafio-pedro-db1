package org.db1.api.domain.gamehistory.dto;

import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.enums.Move;
import org.db1.api.domain.gamehistory.GameHistory;

import java.time.LocalDateTime;

public record GameDetailsDTO(
        String playerOneName,
        Move playerOneMove,
        String playerTwoName,
        Move playerTwoMove,
        MatchResult result,
        LocalDateTime dateTime) {

    public GameDetailsDTO(GameHistory gameHistory) {
        this(gameHistory.getPlayerOneName(),
                gameHistory.getPlayerOneMove(),
                gameHistory.getPlayerTwoName(),
                gameHistory.getPlayerTwoMove(),
                gameHistory.getMatchResult(),
                gameHistory.getPlayedIn());
    }
}

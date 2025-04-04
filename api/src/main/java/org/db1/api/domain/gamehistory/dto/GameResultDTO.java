package org.db1.api.domain.gamehistory.dto;

import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.enums.Move;

public record GameResultDTO(
        String playerOneName,
        Move playerOneMove,
        String playerTwoName,
        Move playerTwoMove,
        MatchResult result) {
    public GameResultDTO(GameStartDTO dto, MatchResult result) {
        this(dto.playerOneName(), dto.playerOneMove(), dto.playerTwoName(), dto.playerTwoMove(), result);
    }
}

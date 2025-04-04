package org.db1.api.domain.gamehistory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.db1.api.domain.enums.Move;

public record GameStartDTO(
        @NotBlank(message = "Name can't be blank")
        String playerOneName,

        @NotNull(message = "Move can't be null")
        Move playerOneMove,

        @NotBlank(message = "Name can't be blank")
        String playerTwoName,

        @NotNull(message = "Move can't be null")
        Move playerTwoMove) {
}

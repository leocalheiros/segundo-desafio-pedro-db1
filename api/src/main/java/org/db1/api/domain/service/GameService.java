package org.db1.api.domain.service;

import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.gamehistory.GameHistory;
import org.db1.api.domain.gamehistory.GameHistoryRepository;
import org.db1.api.domain.gamehistory.dto.GameDetailsDTO;
import org.db1.api.domain.gamehistory.dto.GameResultDTO;
import org.db1.api.domain.gamehistory.dto.GameStartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameHistoryRepository repository;

    @Autowired
    public GameService(GameHistoryRepository repository) {
        this.repository = repository;
    }

    public GameResultDTO play(GameStartDTO dto) {
        boolean hasPlayer1Won = dto.playerOneMove().beats(dto.playerTwoMove());
        boolean hasPlayer2Won = dto.playerTwoMove().beats(dto.playerOneMove());

        if (hasPlayer1Won) {
            repository.save(new GameHistory(dto, MatchResult.PLAYER_ONE_WINS));
            return new GameResultDTO(dto, MatchResult.PLAYER_ONE_WINS);
        }

        if (hasPlayer2Won) {
            repository.save(new GameHistory(dto, MatchResult.PLAYER_TWO_WINS));
            return new GameResultDTO(dto, MatchResult.PLAYER_TWO_WINS);
        }

        repository.save(new GameHistory(dto, MatchResult.DRAW));
        return new GameResultDTO(dto,MatchResult.DRAW);
    }

    public List<GameDetailsDTO> list() {
        return repository.findAll().stream().map(GameDetailsDTO::new).toList();
    }
}

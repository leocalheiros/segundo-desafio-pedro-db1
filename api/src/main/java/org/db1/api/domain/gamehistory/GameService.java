package org.db1.api.domain.gamehistory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.gamehistory.dto.GameDetailsDTO;
import org.db1.api.domain.gamehistory.dto.GameResultDTO;
import org.db1.api.domain.gamehistory.dto.GameStartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class GameService {
    private static final Logger log = LogManager.getLogger(GameService.class);
    private final GameHistoryRepository repository;

    @Autowired
    public GameService(GameHistoryRepository repository) {
        this.repository = repository;
    }

    public GameResultDTO play(GameStartDTO dto) {
        log.info("Iniciando partida em {}", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        boolean hasPlayer1Won = dto.playerOneMove().beats(dto.playerTwoMove());
        boolean hasPlayer2Won = dto.playerTwoMove().beats(dto.playerOneMove());

        if (hasPlayer1Won) {
            GameHistory gameSaved = new GameHistory(dto, MatchResult.PLAYER_ONE_WINS);
            repository.save(gameSaved);
            log.info("Jogador 1 venceu");
            log.info(gameSaved);
            return new GameResultDTO(dto, MatchResult.PLAYER_ONE_WINS);
        }

        if (hasPlayer2Won) {
            GameHistory gameSaved = new GameHistory(dto, MatchResult.PLAYER_TWO_WINS);
            repository.save(gameSaved);
            log.info("Jogador 2 venceu");
            log.info(gameSaved);
            return new GameResultDTO(dto, MatchResult.PLAYER_TWO_WINS);
        }

        GameHistory gameSaved = new GameHistory(dto, MatchResult.DRAW);
        repository.save(gameSaved);
        log.info("Empate");
        log.info(gameSaved);
        return new GameResultDTO(dto,MatchResult.DRAW);
    }

    public Page<GameDetailsDTO> list(Pageable pageable) {
        log.info("Listando as últimas partidas em {}", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return repository.findAllByOrderByPlayedInDesc(pageable).map(GameDetailsDTO::new);
    }
}

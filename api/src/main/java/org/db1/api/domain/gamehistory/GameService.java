package org.db1.api.domain.gamehistory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.gamehistory.dto.GameDetailsDTO;
import org.db1.api.domain.gamehistory.dto.GameResultDTO;
import org.db1.api.domain.gamehistory.dto.GameStartDTO;
import org.db1.api.domain.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private static final Logger log = LogManager.getLogger(GameService.class);
    private final GameHistoryRepository repository;

    @Autowired
    public GameService(GameHistoryRepository repository) {
        this.repository = repository;
    }

    public GameResultDTO play(GameStartDTO dto) {
        log.info("Iniciando partida em {}", LocalDateTimeUtils.setSaoPauloDateTime());
        boolean hasPlayer1Won = dto.playerOneMove().beats(dto.playerTwoMove());
        boolean hasPlayer2Won = dto.playerTwoMove().beats(dto.playerOneMove());

        if (hasPlayer1Won) {
            log.info("Jogador 1 venceu");
            return handleGameResult(dto, MatchResult.PLAYER_ONE_WINS);
        }

        if (hasPlayer2Won) {
            log.info("Jogador 2 venceu");
            return handleGameResult(dto, MatchResult.PLAYER_TWO_WINS);
        }

        log.info("Empate");
        return handleGameResult(dto, MatchResult.DRAW);
    }

    private GameResultDTO handleGameResult(GameStartDTO dto, MatchResult result) {
        GameHistory gameSaved = new GameHistory(dto, result);
        repository.save(gameSaved);
        log.info(gameSaved);
        return new GameResultDTO(dto, result);
    }

    public Page<GameDetailsDTO> list(Pageable pageable) {
        log.info("Listando as últimas partidas em {}", LocalDateTimeUtils.setSaoPauloDateTime());
        return repository.findAllByOrderByPlayedInDesc(pageable).map(GameDetailsDTO::new);
    }
}

package org.db1.api.controller;

import jakarta.validation.Valid;
import org.db1.api.domain.gamehistory.dto.GameDetailsDTO;
import org.db1.api.domain.gamehistory.dto.GameResultDTO;
import org.db1.api.domain.gamehistory.dto.GameStartDTO;
import org.db1.api.domain.gamehistory.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/play")
    public ResponseEntity<GameResultDTO> play(@RequestBody @Valid GameStartDTO dto) {
        var result = gameService.play(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history")
    public ResponseEntity<Page<GameDetailsDTO>> list(@PageableDefault(size = 5) Pageable pageable) {
        var resultList = gameService.list(pageable);
        return ResponseEntity.ok(resultList);
    }
}

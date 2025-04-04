package org.db1.api.controller;

import jakarta.validation.Valid;
import org.db1.api.domain.gamehistory.dto.GameDetailsDTO;
import org.db1.api.domain.gamehistory.dto.GameResultDTO;
import org.db1.api.domain.gamehistory.dto.GameStartDTO;
import org.db1.api.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/play")
    public ResponseEntity<GameResultDTO> play(@RequestBody @Valid GameStartDTO dto) {
        var result = gameService.play(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history")
    public ResponseEntity<List<GameDetailsDTO>> list() {
        var resultList = gameService.list();
        return ResponseEntity.ok(resultList);
    }
}

package org.db1.api.domain.gamehistory;

import jakarta.persistence.*;
import lombok.*;
import org.db1.api.domain.enums.MatchResult;
import org.db1.api.domain.enums.Move;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_history")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@EqualsAndHashCode(of = "id")
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_one_name", nullable = false)
    private String playerOneName;

    @Column(name = "player_two_name", nullable = false)
    private String playerTwoName;

    @Column(name = "player_one_move", nullable = false)
    @Enumerated(EnumType.STRING)
    private Move playerOneMove;

    @Column(name = "player_two_move", nullable = false)
    @Enumerated(EnumType.STRING)
    private Move playerTwoMove;

    @Column(name = "played_in", nullable = false)
    private LocalDateTime playedIn;

    @Column(name = "match_result", nullable = false)
    @Enumerated(EnumType.STRING)
    private MatchResult matchResult;
}

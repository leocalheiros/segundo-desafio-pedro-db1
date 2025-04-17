package org.db1.api.domain.gamehistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameHistoryRepository extends JpaRepository<GameHistory, Long> {
    Page<GameHistory> findAllByOrderByPlayedInDesc(Pageable pageable);
}

package com.odds_and_ends.backendv1.entity.trash_share_board;

import com.odds_and_ends.backendv1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrashShareBoardRepository extends JpaRepository<TrashShareBoard, Long> {
    Optional<TrashShareBoard> findByIdAndUser(long id, User user);
}

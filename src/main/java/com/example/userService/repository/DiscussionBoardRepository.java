package com.example.userService.repository;

import com.example.userService.model.DiscussionBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionBoardRepository extends JpaRepository<DiscussionBoard,Long> {
}

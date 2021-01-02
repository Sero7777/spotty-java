package com.spotty.spotty.query.svc.repositories;

import com.spotty.spotty.query.svc.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}

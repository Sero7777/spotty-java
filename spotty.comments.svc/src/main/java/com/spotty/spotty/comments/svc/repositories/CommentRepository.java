package com.spotty.spotty.comments.svc.repositories;

import com.spotty.spotty.comments.svc.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}

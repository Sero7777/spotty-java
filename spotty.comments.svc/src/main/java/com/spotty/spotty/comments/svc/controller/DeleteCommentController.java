package com.spotty.spotty.comments.svc.controller;

import com.spotty.spotty.comments.svc.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class DeleteCommentController {

    private final CommentService commentService;

    @DeleteMapping("/{id}")
    public ResponseEntity deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}

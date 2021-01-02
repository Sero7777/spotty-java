package com.spotty.spotty.comments.svc.controller;

import com.spotty.spotty.comments.svc.models.CommentDto;
import com.spotty.spotty.comments.svc.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class CreateCommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createNewComment(@RequestBody @Valid CommentDto comment) {
        CommentDto commentDto = commentService.saveNewComment(comment);

        if (commentDto != null) {
            return new ResponseEntity(commentDto, HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.spotty.spotty.comments.svc.controller;

import com.spotty.spotty.comments.svc.models.CommentDto;
import com.spotty.spotty.comments.svc.models.CommentUpdateDto;
import com.spotty.spotty.comments.svc.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class UpdateCommentController {

    private final CommentService commentService;

    @PutMapping
    public ResponseEntity updateComment(@RequestBody @Valid CommentUpdateDto commentUpdateDto) {
        CommentDto commentDto = commentService.updateComment(commentUpdateDto);

        if (commentDto != null) {
            return new ResponseEntity(commentDto, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

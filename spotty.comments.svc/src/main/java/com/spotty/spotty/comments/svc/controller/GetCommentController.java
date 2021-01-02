package com.spotty.spotty.comments.svc.controller;

import com.spotty.spotty.comments.svc.models.CommentDto;
import com.spotty.spotty.comments.svc.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class GetCommentController {

    private final CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity getAllComments() {
        List<CommentDto> commentDtoList = commentService.getAllComments();

        return new ResponseEntity(commentDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getComment(@PathVariable String id) {
        CommentDto comment = commentService.getComment(id);

        if (comment != null) {
            return new ResponseEntity(comment, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

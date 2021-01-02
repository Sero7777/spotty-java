package com.spotty.spotty.comments.svc.services;

import com.spotty.spotty.comments.svc.domain.Comment;
import com.spotty.spotty.comments.svc.domain.Spot;
import com.spotty.spotty.comments.svc.exceptions.CommentNotFoundException;
import com.spotty.spotty.comments.svc.exceptions.SpotNotFoundException;
import com.spotty.spotty.comments.svc.messaging.MessagingService;
import com.spotty.spotty.comments.svc.models.CommentDto;
import com.spotty.spotty.comments.svc.models.CommentUpdateDto;
import com.spotty.spotty.comments.svc.repositories.CommentRepository;
import com.spotty.spotty.comments.svc.repositories.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final SpotRepository spotRepository;
    private final MessagingService messagingService;
    private CommentMapperService commentMapperService;

    @Override
    public CommentDto saveNewComment(CommentDto commentDto) {
        Comment comment = commentRepository.save(createSavableComment(commentDto));

        messagingService.publishCommentCreatedEvent(commentMapperService.mapCommentToCommentDto(comment));

        return commentMapperService.mapCommentToCommentDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentUpdateDto commentDto) {
        Comment updatedComment = updateCommentFromDb(commentDto);
        Comment savedComment = commentRepository.save(updatedComment);

        messagingService.publishCommentUpdatedEvent(commentMapperService.mapCommentToCommentDto(savedComment));

        return commentMapperService.mapCommentToCommentDto(savedComment);
    }

    @Override
    public void deleteComment(String id) {
        UUID uuid = UUID.fromString(id);

        Comment comment = commentRepository.findById(uuid).orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);

        messagingService.publishCommentDeletedEvent(id);
    }

    @Override
    public CommentDto getComment(String id) {
        UUID uuid = UUID.fromString(id);
        Comment comment = commentRepository.findById(uuid).orElseThrow(CommentNotFoundException::new);

        return commentMapperService.mapCommentToCommentDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream().map(comment -> commentMapperService.mapCommentToCommentDto(comment)).collect(Collectors.toList());
    }

    private Comment updateCommentFromDb(CommentUpdateDto commentDto) {
        Comment commentFromDb = getCommentFromDB(commentDto.getId());
        return updateCommentProperties(commentDto, commentFromDb);
    }

    private Comment getCommentFromDB(String id) {
        UUID commentId = UUID.fromString(id);
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    private Comment updateCommentProperties(CommentUpdateDto commentDto, Comment comment) {
        comment.setText(commentDto.getText());
        return comment;
    }

    private Comment createSavableComment(CommentDto commentDto) {
        UUID spotId = UUID.fromString(commentDto.getSpotId());

        Spot spot = spotRepository.findById(spotId).orElseThrow(SpotNotFoundException::new);

        return Comment.builder()
                .spot(spot)
                .text(commentDto.getText())
                .build();
    }
}

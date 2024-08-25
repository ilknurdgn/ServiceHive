package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.CommentCreateDto;
import ilknurdogan.servicefinder.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@Validated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody @Valid CommentCreateDto commentCreateDto){
        commentService.createComment(commentCreateDto);
        return new ResponseEntity<>("Comment successfully created", HttpStatus.CREATED);
    }
}

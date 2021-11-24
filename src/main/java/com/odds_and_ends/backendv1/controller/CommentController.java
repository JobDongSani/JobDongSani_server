package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.payload.request.CommentRequest;
import com.odds_and_ends.backendv1.payload.response.CommentListResponse;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public CommonResponse<String> saveComment(@PathVariable Long id,
                                              @RequestBody CommentRequest request) {
        return commentService.saveComment(id, request);
    }

    @GetMapping("/{id}")
    public CommonResponse<CommentListResponse> getComment(@PathVariable Long id) {
        return commentService.getCommentList(id);
    }
}

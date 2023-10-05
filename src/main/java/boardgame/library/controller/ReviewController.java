package boardgame.library.controller;

import boardgame.library.dto.request.ReviewRequestDto;
import boardgame.library.dto.response.ReviewDto;
import boardgame.library.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review management", description = "Endpoints for managing reviews")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/boardgames/{boardGameId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "Get all reviews", description = "Get all reviews by board game id")
    @PreAuthorize("hasAuthority('USER')")
    public List<ReviewDto> getAllReviewsByGameId(@PathVariable Long boardGameId) {
        return reviewService.findAllByBoardGameId(boardGameId);
    }

    @PostMapping("/new")
    @Operation(summary = "Add new review", description = "Add new review to board game")
    @PreAuthorize("hasAuthority('USER')")
    public ReviewDto addNewReview(Authentication authentication,
                                @RequestBody @Valid ReviewRequestDto review) {
        return reviewService.addNewReview(authentication, review);
    }
}

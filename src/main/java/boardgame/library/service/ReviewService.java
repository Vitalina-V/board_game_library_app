package boardgame.library.service;

import boardgame.library.dto.request.ReviewRequestDto;
import boardgame.library.dto.response.ReviewDto;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface ReviewService {
    ReviewDto addNewReview(Authentication authentication,
                   ReviewRequestDto requestDto);

    List<ReviewDto> findAllByBoardGameId(Long boardGameId);
}

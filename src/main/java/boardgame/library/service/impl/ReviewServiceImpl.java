package boardgame.library.service.impl;

import boardgame.library.dto.request.ReviewRequestDto;
import boardgame.library.dto.response.BoardGameDto;
import boardgame.library.dto.response.ReviewDto;
import boardgame.library.exception.EntityNotFoundException;
import boardgame.library.mapper.BoardGameMapper;
import boardgame.library.mapper.ReviewMapper;
import boardgame.library.model.BoardGame;
import boardgame.library.model.Review;
import boardgame.library.model.User;
import boardgame.library.repository.ReviewRepository;
import boardgame.library.repository.UserRepository;
import boardgame.library.service.BoardGameService;
import boardgame.library.service.ReviewService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;
    private final BoardGameService boardGameService;
    private final BoardGameMapper boardGameMapper;

    @Override
    public ReviewDto addNewReview(Authentication authentication, ReviewRequestDto requestDto) {
        User user = getUser(authentication);
        BoardGame boardGame = getBoardGame(requestDto.getBoardGameId());
        Review review = new Review();
        review.setCreationDate(LocalDateTime.now());
        review.setUser(user);
        review.setBoardGame(boardGame);
        review.setRate(requestDto.getRate());
        review.setTextReview(requestDto.getTextReview());
        reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDto> findAllByBoardGameId(Long boardGameId) {
        return reviewRepository.findAllByBoardGameId(boardGameId).stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    private User getUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Can not find user by email" + email));
    }

    private BoardGame getBoardGame(Long boardGameId) {
        BoardGameDto game = boardGameService.findById(boardGameId);
        return boardGameMapper.toModel(game);
    }
}

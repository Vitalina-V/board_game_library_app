package boardgame.library.repository;

import boardgame.library.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBoardGameId(Long boardGameId);
}

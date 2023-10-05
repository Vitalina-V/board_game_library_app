package boardgame.library.repository;

import boardgame.library.model.BoardGame;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    List<BoardGame> findAllByTitleContainsIgnoreCase(String title);
}

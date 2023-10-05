package boardgame.library.service;

import boardgame.library.dto.request.BoardGameRequestDto;
import boardgame.library.dto.response.BoardGameDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BoardGameService {
    BoardGameDto save(BoardGameRequestDto requestDto);

    List<BoardGameDto> findAll(Pageable pageable);

    BoardGameDto findById(Long id);

    List<BoardGameDto> findAllByTitleContainsIgnoreCase(String title);

    void deleteById(Long id);

    BoardGameDto update(Long id, BoardGameRequestDto requestDto);
}

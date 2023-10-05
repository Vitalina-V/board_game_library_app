package boardgame.library.service.impl;

import boardgame.library.dto.request.BoardGameRequestDto;
import boardgame.library.dto.response.BoardGameDto;
import boardgame.library.exception.EntityNotFoundException;
import boardgame.library.mapper.BoardGameMapper;
import boardgame.library.model.BoardGame;
import boardgame.library.repository.BoardGameRepository;
import boardgame.library.service.BoardGameService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardGameServiceImpl implements BoardGameService {
    private final BoardGameRepository boardGameRepository;
    private final BoardGameMapper boardGameMapper;

    @Override
    public BoardGameDto save(BoardGameRequestDto requestDto) {
        BoardGame boardGame = boardGameMapper.toModel(requestDto);
        return boardGameMapper.toDto(boardGameRepository.save(boardGame));
    }

    @Override
    public List<BoardGameDto> findAll(Pageable pageable) {
        return boardGameRepository.findAll(pageable).stream()
                .map(boardGameMapper::toDto)
                .toList();
    }

    @Override
    public BoardGameDto findById(Long id) {
        BoardGame boardGame = boardGameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can`t find board game by id " + id)
        );
        return boardGameMapper.toDto(boardGame);
    }

    @Override
    public List<BoardGameDto> findAllByTitleContainsIgnoreCase(String title) {
        return boardGameRepository.findAllByTitleContainsIgnoreCase(title).stream()
                .map(boardGameMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        boardGameRepository.deleteById(id);
    }

    @Override
    public BoardGameDto update(Long id, BoardGameRequestDto requestDto) {
        boardGameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cant update board game with id: " + id));
        BoardGame boardGame = boardGameMapper.toModel(requestDto);
        boardGame.setId(id);
        return boardGameMapper.toDto(boardGameRepository.save(boardGame));
    }
}

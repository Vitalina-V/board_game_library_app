package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.request.BoardGameRequestDto;
import boardgame.library.dto.response.BoardGameDto;
import boardgame.library.model.BoardGame;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BoardGameMapper {
    BoardGameDto toDto(BoardGame boardGame);

    BoardGame toModel(BoardGameRequestDto requestDto);

    BoardGame toModel(BoardGameDto boardGameDto);
}

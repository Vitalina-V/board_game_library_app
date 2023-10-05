package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.request.ReviewRequestDto;
import boardgame.library.dto.response.ReviewDto;
import boardgame.library.model.Review;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ReviewMapper {
    ReviewDto toDto(Review review);

    Review toModel(ReviewRequestDto requestDto);
}

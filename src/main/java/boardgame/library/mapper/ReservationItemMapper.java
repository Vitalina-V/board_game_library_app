package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.response.ReservationItemDto;
import boardgame.library.model.ReservationItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ReservationItemMapper {
    ReservationItemDto toDto(ReservationItem reservationItem);
}

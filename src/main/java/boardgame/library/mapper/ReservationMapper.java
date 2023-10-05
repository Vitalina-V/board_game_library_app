package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.response.ReservationDto;
import boardgame.library.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ReservationMapper {
    ReservationDto toDto(Reservation reservation);

    Reservation toModel(ReservationDto reservationDto);
}

package boardgame.library.service;

import boardgame.library.dto.request.ReservationRequestDto;
import boardgame.library.dto.response.ReservationDto;
import boardgame.library.dto.response.ReservationItemDto;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface ReservationService {
    ReservationDto create(Long id, ReservationRequestDto reservationRequestDto);

    List<ReservationDto> findAllReservations(Long id, Pageable pageable);

    Set<ReservationItemDto> findAllReservationItems(Long reservationId);

    ReservationItemDto findReservationItemById(Long reservationId, Long itemId);
}

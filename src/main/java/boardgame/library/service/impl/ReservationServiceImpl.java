package boardgame.library.service.impl;

import boardgame.library.dto.request.ReservationRequestDto;
import boardgame.library.dto.response.ReservationDto;
import boardgame.library.dto.response.ReservationItemDto;
import boardgame.library.exception.EntityNotFoundException;
import boardgame.library.mapper.ReservationItemMapper;
import boardgame.library.mapper.ReservationMapper;
import boardgame.library.model.Cart;
import boardgame.library.model.Reservation;
import boardgame.library.model.ReservationItem;
import boardgame.library.repository.CartRepository;
import boardgame.library.repository.ReservationRepository;
import boardgame.library.service.ReservationService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final CartRepository cartRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final ReservationItemMapper reservationItemMapper;

    @Override
    public ReservationDto create(Long id, ReservationRequestDto reservationRequestDto) {
        Cart cart = cartRepository.findCartByUserId(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find cart by id: " + id)
                );
        Reservation reservation = new Reservation();
        reservation.setReservationDateTime(reservationRequestDto.getReservationDateTime());
        reservation.setUser(cart.getUser());
        Set<ReservationItem> reservationItems = getReservationItemsFromCart(cart, reservation);
        reservation.setReservationItems(reservationItems);
        reservation.setReservationDateTime(reservationRequestDto.getReservationDateTime());
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationDto> findAllReservations(Long id, Pageable pageable) {
        return reservationRepository.findAllReservations(id).stream()
                .map(reservationMapper::toDto).toList();
    }

    @Override
    public Set<ReservationItemDto> findAllReservationItems(Long reservationId) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find reservation by id: " + reservationId)
                );
        return reservation.getReservationItems().stream()
                .map(reservationItemMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ReservationItemDto findReservationItemById(Long reservationId, Long itemId) {
        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find reservation by id: " + reservationId));
        return reservation.getReservationItems().stream()
                .filter(r -> r.getId().equals(itemId))
                .findFirst()
                .map(reservationItemMapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find item by id: " + itemId
                                + ", with reservation id:  " + reservationId)
                );
    }

    private Set<ReservationItem> getReservationItemsFromCart(Cart cart, Reservation reservation) {
        return cart.getCartItems().stream()
                .map(cartItem -> {
                    ReservationItem reservationItem = new ReservationItem();
                    reservationItem.setBoardGame(cartItem.getBoardGame());
                    reservationItem.setReservation(reservation);
                    return reservationItem;
                })
                .collect(Collectors.toSet());
    }
}

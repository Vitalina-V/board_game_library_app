package boardgame.library.controller;

import boardgame.library.dto.request.ReservationRequestDto;
import boardgame.library.dto.response.ReservationDto;
import boardgame.library.dto.response.ReservationItemDto;
import boardgame.library.model.User;
import boardgame.library.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Reservation management", description = "Endpoints for managing users reservations")
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(summary = "Place an reservation", description = "Place an reservation")
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ReservationDto create(Authentication authentication,
                                 @RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        User user = (User) authentication.getPrincipal();
        return reservationService.create(user.getId(), reservationRequestDto);
    }

    @Operation(summary = "Retrieve user's reservation history",
            description = "Retrieve user's reservation history")
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<ReservationDto> findAll(Authentication authentication,
                                        Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return reservationService.findAllReservations(user.getId(), pageable);
    }

    @Operation(summary = "Retrieve all reservationItems for a specific reservation",
            description = "Retrieve all reservationItems for a specific reservation")
    @GetMapping("/{reservationId}/items")
    @PreAuthorize("hasRole('USER')")
    public Set<ReservationItemDto> findAllReservationItems(@PathVariable Long reservationId) {
        return reservationService.findAllReservationItems(reservationId);
    }

    @Operation(summary = "Retrieve a specific ReservationItem within an reservation",
            description = "Retrieve a specific ReservationItem within an reservation")
    @GetMapping("/{reservationId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ReservationItemDto findReservationItemById(@PathVariable Long reservationId,
                                                  @PathVariable Long itemId) {
        return reservationService.findReservationItemById(reservationId, itemId);
    }
}

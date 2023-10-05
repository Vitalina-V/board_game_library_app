package boardgame.library.repository;

import boardgame.library.model.ReservationItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Long> {
    Optional<ReservationItem> findReservationItemByReservationIdAndId(Long reservationId, Long id);
}

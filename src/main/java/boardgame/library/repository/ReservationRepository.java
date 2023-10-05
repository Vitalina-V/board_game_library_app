package boardgame.library.repository;

import boardgame.library.model.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r LEFT JOIN FETCH r.reservationItems"
            + " LEFT JOIN FETCH r.user u WHERE u.id = :userId")
    List<Reservation> findAllReservations(long userId);

    @EntityGraph(attributePaths = "reservationItems")
    Optional<Reservation> findById(Long id);
}

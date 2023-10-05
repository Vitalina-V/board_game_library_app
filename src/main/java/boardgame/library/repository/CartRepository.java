package boardgame.library.repository;

import boardgame.library.model.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @EntityGraph(attributePaths = "cartItems")
    Optional<Cart> findCartByUserId(@Param("userId") Long userId);
}

package boardgame.library.controller;

import boardgame.library.dto.request.CartItemRequestDto;
import boardgame.library.dto.response.CartDto;
import boardgame.library.repository.CartItemRepository;
import boardgame.library.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Carts management",
        description = "Endpoints for managing carts")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add board game to cart", description = "Add board game to cart")
    @PreAuthorize("hasAuthority('USER')")
    public void addBoardGame(Authentication authentication,
                        @RequestBody @Valid CartItemRequestDto cartItemRequestDto) {
        cartService.addItemToCart(authentication, cartItemRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all games from cart", description = "Get all games from cart")
    public CartDto getAllCartItems(Authentication authentication) {
        return cartService.getAllCartItems(authentication);
    }

    @DeleteMapping("/cart-items/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete cart item", description = "Delete cart item")
    @PreAuthorize("hasAuthority('USER')")
    public void removeCartItemByBoardGameId(@PathVariable Long id) {
        cartItemRepository.deleteById(id);
    }
}

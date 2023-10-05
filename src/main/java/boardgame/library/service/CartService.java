package boardgame.library.service;

import boardgame.library.dto.request.CartItemRequestDto;
import boardgame.library.dto.response.CartDto;
import org.springframework.security.core.Authentication;

public interface CartService {
    void addItemToCart(Authentication authentication, CartItemRequestDto cartItemRequestDto);

    CartDto getAllCartItems(Authentication authentication);
}

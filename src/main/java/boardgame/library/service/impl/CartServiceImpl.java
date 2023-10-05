package boardgame.library.service.impl;

import boardgame.library.dto.request.CartItemRequestDto;
import boardgame.library.dto.response.CartDto;
import boardgame.library.exception.EntityNotFoundException;
import boardgame.library.mapper.CartItemMapper;
import boardgame.library.mapper.CartMapper;
import boardgame.library.model.BoardGame;
import boardgame.library.model.Cart;
import boardgame.library.model.CartItem;
import boardgame.library.model.User;
import boardgame.library.repository.BoardGameRepository;
import boardgame.library.repository.CartItemRepository;
import boardgame.library.repository.CartRepository;
import boardgame.library.repository.UserRepository;
import boardgame.library.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final BoardGameRepository boardGameRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addItemToCart(Authentication authentication,
                              CartItemRequestDto cartItemRequestDto) {
        User user = getUser(authentication);
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseGet(() -> registerNewCart(user));
        CartItem cartItemToUpdate = createNewCartItem(cartItemRequestDto, cart);
        cartItemRepository.save(cartItemToUpdate);
    }

    @Override
    public CartDto getAllCartItems(Authentication authentication) {
        User user = getUser(authentication);
        Cart cart = cartRepository.findCartByUserId(user.getId())
                .orElseGet(() -> registerNewCart(user));
        return cartMapper.toDto(cart);
    }

    private User getUser(Authentication authentication) {
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Can not find user by email" + email));
    }

    private Cart registerNewCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    private CartItem createNewCartItem(CartItemRequestDto cartItemRequestDto,
                                       Cart cart) {
        BoardGame boardGame = boardGameRepository
                .findById(cartItemRequestDto.getBoardGameId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can not find board game with id: "
                                + cartItemRequestDto.getBoardGameId()));
        CartItem cartItem = cartItemMapper.toModel(cartItemRequestDto);
        cartItem.setBoardGame(boardGame);
        cartItem.setCart(cart);
        return cartItem;
    }
}

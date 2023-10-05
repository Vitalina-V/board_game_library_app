package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.request.CartItemRequestDto;
import boardgame.library.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItem toModel(CartItemRequestDto cartItemRequestDto);
}

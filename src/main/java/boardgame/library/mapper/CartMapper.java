package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.response.CartDto;
import boardgame.library.model.Cart;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CartMapper {

    CartDto toDto(Cart cart);
}

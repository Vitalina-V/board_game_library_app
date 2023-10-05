package boardgame.library.mapper;

import boardgame.library.config.MapperConfig;
import boardgame.library.dto.request.UserRegistrationRequestDto;
import boardgame.library.dto.response.UserResponseDto;
import boardgame.library.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}

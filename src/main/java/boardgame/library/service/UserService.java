package boardgame.library.service;

import boardgame.library.dto.request.UserRegistrationRequestDto;
import boardgame.library.dto.response.UserResponseDto;
import boardgame.library.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}

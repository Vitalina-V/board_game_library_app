package boardgame.library.controller;

import boardgame.library.dto.request.UserLoginRequestDto;
import boardgame.library.dto.request.UserRegistrationRequestDto;
import boardgame.library.dto.response.UserLoginResponseDto;
import boardgame.library.dto.response.UserResponseDto;
import boardgame.library.exception.RegistrationException;
import boardgame.library.security.AuthenticationService;
import boardgame.library.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }
}

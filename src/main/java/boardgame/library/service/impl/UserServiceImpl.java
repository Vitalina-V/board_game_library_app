package boardgame.library.service.impl;

import boardgame.library.dto.request.UserRegistrationRequestDto;
import boardgame.library.dto.response.UserResponseDto;
import boardgame.library.exception.RegistrationException;
import boardgame.library.mapper.UserMapper;
import boardgame.library.model.Role;
import boardgame.library.model.User;
import boardgame.library.repository.RoleRepository;
import boardgame.library.repository.UserRepository;
import boardgame.library.service.UserService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        Role userRole = roleRepository.findRoleByName(Role.RoleName.USER)
                .orElseThrow(() -> new RegistrationException("Can't find role by name"));
        Set<Role> defaultUserRoleSet = new HashSet<>();
        defaultUserRoleSet.add(userRole);
        user.setRoles(defaultUserRoleSet);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}

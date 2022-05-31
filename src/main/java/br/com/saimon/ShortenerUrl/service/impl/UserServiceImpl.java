package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.DTO.NewUserDto;
import br.com.saimon.ShortenerUrl.domain.User;
import br.com.saimon.ShortenerUrl.repository.UserRepository;
import br.com.saimon.ShortenerUrl.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(ValidationException::new);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(NewUserDto user) {

        User newUser = User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(Arrays.asList(User.ROLE.USER))
                .createdAt(new Date())
                .lastLogin(null)
                .build();
        log.info("Saving new User to the Database");
        return userRepository.save(newUser);
    }

    @Override
    public User loadUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(ValidationException::new);
        log.info("I found you {} ", user.getName());
        return user;
    }

    @Override
    public User addRole(String id) {
        User user = userRepository.findById(id).orElseThrow(ValidationException::new);
        user.getRoles().add(User.ROLE.ADMIN);

        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


}

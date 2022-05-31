package br.com.saimon.ShortenerUrl.service;

import br.com.saimon.ShortenerUrl.DTO.NewUserDto;
import br.com.saimon.ShortenerUrl.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(NewUserDto user);
    User loadUser(String username);
    User addRole(String id);
    List<User> getUsers();
}

package br.com.saimon.ShortenerUrl.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDto {
    private String name;
    private String username;
    private String password;
}

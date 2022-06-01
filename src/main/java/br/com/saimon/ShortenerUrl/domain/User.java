package br.com.saimon.ShortenerUrl.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Builder
@Document
public class User {
    @Id
    private String id;
    private String name;
    @NotBlank
    @Indexed(unique = true)
    private String username;
    @NotNull
    private String password;
    private Date createdAt;
    private Date lastLogin;
    private Long loginCount;
    private Collection<ROLE> roles = new ArrayList<>();

    public enum ROLE {
        USER,
        ADMIN
    }
}

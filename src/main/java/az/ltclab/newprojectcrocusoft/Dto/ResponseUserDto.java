package az.ltclab.newprojectcrocusoft.Dto;

import az.ltclab.newprojectcrocusoft.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ResponseUserDto  {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime timeLogin;
}

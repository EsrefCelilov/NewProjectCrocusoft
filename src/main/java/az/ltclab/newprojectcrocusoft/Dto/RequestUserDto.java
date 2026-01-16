package az.ltclab.newprojectcrocusoft.Dto;

import az.ltclab.newprojectcrocusoft.Entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserDto {

        @NotBlank(message = "Username boş ola bilməz")
        private String username;

        @Email(message = "Email düzgün formatda deyil")
        @NotBlank
        private String email;

        @Size(min = 6, message = "Password minimum 6 simvol olmalıdır")
        private String password;

        private Role role;
    }




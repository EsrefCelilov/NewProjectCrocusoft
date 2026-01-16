package az.ltclab.newprojectcrocusoft.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;


    @NotNull(message = "Email bos ola bilmez")
    private String email;


    @Size(min = 6, message = "Password ən azı 6 elementdən ibarət olmalıdır")
    @Column(nullable = false)
    @NotNull(message = "Password boş ola bilməz")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Role boş ola bilmez")
    private Role role;

    private LocalDateTime timeLogin;





}

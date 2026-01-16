package az.ltclab.newprojectcrocusoft.Test;

import az.ltclab.newprojectcrocusoft.Repository.UserRepository;
import az.ltclab.newprojectcrocusoft.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {


    private UserRepository userRepository;


    private ModelMapper modelMapper;


    private PasswordEncoder passwordEncoder;


    private UserService userService;
}

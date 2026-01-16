package az.ltclab.newprojectcrocusoft.Service;
import az.ltclab.newprojectcrocusoft.Dto.RequestUserDto;
import az.ltclab.newprojectcrocusoft.Dto.ResponseUserDto;
import az.ltclab.newprojectcrocusoft.Entity.Role;
import az.ltclab.newprojectcrocusoft.Entity.UserEntity;
import az.ltclab.newprojectcrocusoft.Exeption.DuplicateUserException;
import az.ltclab.newprojectcrocusoft.Exeption.InvalidException;
import az.ltclab.newprojectcrocusoft.Exeption.UserNotFoundException;
import az.ltclab.newprojectcrocusoft.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
        private final UserRepository userRepository;
        private final ModelMapper modelMapper;
        private final PasswordEncoder passwordEncoder;

    public void validatePassword(String password)  {
        if (password == null || password.length() < 6) {
            throw new InvalidException("Parol ən azı 6 xarakterdən ibarət olmalıdır");
        }
    }

    public void validateEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidException("Emailin formatı düzgün deyil!");
        }
    }

        public List<ResponseUserDto> read(){
        List<UserEntity> all = userRepository.findAll();
        return all.stream().map(userEntity -> modelMapper.map(userEntity, ResponseUserDto.class)).collect(Collectors.toList());
    }


    public String create(RequestUserDto requestUserDto)throws  DuplicateUserException {
        validateEmail(requestUserDto.getEmail());
        validatePassword(requestUserDto.getPassword());


        if(userRepository.existsByUsername(requestUserDto.getUsername())) {
            throw new DuplicateUserException("İstifadəçi artıq mövcuddur.");
        }
        if(userRepository.existsByEmail(requestUserDto.getEmail())) {
            throw new DuplicateUserException("Bu email artıq qeydiyyatdan keçib");
        }
        if (requestUserDto.getRole()==null){
            requestUserDto.setRole(Role.USER);
        }
        UserEntity userEntity = dtoToEntity(requestUserDto);
        userEntity.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
        userEntity.setTimeLogin(LocalDateTime.now());
        userRepository.save(userEntity);
        return "Yeni istifadəçi";
    }
        public ResponseUserDto getById(Long id)throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("İstifadəçi tapılmadı"));
        return modelMapper.map(userEntity, ResponseUserDto.class);
    }

    public String update(Long id, RequestUserDto dto) throws UserNotFoundException {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("İstifadəçi tapılmadı"));

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        String encodedPassword = userEntity.getPassword();
        modelMapper.map(dto, userEntity);
        userEntity.setPassword(encodedPassword);

        userRepository.save(userEntity);
        return "İstifadəçi məlumatları yeniləndi";
    }

    public String delete(Long id) throws UserNotFoundException {
            UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Istifadəçi tapılmadı"));
            userRepository.delete(userEntity);
            return "İstifadəçi silindi";
        }


            public UserEntity dtoToEntity(RequestUserDto requestUserDto){
            return modelMapper.map(requestUserDto,UserEntity.class);
}}

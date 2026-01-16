package az.ltclab.newprojectcrocusoft.Controller;

import az.ltclab.newprojectcrocusoft.Dto.RequestUserDto;
import az.ltclab.newprojectcrocusoft.Dto.ResponseUserDto;
import az.ltclab.newprojectcrocusoft.Exeption.DuplicateUserException;
import az.ltclab.newprojectcrocusoft.Exeption.UserNotFoundException;
import az.ltclab.newprojectcrocusoft.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


        @GetMapping("/read")
        public ResponseEntity<List<ResponseUserDto>>read(){
            return ResponseEntity.ok(userService.read());
    }


        @GetMapping("/getId/{id}")
        public ResponseEntity<ResponseUserDto> getById(@PathVariable Long id) throws UserNotFoundException {
            return ResponseEntity.ok(userService.getById(id));
        }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody RequestUserDto dto) throws DuplicateUserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }


        @PutMapping("/update/{id}")
        public ResponseEntity<String> update(
                @PathVariable Long id,
                @Valid @RequestBody RequestUserDto dto) throws DuplicateUserException {
            return ResponseEntity.ok(userService.update(id, dto));
        }


        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) throws UserNotFoundException {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }



    }














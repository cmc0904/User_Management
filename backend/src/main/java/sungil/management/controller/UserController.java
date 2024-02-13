package sungil.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sungil.management.domain.ResponseDto;
import sungil.management.domain.Role;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.domain.User;
import sungil.management.form.LoginForm;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.service.user.UserSerivce;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserSerivce userSerivce;
    private final JwtTokenValidator jwtTokenValidator;

    @Autowired
    public UserController(UserSerivce userSerivce, JwtTokenValidator jwtTokenValidator) {
        this.userSerivce = userSerivce;
        this.jwtTokenValidator = jwtTokenValidator;
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userSerivce.getAllUsers());
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<User>> getAllAdmins() {
        return ResponseEntity.ok(userSerivce.getAllAdmins());
    }

    @GetMapping("/getUserByUserId")
    public ResponseEntity<?> getUserById(@RequestHeader("Authorization") String authorizationHeader, String userId) {
        return ResponseEntity.ok(userSerivce.getUserById(userId == null ? jwtTokenValidator.getUserIdFromToken(jwtTokenValidator.extractJwtToken(authorizationHeader)) : userId));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody  User user) {
        try {
            return ResponseEntity.ok(userSerivce.register(user));
        } catch (DuplicateUserExecption e) {
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginForm loginForm) {
        try {
            return ResponseEntity.ok(new ResponseDto<Map<?, ?>>("Login", userSerivce.login(loginForm)));
        } catch (NotFoundUserExecption e) {
            return ResponseEntity.ok(new ResponseDto<String>(e.getMessage(), null));
        }
    }

    @PostMapping("/addRole")
    public Map<String, Object> addRole(@RequestBody Role role) {
        return userSerivce.addRole(role);
    }

}

package sungil.management.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sungil.management.domain.Role;
import sungil.management.domain.User;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.form.LoginForm;
import sungil.management.jwt.JwtTokenProvider;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.repository.users.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserSerivce {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, JwtTokenValidator jwtTokenValidator) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenValidator = jwtTokenValidator;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<User> getAllAdmins() {
        return userRepository.getAllAdmins();
    }

    public Map<String, ?> login(LoginForm loginForm) throws NotFoundUserExecption {
        Optional<User> findUser = userRepository.getUserByUserIdAndPassword(loginForm.getUserId(), loginForm.getPassword());
        Map<String, Object> map = new HashMap<>();

        if(findUser.isPresent()) {
            User user = findUser.get();
            String tk = jwtTokenProvider.generateToken(user, userRepository.getRoleByUserId(user.getUserId()));
            map.put("token", tk);
            map.put("roles", jwtTokenValidator.getUserRolesFromToken(tk));

            return map;
        } else {
            throw new NotFoundUserExecption();
        }
    }

    public Map<String, String> register(User user) throws DuplicateUserExecption {
        Optional<User> findUser = userRepository.getUserByUserId(user.getUserId());
        Map<String, String> map = new HashMap<>();

        if (findUser.isEmpty()) {
            userRepository.insertUser(user);
            map.put("result", "REGISTER_COMPLETE");
        } else {
            throw new DuplicateUserExecption();
        }

        return map;
    }

    @Override
    public Map<String, ?> getUserById(String id) {
        Optional<User> findUser = userRepository.getUserByUserId(id);

        Map<String, Object> map = new HashMap<>();
        map.put("result" , findUser.isPresent() ? findUser.get() : "CAN_NOT_FOUND_USER");

        return map;
    }

    @Override
    public Map addRole(Role role) {
        Map<String, Object> map = new HashMap<>();
        try {
            userRepository.addRole(role);
            map.put("result", "ADD_ROLE_COMPLETE");
        } catch (Exception e) {
            map.put("result" , "CAN_NOT_ADD_ROLE");
        }


        return map;
    }
}

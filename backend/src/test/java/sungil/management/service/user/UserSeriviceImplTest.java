package sungil.management.service.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sungil.management.domain.Gender;
import sungil.management.domain.User;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.form.LoginForm;
import sungil.management.jwt.JwtTokenValidator;
import sungil.management.repository.users.MyBatisUserRepository;

import java.util.Date;
import java.util.Map;


@SpringBootTest
class UserServiceImplTest {


    private final UserServiceImpl userService;
    private final JwtTokenValidator jwtTokenValidator;
    private final MyBatisUserRepository userRepository;

    @Autowired
    public UserServiceImplTest(UserServiceImpl userService, JwtTokenValidator jwtTokenValidator, MyBatisUserRepository userRepository) {
        this.userService = userService;
        this.jwtTokenValidator = jwtTokenValidator;
        this.userRepository = userRepository;
    }


    @Test
    void getAllUsers() {
        Assertions.assertThat(userService.getAllUsers()).isNotNull();
    }

    @Test
    void getAllAdmins() {
        Assertions.assertThat(userService.getAllAdmins()).isNotNull();
    }

    @Test
    void login() throws NotFoundUserExecption {
        LoginForm loginForm = new LoginForm("anscks2350A", "andy2350");


        Map<String, ?> result = userService.login(loginForm);

        Assertions.assertThat(jwtTokenValidator.validateToken((String) result.get("token"))).isEqualTo(true);
    }

    @Test
    void notFoundLogin(){
        LoginForm loginForm = new LoginForm("", "");


        Map<String, ?> result = null;
        try {
            result = userService.login(loginForm);
        } catch (NotFoundUserExecption e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("NOT_FOUND_USER");
        }


    }

    @Test
    void register() throws DuplicateUserExecption {

        User user = new User("test01", "test01", "test01", "test0101@email.com", "성남시", "01035876350", Gender.F, new Date());

        userService.register(user);

        User findUser = (User) userService.getUserById("test01").get("result");

        userRepository.deleteUserByUserId("test01");

        Assertions.assertThat(user.getUserId()).isEqualTo(findUser.getUserId());

    }

    @Test
    void dulicateregister() {

        User user = new User("test01", "test01", "test01", "test0101@email.com", "성남시", "01035876350", Gender.F, new Date());
        User user1 = new User("test01", "test01", "test01", "test0101@email.com", "성남시", "01035876350", Gender.F, new Date());


        try {
            userService.register(user);
            userService.register(user1);
        } catch (DuplicateUserExecption e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("DUPLICATE_USER");
        }

        userRepository.deleteUserByUserId("test01");

    }


}
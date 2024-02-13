package sungil.management.service.user;

import sungil.management.domain.Role;
import sungil.management.domain.User;
import sungil.management.execption.DuplicateUserExecption;
import sungil.management.execption.NotFoundUserExecption;
import sungil.management.form.LoginForm;

import java.util.List;
import java.util.Map;

public interface UserSerivce {
    List<User> getAllUsers();

    List<User> getAllAdmins();
    Map<String, ?> login(LoginForm loginForm) throws NotFoundUserExecption;
    Map<String, String> register(User user) throws DuplicateUserExecption;
    Map<String, ?> getUserById(String id);
    Map<String, Object> addRole(Role role);
}

package sungil.management.repository.users;

import org.apache.ibatis.annotations.*;
import sungil.management.domain.Role;
import sungil.management.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MyBatisUserRepository extends UserRepository {
    @Select("SELECT * FROM stn_users")
    List<User> getAllUsers();

    @Select("SELECT u.* FROM stn_users u, stn_roles r where u.userId = r.userId and r.role = 'Admin'")
    List<User> getAllAdmins();

    @Select("SELECT * FROM stn_users where userId = #{userId}")
    Optional<User> getUserByUserId(String userId);

    @Select("SELECT * FROM stn_users where userId = #{userId} and password = #{password}")
    Optional<User> getUserByUserIdAndPassword(String userId, String password);

    @Select("SELECT role FROM stn_roles where userId = #{userId}")
    List<String> getRoleByUserId(String userId);

    @Insert("INSERT INTO stn_users(userId, password, name, email, address, phone, gender, createAt) VALUES(#{userId}, #{userPassword}, #{userName}, #{userEmail}, #{userAddress}, #{userPhone}, #{userGender}, #{createAt})")
    void insertUser(User user);

    @Insert("INSERT INTO stn_roles(userId, role) VALUES(#{userId}, #{role})")
    void addRole(Role role);

    @Delete("delete from stn_users where userId = #{userId}")
    void deleteUserByUserId(String userId);
}

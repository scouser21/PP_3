package boot.springboot.dao;


import boot.springboot.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();

    User getUserByUserName(String userName);

    User getUserById(int id);
}
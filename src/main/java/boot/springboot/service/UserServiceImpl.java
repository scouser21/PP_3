package boot.springboot.service;

import boot.springboot.dao.UserDao;
import boot.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void removeUserById(int id) {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}

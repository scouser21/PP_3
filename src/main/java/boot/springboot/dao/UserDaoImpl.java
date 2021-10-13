package boot.springboot.dao;

import boot.springboot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public UserDaoImpl() {

    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserByUserName(String userName) {
        List<User> list = entityManager.createQuery("from User where username = '" + userName + "'", User.class).getResultList();
        if (list.size() > 0){
            return entityManager.createQuery("from User where username = '" + userName + "'", User.class).getSingleResult();
        } else {
            return new User();
        }
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

}

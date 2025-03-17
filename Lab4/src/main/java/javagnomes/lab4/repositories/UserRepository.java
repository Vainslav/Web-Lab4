package javagnomes.lab4.repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javagnomes.lab4.modelObjects.ShotEntity;
import javagnomes.lab4.modelObjects.UserEntity;
import javagnomes.lab4.security.PasswordHasher;

@Stateless
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public UserEntity getUserByName(String username) {
        UserEntity user = em.find(UserEntity.class, username);
        return user;
    }

    public void add(UserEntity user) {
        em.persist(user);
        em.flush();
    }

    public UserEntity authenticate(String username, String password){
        UserEntity user = em.find(UserEntity.class, username);
        if (user == null || PasswordHasher.verify(password, user.getHashedPassword(), user.getSalt())) {
            return null;
        }
        return user;
    }
}

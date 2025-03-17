package javagnomes.lab4.repositories;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javagnomes.lab4.exceptions.UsernameException;
import javagnomes.lab4.modelObjects.ShotEntity;
import javagnomes.lab4.modelObjects.UserEntity;

import java.util.List;

@Stateless
public class ShotsRepository {
    @EJB
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    public void update(ShotEntity shot) {
        em.persist(shot);
        em.flush();
    }

    public void add(ShotEntity shot, String username) {
        UserEntity user = userRepository.getUserByName(username);
        if (user == null) {
            throw new UsernameException("User already exists");
        }
        shot.setOwner(user);
        em.persist(shot);
        em.flush();
    }

    public List<ShotEntity> findAllByOwner(UserEntity user) {
        String query = "select s from ShotEntity s where s.owner = :user";
        return em.createQuery(query, ShotEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void removeAllShotsOwnedByUser(UserEntity user) {
        String query = "select s from ShotEntity s where s.owner = :user";
        em.createQuery(query)
                .setParameter("user", user).executeUpdate();
        em.flush();
    }
}

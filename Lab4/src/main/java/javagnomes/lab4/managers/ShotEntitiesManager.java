package javagnomes.lab4.managers;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import javagnomes.lab4.modelObjects.ShotEntity;
import javagnomes.lab4.repositories.ShotsRepository;
import javagnomes.lab4.repositories.UserRepository;
import javagnomes.lab4.security.JwtManager;

import java.util.List;

@Stateless
@Transactional
public class ShotEntitiesManager {
    @EJB
    private ShotsRepository shotsRepository;

    @EJB
    private UserRepository userRepository;

    public void addShot(ShotEntity shot, String username) {
        shotsRepository.add(shot, username);
    }

    public void clearShots(String username) {
        shotsRepository.removeAllShotsOwnedByUser(userRepository.getUserByName(username));
    }

    public List<ShotEntity> getAllShots(String username) {
        return shotsRepository.findAllByOwner(userRepository.getUserByName(username));
    }
}

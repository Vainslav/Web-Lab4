package javagnomes.lab4.managers;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import javagnomes.lab4.exceptions.AuthException;
import javagnomes.lab4.exceptions.UsernameException;
import javagnomes.lab4.modelObjects.UserEntity;
import javagnomes.lab4.repositories.UserRepository;
import javagnomes.lab4.security.JwtManager;
import javagnomes.lab4.security.PasswordHasher;
import org.apache.commons.lang3.tuple.Pair;

@Stateless
@Transactional
public class AuthenticationManager {
    @EJB
    private UserRepository userRepository;

    public void registerUser(String username, String password) {
        if (userRepository.getUserByName(username) != null){
            throw new UsernameException("USERNAME ALREADY OCCUPIED");
        }
        Pair<String,String> hashAndSalt = PasswordHasher.hash(password, null);
        UserEntity user = new UserEntity(username, hashAndSalt.getLeft(), hashAndSalt.getRight());
        userRepository.add(user);
    }

    public String login(String username, String password) {
        if (userRepository.getUserByName(username) == null){
            throw new UsernameException("USER WITH NAME NOT FOUND");
        }
        UserEntity user = userRepository.authenticate(username, password);
        if (user == null){
            throw new AuthException("AUTHENTICATION FAILED");
        }
        return JwtManager.createToken(user.getUsername(), user.getHashedPassword());
    }
}

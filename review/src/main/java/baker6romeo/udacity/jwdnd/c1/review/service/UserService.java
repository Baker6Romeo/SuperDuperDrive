package baker6romeo.udacity.jwdnd.c1.review.service;

import baker6romeo.udacity.jwdnd.c1.review.mapper.UserMapper;
import baker6romeo.udacity.jwdnd.c1.review.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUserNameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(user.getFirstName(), user.getLastName(), user.getPassword(), user.getSalt(),
                null, user.getUsername()));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}

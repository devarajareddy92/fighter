package unter.figter.service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unter.figter.entity.User;
import unter.figter.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final IgniteCache<Long, User> userCache;

    @Autowired
    public UserService(UserRepository userRepository, Ignite ignite) {
        this.userRepository = userRepository;
        this.userCache = ignite.getOrCreateCache("userCache");
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        User user = userCache.get(id);
        if (user == null) {
            user = userRepository.findById(id).orElse(null);
            if (user != null) {
                userCache.put(id, user);
            }
        }
        return user;
    }

    public User save(User user) {
        User savedUser = userRepository.save(user);
        userCache.put(savedUser.getId(), savedUser);
        return savedUser;
    }
}


package ie.atu.kebabeloginwebpage.service;

import ie.atu.kebabeloginwebpage.model.User;
import ie.atu.kebabeloginwebpage.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).isPresent();
    }

    public boolean register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public void deleteAccount(String username) {
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }

    public String resetPassword(String username, String currentPassword, String newPassword) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            return "Username not found";
        }
        User user = optUser.get();
        if (!user.getPassword().equals(currentPassword)) {
            return "Current password is incorrect";
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return null;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

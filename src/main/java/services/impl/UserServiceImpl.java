package services.impl;

import exceptions.UserNonUniqueException;
import model.User;
import repositories.UserRepository;
import services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> getAllLogins() {
        return userRepository.getAllUsers().stream().map(User::getLogin).toList();
    }

    @Override
    public void createUser(String login, String pass) {
        if ((login != null && !login.isEmpty())
                && (pass != null && !pass.isEmpty()))
            try {
                if (!verificationUser(login, pass)) {
                    userRepository.addUser(new User(login, pass));
                } else throw new UserNonUniqueException();
            } catch (UserNonUniqueException e) {
                e.getStackTrace("This user exists");
            }
        else throw new IllegalArgumentException();
    }

    @Override
    public boolean verificationUser(String login, String pass) {
        return userRepository
                .getAllUsers()
                .stream()
                .anyMatch(user -> user.getLogin().equals(login)
                        && user.getPassword().equals(pass));
    }
}

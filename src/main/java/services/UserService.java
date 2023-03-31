package services;

import java.util.List;

public interface UserService {

    List<String> getAllLogins();

    void createUser(String login, String pass);

    boolean verificationUser(String login, String pass);
}

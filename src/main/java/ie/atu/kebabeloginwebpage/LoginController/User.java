package ie.atu.kebabeloginwebpage.LoginController;

import lombok.Getter;

@Getter
public class User {
    private final String username;
    private final String email;
    private final String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
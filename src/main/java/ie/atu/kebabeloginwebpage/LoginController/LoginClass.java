package ie.atu.kebabeloginwebpage.LoginController;

import ie.atu.kebabeloginwebpage.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginClass {

    private final ArrayList<User> users = new ArrayList<>();

    public LoginClass() {
        // demo user for sprint review
        users.add(new User("demo", "demo@atu.ie", "demo123"));
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String email,
                             @RequestParam String password) {

        users.add(new User(username, email, password));
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password) {

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}



package ie.atu.kebabeloginwebpage.Controller;

import ie.atu.kebabeloginwebpage.model.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
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
                          @RequestParam String password,
                          HttpSession session) {

        for (User u : users) {
            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password)) {

                session.setAttribute("loggedInUser", username);
                return "redirect:/dashboard";
            }
        }

        return "redirect:/login?error=true";
    }


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "dashboard";
    }
}



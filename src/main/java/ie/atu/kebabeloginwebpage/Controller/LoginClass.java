package ie.atu.kebabeloginwebpage.Controller;

import ie.atu.kebabeloginwebpage.model.User;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;

@Controller
public class LoginClass {

    private final ArrayList<User> users = new ArrayList<>();

    public LoginClass() {
        // demo user for sprint review
        users.add(new User());
    }

    @PostMapping("/register")
    public String doRegister(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        users.add(user);
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


    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
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



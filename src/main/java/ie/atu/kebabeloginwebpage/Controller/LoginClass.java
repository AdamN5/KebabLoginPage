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
        users.add(new User("demo", "demo@atu.ie", "demo123"));
    }

    @PostMapping("/register")
    public String doRegister(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        users.add(user);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // removes logged-in user
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {

        for (User u : users) {
            if (username.equals(u.getUsername())
                    && password.equals(u.getPassword())) {

                session.setAttribute("loggedInUser", username);
                return "redirect:/dashboard";
            }
        }

        return "redirect:/login?error=true";
    }

    @PostMapping("/reset-password")
    public String doResetPassword(@RequestParam String username,
                                  @RequestParam String currentPassword,
                                  @RequestParam String newPassword,
                                  Model model) {

        User found = null;
        for (User u : users) {
            if (u.getUsername() != null && u.getUsername().equals(username)) {
                found = u;
                break;
            }
        }

        if (found == null) {
            model.addAttribute("error", "Username not found");
            return "reset-password";
        }

        if (found.getPassword() == null || !found.getPassword().equals(currentPassword)) {
            model.addAttribute("error", "Current password is incorrect");
            return "reset-password";
        }

        found.setPassword(newPassword);
        return "redirect:/login";
    }


    @GetMapping("/account")
    public String viewAccount(HttpSession session, Model model) {

        String username = (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return "redirect:/login";
        }

        for (User u : users) {
            if (u.getUsername().equals(username)) {
                model.addAttribute("username", u.getUsername());
                model.addAttribute("email", u.getEmail());
            }
        }

        return "account";
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

    @GetMapping("/reset-password")
    public String showResetPasswordPage() {
        return "reset-password";
    }

}



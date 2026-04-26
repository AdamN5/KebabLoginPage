package ie.atu.kebabeloginwebpage.Controller;

import ie.atu.kebabeloginwebpage.model.User;
import ie.atu.kebabeloginwebpage.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginClass {

    private final UserService userService;

    public LoginClass(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String doRegister(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!userService.register(user)) {
            model.addAttribute("error", "Username already taken");
            return "register";
        }
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/delete-account")
    public String deleteAccountPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        return "delete-account";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(HttpSession session) {
        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }
        userService.deleteAccount(username);
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {
        if (userService.login(username, password)) {
            session.setAttribute("loggedInUser", username);
            return "redirect:/dashboard";
        }
        return "redirect:/login?error=true";
    }

    @PostMapping("/reset-password")
    public String doResetPassword(@RequestParam String username,
                                  @RequestParam String currentPassword,
                                  @RequestParam String newPassword,
                                  Model model) {
        String error = userService.resetPassword(username, currentPassword, newPassword);
        if (error != null) {
            model.addAttribute("error", error);
            return "reset-password";
        }
        return "redirect:/login";
    }

    @GetMapping("/account")
    public String viewAccount(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return "redirect:/login";
        }
        userService.findByUsername(username).ifPresent(u -> {
            model.addAttribute("username", u.getUsername());
            model.addAttribute("email", u.getEmail());
        });
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

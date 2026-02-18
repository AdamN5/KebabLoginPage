package ie.atu.kebabeloginwebpage.LoginController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginClass {

    @GetMapping("/register")
    public String register() {
        return "dashboard";
    }
}



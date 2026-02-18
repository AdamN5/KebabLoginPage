package ie.atu.kebabeloginwebpage.webController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

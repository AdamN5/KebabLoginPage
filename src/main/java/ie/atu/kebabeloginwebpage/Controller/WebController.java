package ie.atu.kebabeloginwebpage.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

package framework.app.frontoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class HelloController {

    @GetMapping
    public String getHello() {
        return "Hello";
    }
}

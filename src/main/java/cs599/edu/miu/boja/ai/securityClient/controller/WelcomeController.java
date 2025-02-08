package cs599.edu.miu.boja.ai.securityClient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: secure
 * @Author: Temesgen D.
 * @Date: 9/7/24
 */

@RestController
@RequestMapping("api/v1")
public class WelcomeController {

    @GetMapping("welcome")
    public String greeting() {
        return "Hello, welcome!";
    }

    @GetMapping("goodbye")
    public String sayGoodbye() {
        return "Ciao!";
    }
}

package yakisuzu.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public UserDto get(@RequestParam(value = "mail", required = true) String mail) {
        return new UserDto(mail);
    }
}

package yakisuzu.admin;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@Validated
@RequestMapping(path = "/admin")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public UserDto get(@Valid
                       @Email @RequestParam(value = "email", required = true) String email) {
        return new UserDto(email);
    }
}

package yakisuzu.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @RequestMapping(method = RequestMethod.GET)
    public DemoDto greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new DemoDto("Hello " + name);
    }

    @AllArgsConstructor
    @Getter
    class DemoDto {
        private String message;
    }
}

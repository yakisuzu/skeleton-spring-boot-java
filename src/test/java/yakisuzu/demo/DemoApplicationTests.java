package yakisuzu.demo;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig
class DemoApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
        // throw new AssertionError("aaaaaaaaaaa");
    }
}

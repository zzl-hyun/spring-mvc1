package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class requestmapping {

    @RequestMapping(value = "hello-basic", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String helloBasic() {
        log.info("hello-basic");
        return "ok";
    }
}

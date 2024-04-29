package org.smc.firstservice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First Service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info("{}", header);
        return "Welcome to the First Service.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("server port : {}", request.getServerPort());
        return String.format("This is from First Service on PORT %s", request.getServerPort());
    }

}

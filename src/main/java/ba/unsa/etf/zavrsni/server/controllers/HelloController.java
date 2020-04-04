package ba.unsa.etf.zavrsni.server.controllers;

import ba.unsa.etf.zavrsni.server.responses.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public ResponseMessage sayHello() {
        return new ResponseMessage("Hello");
    }
}

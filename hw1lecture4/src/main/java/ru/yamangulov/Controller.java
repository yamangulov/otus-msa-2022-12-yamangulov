package ru.yamangulov;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/health/")
    public ResponseEntity<String> getHealth() {
        String body = "{\"status\": \"OK\"}";
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}

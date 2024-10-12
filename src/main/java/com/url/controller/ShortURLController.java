
package com.url.controller;

import com.url.service.ShortURLService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.url.api.model.ShortURLDTO;

@RestController
@Log4j2
@RequestMapping("/api/v1/redis/url")
public class ShortURLController {

    @Autowired
    private ShortURLService shortURLService;

    @PostMapping("/create")
    public ResponseEntity<ShortURLDTO> create(@RequestBody ShortURLDTO shortURL) {
        try {
            shortURL = shortURLService.create(shortURL);
            return ResponseEntity.ok(shortURL);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse(e));
        }
    }

    @GetMapping("/{alias}")
    public ResponseEntity<ShortURLDTO> get(@PathVariable String alias) {
        try {
            ShortURLDTO shortURL = shortURLService.get(alias);
            return ResponseEntity.ok(shortURL);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse(e));
        }
    }

    @GetMapping("/random")
    public ResponseEntity<ShortURLDTO> getRandom() {
        try {
            ShortURLDTO shortURL = shortURLService.getRandom();
            return ResponseEntity.ok(shortURL);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse(e));
        }
    }

    private ShortURLDTO errorResponse(Exception e) {
        return ShortURLDTO.builder()
                .message(e.getMessage())
                .build();
    }
}

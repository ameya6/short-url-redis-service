
package com.url.controller;

import com.url.model.ShortURL;
import com.url.service.ShortURLService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/v1/redis/url")
public class ShortURLController {

    @Autowired
    private ShortURLService shortURLService;

    @PostMapping("/create")
    public ResponseEntity<ShortURL> create(@RequestBody ShortURL shortURL) {
        shortURL = shortURLService.create(shortURL);
        return ResponseEntity.ok(shortURL);
    }

    @GetMapping("/{alias}")
    public ResponseEntity<ShortURL> get(@PathVariable String alias) {
        ShortURL shortURL = shortURLService.get(alias);
        return ResponseEntity.ok(shortURL);
    }
}

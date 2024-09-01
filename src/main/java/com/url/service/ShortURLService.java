package com.url.service;

import com.url.model.ShortURL;
import com.url.repository.ShortURLRedisRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Log4j2
public class ShortURLService {

    @Autowired
    private ShortURLRedisRepository shortURLRepository;

    public ShortURL create(ShortURL shortURL) {
        Assert.notNull(shortURL, "ShortURL object is empty");
        log.info("Saving short url {}", shortURL);
        return shortURLRepository.save(shortURL);
    }

    // To be removed
    public ShortURL get(String alias) {
        return shortURLRepository.findByAlias(alias);
    }
}

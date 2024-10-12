package com.url.service;

import com.url.model.ShortURL;
import com.url.repository.ShortURLRedisRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.url.api.model.ShortURLDTO;

@Service
@Log4j2
public class ShortURLService {

    @Autowired
    private ShortURLRedisRepository shortURLRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public ShortURLDTO create(ShortURLDTO shortURLDTO) {
        Assert.notNull(shortURLDTO, "ShortURL object is empty");
        ShortURL shortURL = toShortURL(shortURLDTO);
        shortURL = shortURLRepository.save(shortURL);
        saveRedisAlias(shortURL.getAlias());
        log.info("Saved short url {}", shortURL);
        return shortURLDTO;
    }

    public ShortURLDTO get(String alias) {
        ShortURL shortURL = shortURLRepository.findByAlias(alias);
        log.info("URL data, alias : {}, id : {}", alias, shortURL.getId());
        return toShortURLDTO(shortURL);
    }

    public ShortURLDTO getRandom() {
        String alias = redisTemplate.opsForSet().randomMember("alias");
        ShortURL shortURL = shortURLRepository.findByAlias(alias);
        if(shortURL == null)
            return ShortURLDTO.builder().alias(alias).message("No data exists").build();
        log.info("Random data, alias : {}, id : {}", alias, shortURL.getId());
        return toShortURLDTO(shortURL);
    }

    private Long saveRedisAlias(String alias) {
        return redisTemplate.opsForSet().add("alias", alias);
    }

    private ShortURL toShortURL(ShortURLDTO shortURLDTO) {
        return ShortURL.builder()
                .id(shortURLDTO.getId())
                .createdAt(shortURLDTO.getCreatedAt())
                .longURL(shortURLDTO.getLongURL())
                .alias(shortURLDTO.getAlias())
                .distributedId(shortURLDTO.getDistributedId())
                .expiry(shortURLDTO.getExpiry())
                .build();
    }

    private ShortURLDTO toShortURLDTO(ShortURL shortURL) {
        return ShortURLDTO.builder()
                .id(shortURL.getId())
                .createdAt(shortURL.getCreatedAt())
                .longURL(shortURL.getLongURL())
                .alias(shortURL.getAlias())
                .distributedId(shortURL.getDistributedId())
                .expiry(shortURL.getExpiry())
                .build();
    }
}

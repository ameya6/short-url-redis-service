package com.url.repository;

import com.url.model.ShortURL;
import org.springframework.data.repository.CrudRepository;

public interface ShortURLRedisRepository extends CrudRepository<ShortURL, String> {
    ShortURL findByAlias(String alias);
}

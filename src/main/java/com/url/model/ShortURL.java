package com.url.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "short_url", timeToLive = 86400 * 30)
public class ShortURL {
    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime expiry;
    private Long distributedId;
    private String longURL;
    @Indexed
    private String alias;

    @Override
    public String toString() {
        return "ShortURL{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", expiry=" + expiry +
                ", distributedId=" + distributedId +
                ", longURL='" + longURL + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}

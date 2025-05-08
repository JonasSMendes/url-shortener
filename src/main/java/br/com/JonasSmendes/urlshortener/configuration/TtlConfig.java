package br.com.JonasSmendes.urlshortener.configuration;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;

import java.time.Duration;

@Configuration
public class TtlConfig {

    @Autowired
    private MongoOperations mongoOperations;

    @PostConstruct
    public void initTTLIndex() {
        mongoOperations.indexOps("urls")
                .ensureIndex(new Index("expiresAt", org.springframework.data.domain.Sort.Direction.ASC)
                        .expire(Duration.ofSeconds(60)));
    }
}

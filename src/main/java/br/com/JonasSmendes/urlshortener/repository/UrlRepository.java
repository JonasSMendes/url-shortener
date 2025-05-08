package br.com.JonasSmendes.urlshortener.repository;

import br.com.JonasSmendes.urlshortener.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}

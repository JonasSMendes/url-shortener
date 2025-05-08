package br.com.JonasSmendes.urlshortener.controller;

import br.com.JonasSmendes.urlshortener.dto.UrlRequest;
import br.com.JonasSmendes.urlshortener.dto.UrlResponse;
import br.com.JonasSmendes.urlshortener.entities.UrlEntity;
import br.com.JonasSmendes.urlshortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
public class UrlController {

    @Autowired
    private final UrlRepository urlRepository;

    public UrlController(UrlRepository repository) {
        this.urlRepository = repository;
    }

    @PostMapping("/url")
    public ResponseEntity<UrlResponse> urlResponseResponseEntity (@RequestBody UrlRequest url,
                                                           HttpServletRequest httpServletRequest){
        String id;
        do {
          id =  RandomStringUtils.randomAlphabetic(5, 10);
        }while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, url.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = httpServletRequest.getRequestURL().toString().replace("url", id);

        return ResponseEntity.ok(new UrlResponse(redirectUrl));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getUrlShortener (@PathVariable("id") String id ){

        var urlId = urlRepository.findById(id);

        if (urlId.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(urlId.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
    }
}

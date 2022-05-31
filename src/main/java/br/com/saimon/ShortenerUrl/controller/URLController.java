package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.service.URLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/url")
public class URLController {

    private final URLService URLService;

    public URLController(URLService URLService) {
        this.URLService = URLService;
    }

    @GetMapping
    public ResponseEntity loadAll() {
        return ResponseEntity.ok(URLService.getAllUrl());
    }

    @GetMapping("/{id}")
    public ResponseEntity load(@PathVariable String id) {
        return ResponseEntity.ok(URLService.load(id));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ShorterURLDto shorterURL) {
        ShorterURL url = URLService.save(shorterURL);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/url").toUriString());
        return ResponseEntity.created(uri).body(url);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        URLService.delete(id);
        return ResponseEntity.accepted().build();
    }
}

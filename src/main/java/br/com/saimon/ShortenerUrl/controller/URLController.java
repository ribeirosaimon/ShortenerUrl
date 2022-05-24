package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.service.ServiceURL;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class URLController {

    private final ServiceURL serviceURL;

    public URLController(ServiceURL serviceURL) {
        this.serviceURL = serviceURL;
    }

    @GetMapping("/urls")
    public ResponseEntity loadAll() {
        return ResponseEntity.ok(serviceURL.getAllUrl());
    }

    @GetMapping("/{id}")
    public ResponseEntity load(@PathVariable String id){
        return ResponseEntity.ok(serviceURL.load(id));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ShorterURLDto shorterURL){
        serviceURL.save(shorterURL);
    }
}

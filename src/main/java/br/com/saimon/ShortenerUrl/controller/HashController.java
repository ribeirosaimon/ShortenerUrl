package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.service.URLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hash")
public class HashController {

    private final URLService URLService;

    public HashController(URLService URLService) {
        this.URLService = URLService;
    }

    @GetMapping("/{hash}")
    public ResponseEntity loadByHash(@PathVariable String hash) throws Exception {
        return ResponseEntity.ok(URLService.getUrlByHash(hash));
    }
}

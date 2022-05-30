package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.service.ServiceURL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hash")
public class ControllerHash {

    private final ServiceURL serviceURL;

    public ControllerHash(ServiceURL serviceURL) {
        this.serviceURL = serviceURL;
    }

    @GetMapping("/{hash}")
    public ResponseEntity loadByHash(@PathVariable String hash){
        return ResponseEntity.ok(serviceURL.getUrlByHash(hash));
    }
}

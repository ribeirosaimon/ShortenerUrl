package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.repository.URLRepository;
import br.com.saimon.ShortenerUrl.service.HashService;
import br.com.saimon.ShortenerUrl.service.URLService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class URLServiceImpl implements URLService {
    private final URLRepository urlRepository;
    private final HashService hashService;

    public URLServiceImpl(URLRepository urlRepository, HashService hashService) {
        this.urlRepository = urlRepository;
        this.hashService = hashService;
    }

    @Override
    public ShorterURL load(String id) {
        return urlRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<ShorterURL> getAllUrl() {
        return urlRepository.findAll();
    }

    @Override
    public ShorterURL save(ShorterURLDto shorterURL) {
        ShorterURL url = new ShorterURL();

        url.setUrl(shorterURL.getUrl());
        url.setCreatedAt(new Date());
        url.setExpired(false);
        url.setHash(hashService.createHash());
        url.setUserId("userId");

        return  urlRepository.save(url);
    }

    @Override
    public ShorterURL getUrlByHash(String hash) throws Exception {
        return urlRepository.findByHash(hash).orElseThrow(Exception::new);
    }

    @Override
    public void delete(String id) {
        ShorterURL loadUrl = this.load(id);
        urlRepository.delete(loadUrl);
    }
}

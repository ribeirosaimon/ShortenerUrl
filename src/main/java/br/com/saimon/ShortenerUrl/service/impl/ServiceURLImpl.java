package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.repository.URLRespository;
import br.com.saimon.ShortenerUrl.service.ServiceURL;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceURLImpl implements ServiceURL {
    private final URLRespository urlRespository;

    public ServiceURLImpl(URLRespository urlRespository) {
        this.urlRespository = urlRespository;
    }

    @Override
    public ShorterURL load(String id) {
        return urlRespository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<ShorterURL> getAllUrl() {
        return null;
    }

    @Override
    public ShorterURL save(ShorterURLDto shorterURL) {
        ShorterURL url = new ShorterURL();

        url.setUrl(shorterURL.getUrl());
        url.setCreatedAt(new Date());
        url.setExpired(false);
        url.setHash("hash");
        url.setUserId("userId");

        urlRespository.save(url);
        return url;
    }
}

package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.repository.RepositoryURL;
import br.com.saimon.ShortenerUrl.service.ServiceURL;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceURLImpl implements ServiceURL {
    private final RepositoryURL repositoryUrl;

    public ServiceURLImpl(RepositoryURL repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    @Override
    public ShorterURL load(String id) {
        return repositoryUrl.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<ShorterURL> getAllUrl() {
        return repositoryUrl.findAll();
    }

    @Override
    public ShorterURL save(ShorterURLDto shorterURL) {
        ShorterURL url = new ShorterURL();

        url.setUrl(shorterURL.getUrl());
        url.setCreatedAt(new Date());
        url.setExpired(false);
        url.setHash("hash");
        url.setUserId("userId");

        repositoryUrl.save(url);
        return url;
    }

    @Override
    public void delete(String id) {
        ShorterURL loadUrl = this.load(id);
        repositoryUrl.delete(loadUrl);
    }
}

package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.repository.RepositoryURL;
import br.com.saimon.ShortenerUrl.service.ServiceHash;
import br.com.saimon.ShortenerUrl.service.ServiceURL;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceURLImpl implements ServiceURL {
    private final RepositoryURL repositoryUrl;
    private final ServiceHash serviceHash;

    public ServiceURLImpl(RepositoryURL repositoryUrl, ServiceHash serviceHash) {
        this.repositoryUrl = repositoryUrl;
        this.serviceHash = serviceHash;
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
        url.setHash(serviceHash.createHash());
        url.setUserId("userId");

        return  repositoryUrl.save(url);
    }

    @Override
    public ShorterURL getUrlByHash(String hash) {
        return repositoryUrl.findByHash(hash);
    }

    @Override
    public void delete(String id) {
        ShorterURL loadUrl = this.load(id);
        repositoryUrl.delete(loadUrl);
    }
}

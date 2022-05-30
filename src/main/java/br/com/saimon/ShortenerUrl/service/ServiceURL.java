package br.com.saimon.ShortenerUrl.service;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;

import java.util.List;

public interface ServiceURL {
    List<ShorterURL> getAllUrl();

    ShorterURL load(String id);

    ShorterURL save(ShorterURLDto shorterURL);

    ShorterURL getUrlByHash(String hash);

    void delete(String id);


}

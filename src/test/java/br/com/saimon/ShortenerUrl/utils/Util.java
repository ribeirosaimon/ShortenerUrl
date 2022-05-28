package br.com.saimon.ShortenerUrl.utils;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import org.bson.types.ObjectId;

import java.util.Date;

public class Util {
    public static String URL = "http://teste.com.br";
    public static String HASH = String.valueOf(URL.hashCode());
    public static String ID = new ObjectId().toString();

    public static ShorterURL newUrl() {
        ShorterURL url = new ShorterURL();
        url.setUrl(URL);
        url.setHash(HASH);
        url.setExpired(false);
        System.out.println();
        url.setId(ID);
        url.setCreatedAt(new Date());

        return url;
    }

    public static ShorterURLDto newUrlDto(){
        ShorterURLDto url = new ShorterURLDto();
        url.setUrl(URL);
        return url;
    }
}

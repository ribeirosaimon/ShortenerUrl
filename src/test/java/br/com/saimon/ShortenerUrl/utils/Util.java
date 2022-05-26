package br.com.saimon.ShortenerUrl.utils;

import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import org.bson.types.ObjectId;

import java.util.Date;

public class Util {
    public static String URL = "http://teste.com.br";
    public static String HASH = String.valueOf(URL.hashCode());

    public static ShorterURL newUrl() {
        ShorterURL url = new ShorterURL();
        url.setUrl(URL);
        url.setHash(HASH);
        url.setExpired(false);
        url.setUserId(new ObjectId().toString());
        url.setCreatedAt(new Date());

        return url;
    }
}

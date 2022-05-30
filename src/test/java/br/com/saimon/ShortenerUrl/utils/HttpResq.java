package br.com.saimon.ShortenerUrl.utils;

import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class HttpResq {


    public static MockHttpServletRequestBuilder httpGetAll(String url) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        return request;
    }

    public static MockHttpServletRequestBuilder load(String url, String id) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(url.concat(id))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        return request;
    }

    public static RequestBuilder save(String url, ShorterURL shorterURL) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(shorterURL);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(json);

        return request;
    }

    public static RequestBuilder delete(String url, String id) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(url.concat(id))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        return request;
    }

    public static RequestBuilder getHash(String url, String hash) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(url.concat(hash))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        return request;
    }
}

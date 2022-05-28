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
    public static final String URL = "http://localhost:8080/";

    public static MockHttpServletRequestBuilder httpGetAll() {
        String baseUrl = URL.concat("urls");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(baseUrl)
                .accept(MediaType.APPLICATION_JSON);
        return request;
    }

    public static MockHttpServletRequestBuilder load(String path, String id) {
        String url = URL.concat(path).concat(id);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON);
        return request;
    }

    public static RequestBuilder save(ShorterURL shorterURL) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(shorterURL);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(json);

        return request;
    }

    public static RequestBuilder delete(String id) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(URL.concat(id))
                .with(SecurityMockMvcRequestPostProcessors.csrf());

        return request;
    }
}

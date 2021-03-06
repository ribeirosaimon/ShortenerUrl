package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.service.URLService;
import br.com.saimon.ShortenerUrl.utils.HttpResq;
import br.com.saimon.ShortenerUrl.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {URLController.class, HashController.class})
@DisplayName("Controller Test")
class URLControllerTest {
    public static final String URL = "http://localhost:8080/";
    public static final String HASH_URL = "hash/";
    public static final String SHORTER_URL = "url/";

    ShorterURL shorterURL = Util.newUrl();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private URLService URLService;

    @BeforeEach
    void setUp() throws Exception {
        BDDMockito.given(URLService.getAllUrl()).willReturn(List.of(this.shorterURL));
        BDDMockito.given(URLService.load(ArgumentMatchers.anyString())).willReturn(this.shorterURL);
        BDDMockito.given(URLService.save(ArgumentMatchers.any(ShorterURLDto.class))).willReturn(Util.newUrl());
        BDDMockito.given(URLService.getUrlByHash(ArgumentMatchers.anyString())).willReturn(this.shorterURL);
        BDDMockito.doNothing().when(URLService).delete(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("Not authenticated")
    void loadWithNotAuthenticated() throws Exception {
        this.mockMvc
                .perform(HttpResq.httpGetAll(URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    @DisplayName("Get all URLS in api")
    void loadAll() throws Exception {
        this.mockMvc.perform(HttpResq.httpGetAll(URL.concat(SHORTER_URL))).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    @WithMockUser
    @DisplayName("Get only one URL in api")
    void load() throws Exception {

        this.mockMvc.perform(HttpResq.load(URL.concat(SHORTER_URL), this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("url").value(this.shorterURL.getUrl()));
    }

    @Test
    @WithMockUser
    @DisplayName("Save one URL in api")
    void save() throws Exception {
        this.mockMvc.perform(HttpResq.save(URL.concat(SHORTER_URL), this.shorterURL))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("url").value(this.shorterURL.getUrl()));
    }

    @Test
    @WithMockUser
    @DisplayName("Delete one URL in api")
    void delete() throws Exception {
        this.mockMvc.perform(HttpResq.delete(URL.concat(SHORTER_URL), this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    @WithMockUser
    @DisplayName("Get one URL in api by Hash")
    void getByHash() throws Exception {
        this.mockMvc.perform(HttpResq.getHash(URL.concat(HASH_URL), this.shorterURL.getHash()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("url").value(this.shorterURL.getUrl()));
    }
}

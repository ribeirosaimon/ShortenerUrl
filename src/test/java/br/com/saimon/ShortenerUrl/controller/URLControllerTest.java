package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.service.ServiceURL;
import br.com.saimon.ShortenerUrl.utils.HttpResq;
import br.com.saimon.ShortenerUrl.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ControllerURL.class)
@DisplayName("Controller Test")
@AutoConfigureMockMvc
class ControllerURLTest {

    ShorterURL shorterURL = Util.newUrl();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceURL serviceURL;

    @BeforeEach
    void setUp() throws Exception {
        BDDMockito.given(serviceURL.getAllUrl()).willReturn(List.of(this.shorterURL));
        BDDMockito.given(serviceURL.load(ArgumentMatchers.anyString())).willReturn(this.shorterURL);
        BDDMockito.given(serviceURL.save(ArgumentMatchers.any(ShorterURLDto.class))).willReturn(Util.newUrl());
        BDDMockito.doNothing().when(serviceURL).delete(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("Not authenticated")
    void loadWithNotAuthenticated() throws Exception {
        this.mockMvc.perform(HttpResq.httpGetAll()).andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser
    @DisplayName("Get all URLS in api")
    void loadAll() throws Exception {
        this.mockMvc.perform(HttpResq.httpGetAll()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    @WithMockUser
    @DisplayName("Get only one URL in api")
    void load() throws Exception {

        this.mockMvc.perform(HttpResq.load("/", this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("url").value(this.shorterURL.getUrl()));
    }

    @Test
    @WithMockUser
    @DisplayName("Save one URL in api")
    void save() throws Exception {
        this.mockMvc.perform(HttpResq.save(this.shorterURL))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("url").value(this.shorterURL.getUrl()));
    }

    @Test
    @WithMockUser
    @DisplayName("Delete one URL in api")
    void delete() throws Exception {
        this.mockMvc.perform(HttpResq.delete(this.shorterURL.getId()))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}
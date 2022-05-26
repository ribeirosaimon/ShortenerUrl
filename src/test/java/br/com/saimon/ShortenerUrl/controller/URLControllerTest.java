package br.com.saimon.ShortenerUrl.controller;

import br.com.saimon.ShortenerUrl.utils.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class URLControllerTest {

    @Test
    void loadAll() {

    }

    @Test
    void load() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }
}
package br.com.saimon.ShortenerUrl.service.impl;

import br.com.saimon.ShortenerUrl.DTO.ShorterURLDto;
import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.service.URLService;
import br.com.saimon.ShortenerUrl.utils.Util;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Service Test")
class URLServiceImplTest {

    ShorterURL shorterURL = Util.newUrl();

    @MockBean
    private URLService URLService;

    @BeforeEach
    public void setUp() {
        BDDMockito.when(URLService.getAllUrl()).thenReturn(List.of(shorterURL));
        BDDMockito.when(URLService.load(ArgumentMatchers.anyString())).thenReturn(shorterURL);
        BDDMockito.when(URLService.save(ArgumentMatchers.any(ShorterURLDto.class))).thenReturn(shorterURL);
        BDDMockito.doNothing().when(URLService).delete(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("load URL in Service")
    void load() {
        ShorterURL load = URLService.load(shorterURL.getId());
        Assertions.assertThat(load.getId().equals(shorterURL.getId())).isTrue();
        Assertions.assertThat(!load.getId().equals("any String")).isTrue();
    }

    @Test
    @DisplayName("get All URL in Service")
    void getAllUrl() {
        List<ShorterURL> allUrl = URLService.getAllUrl();
        Assertions.assertThat(!allUrl.isEmpty()).isTrue();
        Assertions.assertThat(allUrl.stream().count() == 1).isTrue();
    }

    @Test
    @DisplayName("save URL in Service")
    void save() {
        ShorterURL saveUrl = URLService.save(Util.newUrlDto());
        Assertions.assertThat(saveUrl.getId().equals(shorterURL.getId())).isTrue();
        Assertions.assertThat(Util.newUrlDto().getUrl().equals(shorterURL.getUrl())).isTrue();
    }

    @Test
    @DisplayName("delete URL in Service")
    void delete() {
        URLService.delete(shorterURL.getId());
        Assertions.assertThatCode(() -> URLService.delete(shorterURL.getId())).doesNotThrowAnyException();
    }
}
package com.positiveref.inditex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = InditexApplication.class)
@AutoConfigureMockMvc
class InditexApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void givenARequestOn14thAt10_shouldReturn200OkStatusAndExpectedData() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateTime", "2020-06-14T10:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.brandId").value(1),
                        jsonPath("$.productId").value(35455),
                        jsonPath("$.currency").value("EUR"),
                        jsonPath("$.price").value(35.50),
                        jsonPath("$.startDate").value("2020-06-14T00:00:00"),
                        jsonPath("$.endDate").value("2020-12-31T23:59:59"));
    }

    @Test
    void givenARequestOn14thAt16_shouldReturn200OkStatusAndExpectedData() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateTime", "2020-06-14T16:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.brandId").value(1),
                        jsonPath("$.productId").value(35455),
                        jsonPath("$.currency").value("EUR"),
                        jsonPath("$.price").value(25.45),
                        jsonPath("$.startDate").value("2020-06-14T15:00:00"),
                        jsonPath("$.endDate").value("2020-06-14T18:30:00"));
    }

    @Test
    void givenARequestOn14thAt21_shouldReturn200OkStatusAndExpectedData() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateTime", "2020-06-14T21:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.brandId").value(1),
                        jsonPath("$.productId").value(35455),
                        jsonPath("$.currency").value("EUR"),
                        jsonPath("$.price").value(35.50),
                        jsonPath("$.startDate").value("2020-06-14T00:00:00"),
                        jsonPath("$.endDate").value("2020-12-31T23:59:59"));
    }

    @Test
    void givenARequestOn15thAt10_shouldReturn200OkStatusAndExpectedData() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateTime", "2020-06-15T10:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.brandId").value(1),
                        jsonPath("$.productId").value(35455),
                        jsonPath("$.currency").value("EUR"),
                        jsonPath("$.price").value(30.5),
                        jsonPath("$.startDate").value("2020-06-15T00:00:00"),
                        jsonPath("$.endDate").value("2020-06-15T11:00:00"));
    }

    @Test
    void givenARequestOn16thAt21_shouldReturn200OkStatusAndExpectedData() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateTime", "2020-06-16T21:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.brandId").value(1),
                        jsonPath("$.productId").value(35455),
                        jsonPath("$.currency").value("EUR"),
                        jsonPath("$.price").value(38.95),
                        jsonPath("$.startDate").value("2020-06-15T16:00:00"),
                        jsonPath("$.endDate").value("2020-12-31T23:59:59"));
    }

    @Test
    void givenARequestOn13thAt21_shouldReturn200OkStatusAndEmptyBody() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("dateTime", "2020-06-13T21:00:00")
                        .param("brandId", "1")
                        .param("productId", "35455"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void givenNoParametersInRequest_shouldReturn400Status() throws Exception {
        mockMvc.perform(get("/inditex/price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}

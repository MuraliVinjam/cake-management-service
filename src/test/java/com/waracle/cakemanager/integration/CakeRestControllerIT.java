package com.waracle.cakemanager.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.waracle.cakemanager.AbstractTest;
import com.waracle.cakemanager.jpa.Cake;

class CakeRestControllerIT extends AbstractTest {

    private static final String TITLE = "A1 Cake";
    private static final String DESCRIPTION = "A1 Cake is really a A1";
    private static final String IMAGE = "A1.jpg";
    private static final String URI = "/cakes";
    private static final String DEMO = "demo";

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void testGetCakes() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(URI)
                .with(httpBasic(DEMO, DEMO))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        Cake[] cakesList = super.mapFromJson(content, Cake[].class);
        assertTrue(cakesList.length > 0);
    }

    @Test
    void addCake() throws Exception {
        Cake cake = buildCake();
        String inputJson = super.mapToJson(cake);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(URI)
                .with(httpBasic(DEMO, DEMO))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    private Cake buildCake() {
        return Cake.builder()
                .title(TITLE)
                .description(DESCRIPTION)
                .image(IMAGE)
                .build();
    }
}

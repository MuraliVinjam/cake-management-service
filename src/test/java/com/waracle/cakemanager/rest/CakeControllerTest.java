package com.waracle.cakemanager.rest;


import com.waracle.cakemanager.jpa.Cake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

public class CakeControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetCakes() throws Exception {
        String uri = "/cakes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(httpBasic("demo","demo"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        Cake[] cakesList = super.mapFromJson(content, Cake[].class);
        assertTrue(cakesList.length > 0);
    }

    @Test
    public void testGetCake() throws Exception {
        String uri = "/cakes/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(httpBasic("demo","demo"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        Cake cake = super.mapFromJson(content, Cake.class);
        assertNotNull(cake.getName());
    }

    @Test
    public void testUpdateCake() throws Exception {
        String uri = "/cakes/1";
        Cake birthday = buildCake("Anniversary Cake", "Anniversary Cake", "anniversary.jpg", 2);
        String inputJson = super.mapToJson(birthday);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .with(httpBasic("demo","demo"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteCake() throws Exception {
        String uri = "/cakes/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .with(httpBasic("demo","demo"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void addCake() throws Exception {
        String uri = "/cakes";
        Cake birthday = buildCake("A1 Cake", "A1 Cake is really a A1", "A1.jpg", 2);
        String inputJson = super.mapToJson(birthday);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .with(httpBasic("demo","demo"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    private Cake buildCake(String name, String comment, String imageUrl, int yumFactor) {
        Cake birthday = new Cake();
        birthday.setName(name);
        birthday.setComment(comment);
        birthday.setImageUrl(imageUrl);
        birthday.setYumFactor(yumFactor);
        return birthday;
    }

}

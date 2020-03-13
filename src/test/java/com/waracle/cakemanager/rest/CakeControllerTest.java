package com.waracle.cakemanager.rest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.waracle.cakemanager.jpa.Cake;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

public class CakeControllerTest extends AbstractTest {
    @Override
    @Before
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
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        Cake cake = super.mapFromJson(content, Cake.class);
        assertEquals("Sicilian Soft Icing Cake", cake.getName());
    }

    @Test
    public void testUpdateCake() throws Exception {
        String uri = "/cakes/1";
        Cake birthday = buildCake("Anniversary Cake", "Anniversary Cake", "anniversary.jpg", 2);
        String inputJson = super.mapToJson(birthday);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteCake() throws Exception {
        String uri = "/cakes/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void addCake() throws Exception {
        String uri = "/cakes";
        Cake birthday = buildCake("A1 Cake", "A1 Cake is really a A1", "A1.jpg", 2);
        String inputJson = super.mapToJson(birthday);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    public void addDuplicateCake() throws Exception {
        String uri = "/cakes";
        Cake birthday = buildCake("Sicilian Soft Icing Cake", "Made with soft icing and nuts etc.", "SicilianSoftIcingCake.jpg", 3);
        String inputJson = super.mapToJson(birthday);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(409, mvcResult.getResponse().getStatus());
    }

    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void addCakeNoName_expectException() throws Exception {
        String uri = "/cakes";
        Cake birthday = buildCake(null, "Birthday", "Birthday.jpg", 4);
        String inputJson = super.mapToJson(birthday);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        exceptionRule.expect(NestedServletException.class);
        exceptionRule.expectMessage("Name is mandatory");
    }

    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void addCakeNoImage_expectException() throws Exception {
        String uri = "/cakes";
        Cake birthday = buildCake("Birthday", "test", null, 5);
        String inputJson = super.mapToJson(birthday);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        exceptionRule.expect(NestedServletException.class);
        exceptionRule.expectMessage("Image is mandatory");
    }

    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void addCakeHighYumFactor_expectException() throws Exception {
        String uri = "/cakes";
        Cake birthday = buildCake("Birthday", "test", "t1", 6);
        String inputJson = super.mapToJson(birthday);
        mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        exceptionRule.expect(NestedServletException.class);
        exceptionRule.expectMessage("Yum factor must be between 0 and 5");
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

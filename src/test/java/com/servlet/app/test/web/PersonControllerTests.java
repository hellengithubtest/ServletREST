package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PersonControllerTests extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String uriPerson = "/person";

    @Test
    public void createValidPerson() throws Exception{
        String inputJson = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        String content = mvcResult1.getResponse().getContentAsString();
        assertEquals(200, mvcResult1.getResponse().getStatus());
        assertTrue(true);
    }

    @Test
    public void createPersonWithNegativeId() throws Exception{
        String inputJson = "{\"id\":-101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());
    }

    @Test
    public void createPersonWithoutBirthdate() throws Exception{
        String inputJson = "{\"id\":\"-80\",\"name\":\"valid\"}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult1.getResponse().getStatus());
        assertEquals(mvcResult1.getResponse().getContentAsString(), "must not be null");
    }
}

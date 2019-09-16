package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CarControllerTests extends AbstractTest{
    String uriCar = "/car";
    String uriPerson = "/person";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCreateCarWithoutValidOwnerId() throws Exception {
        String inputJson = "{\"id\":100,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":100}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(400, status);
        assertEquals(content, "Entity not found with current ownerId");
    }

    @Test
    public void getCreateCar() throws Exception {
        String inputJson1 = "{\"id\":100,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson2 = "{\"id\":100,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":100}";
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andReturn();

        String content = mvcResult2.getResponse().getContentAsString();
        assertEquals(200, mvcResult2.getResponse().getStatus());
        assertTrue(true);
    }

    @Test
    public void getCreateCarWithNegativeId() throws Exception {
        String inputJson1 = "{\"id\":100,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":-100,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":100}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(200, status);
    }

    @Test
    public void getCreateCarWithNegativeIdAndPersonWithNegativeId() throws Exception {
        String inputJson1 = "{\"id\":-160,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":-160,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":-160}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        assertEquals(200, status);
    }

    @Test
    public void getCreateCarWithDash() throws Exception {
        String inputJson1 = "{\"id\":-150,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":\"-149\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    //{"id":"-160","model":"-da-Devyatka","horsepower":50,"ownerId":"-160"}
    @Test
    public void getCreateCarWithDashNotValid() throws Exception {
        String inputJson1 = "{\"id\":-160,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":\"-160\",\"model\":\"-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-160\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        System.out.println("CONTENT" + mvcResult.getResponse().getContentAsString());
        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "Format vendor-model");
    }

    public void clear() throws Exception{
        String uri = "/clear";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }
}

package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;


public class CarControllerTests extends AbstractTest{
    String uriCar = "/car";
    String uriPerson = "/person";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createCarWithoutValidOwnerId() throws Exception {
        String inputJson = "{\"id\":100,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":100}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "Entity not found with current ownerId");
    }

    @Test
    public void createCarWithoutId() throws Exception{
        String inputJson1 = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":101}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "must not be null");
    }

    @Test
    public void createCarWithoutModel() throws Exception{
        String inputJson1 = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":160,\"horsepower\":\"100\",\"ownerId\":101}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "must not be null");
    }

    @Test
    public void createCarWithoutHorsepower() throws Exception{
        String inputJson1 = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":160,\"model\":\"BMW-X5\",\"ownerId\":101}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "must not be null");
    }

    @Test
    public void createCarWithoutOwnerId() throws Exception{
        String inputJson1 = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":160,\"model\":\"BMW-X5\",\"horsepower\":\"100\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "must not be null");
    }

    @Test
    public void createCarWithValidOwnerId() throws Exception {
        String inputJson1 = "{\"id\":100,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson2 = "{\"id\":100,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":100}";
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andReturn();

        assertEquals(200, mvcResult2.getResponse().getStatus());
    }

    @Test
    public void createCarWithNegativeId() throws Exception {
        String inputJson1 = "{\"id\":100,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":-100,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":100}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void createCarWithNegativeIdAndPersonWithNegativeId() throws Exception {
        String inputJson1 = "{\"id\":-160,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":-160,\"model\":\"BMW-X5\",\"horsepower\":\"100\",\"ownerId\":-160}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void createCarWithDash() throws Exception {
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

    @Test
    public void createCarWithDashNotValid() throws Exception {
        String inputJson1 = "{\"id\":-160,\"name\":\"ValidPerson\",\"birthdate\":\"10.10.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"id\":\"-160\",\"model\":\"-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-160\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "Format vendor-model");
    }

    @Test
    public void createCarWithPersonChild() throws Exception{
        String inputJson1 = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.2005\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());

        String inputJson = "{\"horsepower\": 100,\"id\": 110,\"model\":\"BMW-X5\",\"ownerId\": 101}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals(mvcResult.getResponse().getContentAsString(), "saveCar.car.owner: 18 +");
    }
}

package com.servlet.app.test.web;

import com.servlet.app.dto.PersonWithCars;
import com.servlet.app.entity.Car;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonControllerTests extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String uriPerson = "/person";
    String uriCar = "/car";
    @Test
    public void createValidPerson() throws Exception{
        String inputJson = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        String content = mvcResult1.getResponse().getContentAsString();
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

    @Test
    public void createPersonWithoutName() throws Exception{
        String inputJson = "{\"id\":\"-1\",\"birthdate\":\"01.01.2000\"}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult1.getResponse().getStatus());
        assertEquals(mvcResult1.getResponse().getContentAsString(), "must not be null");
    }

    @Test
    public void createPersonWithoutId() throws Exception{
        String inputJson = "{\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\"}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(400, mvcResult1.getResponse().getStatus());
        assertEquals(mvcResult1.getResponse().getContentAsString(), "must not be null");
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
    public void createPersonChild() throws Exception{
        String inputJson = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.2005\",\"cars\":null}";
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResult1.getResponse().getStatus());
    }

    @Test
    public void getPersonWithCars() throws Exception{
        String uriPersonWithCar = "/personwithcars?personid=101";

        String inputJson = "{\"id\":101,\"name\":\"ValidPerson\",\"birthdate\":\"11.11.1992\",\"cars\":null}";
        MvcResult mvcResultPerson = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        assertEquals(200, mvcResultPerson.getResponse().getStatus());

        String firstCar = "{\"id\":\"-229\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"101\"}";
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(firstCar)).andReturn();
        assertEquals(200, mvcResult2.getResponse().getStatus());

        String secondCar = "{\"id\":\"-228\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"101\"}";
        MvcResult mvcResult3 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(secondCar)).andReturn();
        assertEquals(200, mvcResult3.getResponse().getStatus());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        PersonWithCars personWithCars = super.mapFromJson(content, PersonWithCars.class);
        System.out.println(personWithCars);

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals("BMW-X3", personWithCars.getCars()[0].getModel());
        assertEquals("Lada-Devyatka", personWithCars.getCars()[1].getModel());

    }

}

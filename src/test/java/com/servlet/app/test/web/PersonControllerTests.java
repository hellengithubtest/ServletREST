package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class PersonControllerTests extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String uriPerson = "/person";
    String uriPersonWithCar = "/personwithcars?personid=";

    @Test
    public void addValidPerson() throws Exception{
        String inputJson = "{\"id\":\"-10\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-10))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createValidPersonLessThen18() throws Exception{
        String inputJson = "{\"id\":\"-20\",\"name\":\"Validperson2\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-20))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createPersonWithEmptyName() throws Exception{
        String inputJson = "{\"id\":\"-30\",\"name\":\"\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-30))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createBadPersonWithFutureBirthdate() throws Exception{
        String inputJson = "{\"id\":\"-39\",\"name\":\"valid\",\"birthdate\":\"01.12.2117\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createBadPersonWithEmptyId() throws Exception{
        String inputJson = "{\"id\":\"\",\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createBadPersonWithNullId() throws Exception{
        String inputJson = "{\"name\":\"valid\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createBadPersonWithNullName() throws Exception{
        String inputJson = "{\"id\":\"-70\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-70))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createBadPersonWithNullBirthdate() throws Exception{
        String inputJson = "{\"id\":\"-80\",\"name\":\"valid\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-80))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createBadPersonWithIncorrectBirthdate() throws Exception{
        String inputJson = "{\"id\":\"-90\",\"name\":\"Validperson2\",\"birthdate\":\"2017-01-01\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-90))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void createBadPersonLanientBirthdate() throws Exception{
        String inputJson = "{\"id\":\"-100\",\"name\":\"Validperson2\",\"birthdate\":\"01.15.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-100))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createBadPersonWithSymbolsBirthdaate() throws Exception{
        String inputJson = "{\"id\":\"-110\",\"name\":\"Validperson2\",\"birthdate\":\"sds\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-110))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getBadPersonWithEmptyNullNoformatId() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + null)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + "asdsad")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void getBadPersonUnique() throws Exception{
        String inputJson1 = "{\"id\":\"-250\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-250\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}

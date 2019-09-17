package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ClearTests extends AbstractTest{
    String uriPerson = "/person";
    String uriClear = "/clear";
    String uriPersonWithCar = "/personwithcars?personid=";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testClear() throws Exception {
        String inputJson = "{\"id\":\"-1\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get(uriClear)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());


        mvc.perform(MockMvcRequestBuilders.get(uriPersonWithCar + (-1))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}

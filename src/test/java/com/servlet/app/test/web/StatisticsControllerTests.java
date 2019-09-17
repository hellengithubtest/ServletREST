package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class StatisticsControllerTests extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    String uriStat = "/statistics";
    String uriPerson = "/person";
    String uriCar = "/car";

    @Test
    public void getStatistics() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(uriStat)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriStat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.personcount", is(0)))
                .andExpect(jsonPath("$.carcount", is(0)))
                .andExpect(jsonPath("$.uniquevendorcount", is(0)))
                .andDo(MockMvcResultHandlers.print());

        String inputJsonPerson = "{\"id\":\"-230\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJsonPerson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-230\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-230\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-229\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-230\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson3 = "{\"id\":\"-228\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson3)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson4 = "{\"id\":\"-227\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson4)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson5 = "{\"id\":\"-226\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson5)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriStat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.uniquevendorcount", is(3)))
                .andExpect(jsonPath("$.personcount", is(1)))
                .andExpect(jsonPath("$.carcount", is(5)))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getStatistics2NotAdd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(uriStat)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriStat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.personcount", is(0)))
                .andExpect(jsonPath("$.carcount", is(0)))
                .andExpect(jsonPath("$.uniquevendorcount", is(0)))
                .andDo(MockMvcResultHandlers.print());

        String inputJsonPerson1 = "{\"id\":\"-240\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJsonPerson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJsonPerson2 = "{\"id\":\"-239\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJsonPerson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        String inputJson1 = "{\"id\":\"-240\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-240\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-239\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-240\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriStat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.uniquevendorcount", is(1)))
                .andExpect(jsonPath("$.personcount", is(1)))
                .andExpect(jsonPath("$.carcount", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getStatisticsIgnoreCase() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(uriStat)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriStat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.personcount", is(0)))
                .andExpect(jsonPath("$.carcount", is(0)))
                .andExpect(jsonPath("$.uniquevendorcount", is(0)))
                .andDo(MockMvcResultHandlers.print());

        String inputJsonPerson1 = "{\"id\":\"-270\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJsonPerson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-270\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-270\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-269\",\"model\":\"BmW-X3\",\"horsepower\":100,\"ownerId\":\"-270\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson3 = "{\"id\":\"-268\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-270\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson3)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson4 = "{\"id\":\"-267\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-270\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson4)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson5 = "{\"id\":\"-266\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-270\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson5)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriStat))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.uniquevendorcount", is(3)))
                .andExpect(jsonPath("$.personcount", is(1)))
                .andExpect(jsonPath("$.carcount", is(5)))
                .andDo(MockMvcResultHandlers.print());
    }

}

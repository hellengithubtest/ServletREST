package com.servlet.app.test.web;

import com.servlet.app.dto.Statistics;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

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
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriStat)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getCorrectStatistics() throws Exception {
        //create one person
        Statistics expected = new Statistics(1L, 4L, 3L);
        String firstPerson = "{\"id\":\"-230\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";

        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(firstPerson)).andReturn();
        assertEquals(200, mvcResult1.getResponse().getStatus());

        //create 5 cars
        String firstCar = "{\"id\":\"-229\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-230\"}";
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(firstCar)).andReturn();
        assertEquals(200, mvcResult1.getResponse().getStatus());

        String secondCar = "{\"id\":\"-228\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        MvcResult mvcResult3 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(secondCar)).andReturn();
        assertEquals(200, mvcResult1.getResponse().getStatus());

        String thirdCar = "{\"id\":\"-227\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        MvcResult mvcResult4 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(thirdCar)).andReturn();
        assertEquals(200, mvcResult1.getResponse().getStatus());

        String fourthCar = "{\"id\":\"-226\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-230\"}";
        MvcResult mvcResult5 = mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(fourthCar)).andReturn();
        assertEquals(200, mvcResult1.getResponse().getStatus());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriStat)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Statistics statistics = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Statistics.class);

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(statistics, expected);
    }
}

package com.servlet.test;

import com.servlet.app.entity.Car;
import com.servlet.app.entity.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;


public class CarControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCreateCarWithoutValidOwnerId() throws Exception {
        clear();
        String uri = "/car";
        Car car = new Car();
        car.setId(Long.valueOf(110));
        car.setModel("BMV-X5");
        car.setHorsepower(100);
        car.setOwnerId(Long.valueOf(100));
        String inputJson = super.mapToJson(car);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(400, status);
        //MvcResult mvcResult
        assertEquals(content, "Entity not found with current ownerId");
    }


    @Test
    public void getCreateCar() throws Exception {
        String uri = "/car";
        Person person = new Person();
        person.setId(Long.valueOf(100));
        person.setName("ValidPerson");
        person.setBirthdate(LocalDate.of(1992,10,10));
        String inputJson1 = super.mapToJson(person);
        System.out.println("Json" + inputJson1);
        MvcResult mvcResult1 = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andReturn();
        String content1 = mvcResult1.getResponse().getContentAsString();
        System.out.println(person);
        System.out.println(content1);
        assertEquals(200, mvcResult1.getResponse().getStatus());


        Car car = new Car();
        car.setId(Long.valueOf(110));
        car.setModel("BMV-X5");
        car.setHorsepower(100);
        car.setOwnerId(Long.valueOf(100));
        String inputJson2 = super.mapToJson(car);
        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andReturn();

        int status = mvcResult2.getResponse().getStatus();
        System.out.println(status);
        String content = mvcResult2.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
        //MvcResult mvcResult
        assertTrue(true);
    }


    @Test
    public void getStatistics() throws Exception {
        String uri = "/statistics";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        assertEquals(200, status);
        //MvcResult mvcResult
        assertTrue(true);
    }

    public void clear() throws Exception{
        String uri = "/clear";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }
}

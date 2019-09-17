package com.servlet.app.test.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class CarControllerTests extends AbstractTest{
    String uriCar = "/car";
    String uriPerson = "/person";
    String uriPersonWithCar = "/personwithcars?personid=";

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createValidCar1() throws Exception {
        String inputJsonPerson = "{\"id\":\"-130\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJsonPerson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-130\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-130\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-130)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(1)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')]", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X5')].horsepower").value(100))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void createValidCar2or3() throws Exception {
        String inputJson = "{\"id\":\"-140\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-140\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-140\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-139\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-140\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson3 = "{\"id\":\"-138\",\"model\":\"Lada-Devyatka\",\"horsepower\":50,\"ownerId\":\"-140\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson3)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-140)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(3)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andExpect(jsonPath("$.cars[?(@.model=='Lada-Devyatka')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='Lada-Devyatka')].horsepower").value(50))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X3')]", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='BMW-X3')]", hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createValidCarModelFormatVariations() throws Exception {
        String inputJson = "{\"id\":\"-150\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-149\",\"model\":\"La-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-148\",\"model\":\"La-da-\",\"horsepower\":50,\"ownerId\":\"-150\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-150)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(2)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-Devyatka')].horsepower", hasSize(1)))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-Devyatka')].horsepower").value(50))
                .andExpect(jsonPath("$.cars[?(@.model=='La-da-')].horsepower", hasSize(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarModelFormat() throws Exception {
        String inputJson = "{\"id\":\"-160\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-160\",\"model\":\"-da-Devyatka\",\"horsepower\":50,\"ownerId\":\"-160\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-160)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(0)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarNegativeHorsepower() throws Exception {
        String inputJson = "{\"id\":\"-170\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-170\",\"model\":\"A-B\",\"horsepower\":-50,\"ownerId\":\"-170\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-170)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(0)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarLessThen18Years() throws Exception {
        String inputJson = "{\"id\":\"-180\",\"name\":\"Validperson2\",\"birthdate\":\"01.12.2017\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-180\",\"model\":\"A-B\",\"horsepower\":50,\"ownerId\":\"-180\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-180)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(0)))
                .andExpect(jsonPath("$.name", is("Validperson2")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarEmptyOrNullModel() throws Exception {
        String inputJson = "{\"id\":\"-190\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-190\",\"model\":\"\",\"horsepower\":50,\"ownerId\":\"-190\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        String inputJson2 = "{\"id\":\"-189\",\"horsepower\":50,\"ownerId\":\"-190\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-190)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(0)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarEmptyOrNullId() throws Exception {
        String inputJson = "{\"id\":\"-200\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"\",\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-200\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        String inputJson2 = "{\"model\":\"BMW-X3\",\"horsepower\":100,\"ownerId\":\"-200\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-200)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(0)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarEmptyNull0horsepower() throws Exception {
        String inputJson = "{\"id\":\"-210\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson1 = "{\"id\":\"-210\",\"model\":\"BMW-X3\",\"horsepower\":\"\",\"ownerId\":\"-210\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        String inputJson2 = "{\"id\":\"-209\",\"model\":\"BMW-X3\",\"ownerId\":\"-210\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        String inputJson3 = "{\"id\":\"-208\",\"model\":\"BMW-X3\",\"horsepower\":\"0\",\"ownerId\":\"-210\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson3)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        mvc.perform(
                MockMvcRequestBuilders.get(uriPersonWithCar + (-210)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.cars", hasSize(0)))
                .andExpect(jsonPath("$.name", is("Validperson1")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBadCarEmptyNullOwnerid() throws Exception {
        String inputJson1 = "{\"id\":\"-220\",\"model\":\"BMW-X3\",\"horsepower\":\"100\",\"ownerId\":\"\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        String inputJson2 = "{\"id\":\"-219\",\"model\":\"BMW-X3\",\"horsepower\":\"100\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createBadCarUnique() throws Exception {
        String inputJson1 = "{\"id\":\"-260\",\"name\":\"Validperson1\",\"birthdate\":\"01.01.2000\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriPerson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson1)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson2 = "{\"id\":\"-260\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-260\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson2)).andExpect(MockMvcResultMatchers.status().isOk());

        String inputJson3 = "{\"id\":\"-260\",\"model\":\"BMW-X5\",\"horsepower\":100,\"ownerId\":\"-260\"}";
        mvc.perform(MockMvcRequestBuilders.post(uriCar)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson3)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

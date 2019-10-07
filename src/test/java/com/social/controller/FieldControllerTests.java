package com.social.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.entities.Question;
import com.social.services.FieldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FieldControllerTests {
    @Autowired
    FieldService fieldService;

    @Test
    public void isMyTestWorks() {
        assertEquals(2, 2);
    }

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getResponseTest() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/account/response/1000", HttpMethod.GET, entity, String.class);
//		String json = "[{\"What is your name\":\"secret\"}]";
        String json ="[{\"What is your favourite language?\":\"java\",\"What is your choice?\":\"Два\"}," +
                "{\"What is your favourite language?\":\"С#\",\"What is your choice?\":\"Три\"}]";

        assertEquals(json, response.getBody());
    }

    @Test
    public void DeleteQuestionByUserIdTest() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        int expected;
        if((expected=fieldService.getQuestionByUserId((long) 1000).size()-1)<0)
            expected=0;
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/account/field/100", HttpMethod.DELETE, entity, String.class);
        List<Question> actual = fieldService.getQuestionByUserId((long) 1000);
        assertEquals(expected, actual.size());
    }

//    @Test
//    public void createUserTest() throws Exception {
//
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//        User user = new User(12,"1@1","1234567QWe","Fname","Lname","+380687838139");
//
//        mvc.perform(post("http://localhost:8080/account/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(user)))
//                .andExpect(status().isOk());
//    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
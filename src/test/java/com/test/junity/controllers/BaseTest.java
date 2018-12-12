package com.test.junity.controllers;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.test.junity.JunityApplication;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@ActiveProfiles("test")
public abstract class BaseTest {


    private int port = 8080;
    public String HOST_ROOT = "http://localhost:8080/junity/";

    ValidatableResponse prepareGet(String path) {
        return prepareGetDeleteWhen()
                .get(HOST_ROOT + path)
                .then();
    }

    ValidatableResponse prepareDelete(String path) {
        return prepareGetDeleteWhen()
                .delete(HOST_ROOT + path)
                .then();
    }

    Response preparePut(String path, String body) {
        return preparePostPutWhen(body)
                .put(HOST_ROOT + path);
    }

    Response preparePost(String path, String body) {
        return preparePostPutWhen(body)
                .post(HOST_ROOT + path);
    }

    private RequestSpecification preparePostPutWhen(String body) {
        return given()
                .port(port)
                .contentType(String.valueOf(APPLICATION_JSON))
                .body(body)
                .when();
    }

    private RequestSpecification prepareGetDeleteWhen() {
        return given()
                .port(port)
                .when();
    }
}

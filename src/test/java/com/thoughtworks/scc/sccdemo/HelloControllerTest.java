package com.thoughtworks.scc.sccdemo;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(helloController);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {

        MockMvcResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/hello");

        assertThat(response.statusCode()).isEqualTo(200);

        String responseBody = response.getBody().asString();
        assertThat(responseBody).isEqualTo("Hello World!");
    }
}

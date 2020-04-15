package com.thoughtworks.scc.sccdemo;

import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
    
    @Autowired
    private EmployeeController employeeController;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(employeeController);
    }

    @Test
    public void shouldFindEmployeeById() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/employees/1");

        Employee responseBody = response.getBody().as(Employee.class);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isEqualTo(1);
        assertThat(responseBody.getName()).isEqualTo("xiaoming");
    }

    @Test
    public void shouldCreateEmployee() {
        Employee employee = new Employee();
        employee.setName("Woody");
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .post("/employees");

        List<Employee> responseBody = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.size()).isEqualTo(3);
        assertThat(responseBody.get(2).getName()).isEqualTo("Woody");
    }
}
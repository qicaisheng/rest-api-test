package com.thoughtworks.scc.sccdemo;

import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
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
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(companyController);
    }

    @Test
    public void shouldReturnAllCompanies() {
        ResponseOptions response = given()
                .get("/companies");

        assertThat(response.statusCode()).isEqualTo(200);

        List<Company> companies = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        assertThat(companies.size()).isEqualTo(7);
    }

    @Test
    public void shouldReturnCompaniesByPage() {
        ResponseOptions response = given()
                .params("page", 1)
                .params("pageSize", 2)
                .get("/companies");

        assertThat(response.statusCode()).isEqualTo(200);

        List<Company> companies = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        assertThat(companies.size()).isEqualTo(2);
    }

    @Test
    public void shouldAddCompany() {
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(new Company());
        ResponseOptions response = given().spec(request)
                .post("/companies");

        assertThat(response.statusCode()).isEqualTo(200);

        List<Company> companies = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        assertThat(companies.size()).isEqualTo(1);
    }

    @Test
    public void shouldDeleteCompany() {
        MockMvcRequestSpecification request = given()
                .header("Content-Type", "application/json;charset=UTF-8");
        ResponseOptions response = given().spec(request)
                .delete("/companies/1");

        assertThat(response.statusCode()).isEqualTo(200);

        List<Company> companies = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        assertThat(companies.size()).isEqualTo(6);
    }
    
}
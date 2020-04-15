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
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(companyController);
    }

    @Test
    public void shouldReturnAllCompanies() {
        MockMvcResponse response = given()
                .header("Content-Type", ContentType.JSON)
                .when()
                .get("/companies");
        
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
        MockMvcResponse response = given()
                .params("page", 1)
                .params("pageSize", 2)
                .when()
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

        MockMvcResponse response = given()
                .header("Content-Type", ContentType.JSON)
                .body(new Company())
                .when()
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
    public void shouldUpdateCompany() {
        Company company = new Company();
        company.setCompanyName("TW");
        MockMvcResponse response = given()
                .header("Content-Type", ContentType.JSON)
                .body(company)
                .when()
                .put("/companies/1");

        assertThat(response.statusCode()).isEqualTo(200);

        Company updatedCompany = response.getBody().as(Company.class);
        assertThat(updatedCompany.getCompanyName()).isEqualTo(company.getCompanyName());
    }

    @Test
    public void shouldDeleteCompany() {
        MockMvcResponse response = given()
                .header("Content-Type", ContentType.JSON)
                .when()
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
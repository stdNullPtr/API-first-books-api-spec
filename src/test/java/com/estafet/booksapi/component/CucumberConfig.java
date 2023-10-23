package com.estafet.booksapi.component;

import org.springframework.boot.test.context.SpringBootTest;

import com.estafet.booksapi.OpenApiGeneratorApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = OpenApiGeneratorApplication.class)
public class CucumberConfig {

}

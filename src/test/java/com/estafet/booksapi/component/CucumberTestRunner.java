package com.estafet.booksapi.component;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "com.estafet.booksapi.component",
		plugin = {"pretty"}
)
public class CucumberTestRunner {

}

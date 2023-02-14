package com.teamapp.gospy.testing;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.teamapp.gospy.models.Person;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiControllerTestDefinitions {

    JsonObject jObj;
    @When("^we call the api end-point createPerson$")
    public void we_call_the_api_end_point_createPerson() throws Throwable{
        Person testPerson = new Person();
        testPerson.setName("testperson");
        String jsonData = HttpRequestFactory.getGsonObj().toJson(testPerson);
        String jsonStringResponse = HttpRequestFactory.getInstance().postContent("/api/person/create/",jsonData);
        Type personType = new TypeToken<Person>() {
        }.getType();
        jObj = HttpRequestFactory.getGsonObj().fromJson(jsonStringResponse,personType);

    }

    @Then("^a new Person record is created in the DB$")
    public void a_new_Person_record_is_created_in_the_DB() throws Throwable{

        assertEquals("testperson",jObj.get("name"));
    }
}

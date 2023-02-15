package com.teamapp.gospy.testing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.teamapp.gospy.models.Person;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Type;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ApiControllerTestDefinitions {

    GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Person.class, new PersonJsonSerialiser()).setPrettyPrinting();
    Gson gsonSerPerson = gsonBuilder.create();

    GsonBuilder gsonBuilder2 = new GsonBuilder().registerTypeAdapter(Person.class, new PersonJsonDeserialiser());
    Gson gsonDeserPerson = gsonBuilder2.create();

    Person receivedAsJOjectPerson = null;
    @When("^we call the api end-point createPerson$")
    public void we_call_the_api_end_point_createPerson() throws Throwable{
        Person testPerson = new Person();
        testPerson.setName("testperson");
        String jsonData = gsonSerPerson.toJson(testPerson);
        System.out.println("Json data to post with api call : " + jsonData);
        Optional<String> optJsonStringResponse = HttpRequestFactory.getInstance().postContent("/api/person/create/",jsonData);

        System.out.println("Response from api call : " + optJsonStringResponse.toString());
        receivedAsJOjectPerson = null;
        if (optJsonStringResponse.isEmpty()) {
            fail("Response from server not valid");
        }else {
            Type personType = new TypeToken<Person>() {
            }.getType();

            receivedAsJOjectPerson = gsonDeserPerson.fromJson(optJsonStringResponse.get(), personType);
        }

    }

    @Then("^a new Person record is created in the DB$")
    public void a_new_Person_record_is_created_in_the_DB() throws Throwable{
        if (receivedAsJOjectPerson != null) {
            assertEquals("testperson", receivedAsJOjectPerson.getName());
        }else {
            fail("Response from server not valid");
        }
    }

    @When("we call the api end-point to find a record with id {string}")
    public void weCallTheApiEndPointToFindARecordWithName(String arg0) {
        Optional<String> optJsonStringResponse = HttpRequestFactory.getInstance().getContent("/api/person/getById/01GS7ZGQGGY8ZMX6TWNRBWQTAB");

        System.out.println("Response from api call : " + optJsonStringResponse.toString());
        receivedAsJOjectPerson = null;
        if (optJsonStringResponse.isEmpty()) {
            fail("Response from server not valid");
        }else {
            Type personType = new TypeToken<Person>() {
            }.getType();

            receivedAsJOjectPerson = gsonDeserPerson.fromJson(optJsonStringResponse.get(), personType);
        }
    }

    @Then("the record exists in the DB")
    public void theRecordExistsInTheDB() {

        if (receivedAsJOjectPerson != null) {
            assertEquals("testperson", receivedAsJOjectPerson.getName());
        }else {
            fail("Response from server not valid");
        }
    }
}

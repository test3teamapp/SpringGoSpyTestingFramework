package com.teamapp.gospy.testing;

import com.google.gson.*;
import com.teamapp.gospy.models.Person;
import org.springframework.data.geo.Point;

import java.lang.reflect.Type;
import java.sql.Date;

public class PersonJsonDeserialiser implements JsonDeserializer<Person> {
    /*
    Person class has a member of type Point (from springframe geo) to store the location
    Gson does not know how to serialise/deserialise this object
    What we need to deserialise is the following

    {
    "id": "string",
    "name": "string",
    "deviceToken": "string",
    "location": {
        "x": 0,
        "y": 0
        },
    "locationUpdated": "2023-02-14T08:32:46.609Z"
    }
     */

    @Override
    public Person deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Person newPerson = new Person();
        newPerson.setName(jsonElement.getAsJsonObject().get("name").getAsString());
        newPerson.setId(jsonElement.getAsJsonObject().get("id").getAsString());
        newPerson.setDeviceToken(jsonElement.getAsJsonObject().get("deviceToken").getAsString());
        newPerson.setLocationUpdated(Date.valueOf(jsonElement.getAsJsonObject().get("locationUpdated").getAsString()));
        double locX = jsonElement.getAsJsonObject().get("location").getAsJsonObject().get("x").getAsDouble();
        double locY = jsonElement.getAsJsonObject().get("location").getAsJsonObject().get("y").getAsDouble();

        Point newPoint = new Point(locX, locY);
        newPerson.setLocation(newPoint);

        return newPerson;
    }
}

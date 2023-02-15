package com.teamapp.gospy.testing;

import com.google.gson.*;
import com.teamapp.gospy.models.Person;
import org.springframework.data.geo.Point;

import java.lang.reflect.Type;

public class PersonJsonSerialiser implements JsonSerializer<Person> {
    /*
    Person class has a member of type Point (from springframe geo) to store the location
    Gson does not know how to serialise this object
    What we need to produce is the following

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
    public JsonElement serialize(Person o, Type type, JsonSerializationContext jsonSerializationContext) {
        // debug
        System.out.println("Serialising Person object  : " + o.getName());

        JsonObject object = new JsonObject();
        object.addProperty("name",o.getName() );
        object.addProperty("id", o.getId());
        object.addProperty("deviceToken", o.getDeviceToken());
        object.addProperty("locationUpdated", o.getLocationUpdated() == null ? "" :  o.getLocationUpdated().toString());
        // create location json object
        JsonObject locationObj = new JsonObject();
        Point locpoint = o.getLocation();
        locationObj.addProperty("x", locpoint == null ? 0 : locpoint.getX());
        locationObj.addProperty("y", locpoint == null ? 0 : locpoint.getY());
        object.add("location", locationObj);

        return object;
    }
}

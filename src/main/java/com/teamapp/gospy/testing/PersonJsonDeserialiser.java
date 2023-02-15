package com.teamapp.gospy.testing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.teamapp.gospy.models.Person;
import org.springframework.data.geo.Point;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        //debug
        System.out.println("Deserialising json element : " + jsonElement.toString());
        Person newPerson = new Person();
        newPerson.setName(jsonElement.getAsJsonObject().get("name").getAsString());
        newPerson.setId(jsonElement.getAsJsonObject().get("id").getAsString());
        JsonElement jel = jsonElement.getAsJsonObject().get("deviceToken");
        newPerson.setDeviceToken(jel.isJsonNull() ? null : jel.getAsString());
        jel = jsonElement.getAsJsonObject().get("locationUpdated");
        //e.g. 2023-02-14T12:48:16.827+00:00
        if (jel.isJsonNull()){
            newPerson.setLocationUpdated(null);
        }else {
            Date dateToSetFromObj = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'HH:mm");
            try {
                dateToSetFromObj = sdf.parse(jel.getAsString());
            } catch (ParseException e) {
                System.err.println("PersonJsonDeserialiser : Parsing timezone of date was not possible. Skipping timezone details");
                String dateStr = jel.getAsString().substring(jel.getAsString().indexOf("+"));
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                try {
                    dateToSetFromObj = sdf.parse(dateStr);
                } catch (ParseException ex) {
                    System.err.println("PersonJsonDeserialiser : Parsing of date was not possible");
                }
            }
            newPerson.setLocationUpdated(dateToSetFromObj);
        }
        double locX = jsonElement.getAsJsonObject().get("location").getAsJsonObject().get("x").getAsDouble();
        double locY = jsonElement.getAsJsonObject().get("location").getAsJsonObject().get("y").getAsDouble();

        Point newPoint = new Point(locX, locY);
        newPerson.setLocation(newPoint);

        return newPerson;
    }
}

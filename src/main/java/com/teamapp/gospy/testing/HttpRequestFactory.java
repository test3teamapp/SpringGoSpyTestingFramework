package com.teamapp.gospy.testing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamapp.gospy.models.Person;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HttpRequestFactory{

    protected final Logger LOGGER = Logger.getLogger("HttpRequestFactory");

    public final static String siteUrl = "http://localhost:8080";

    private static HttpRequestFactory httpRequestFactoryObj = null;
    private static Gson gsonObj = null;

    private HttpRequestFactory(){

    }

    public static HttpRequestFactory getInstance(){
        if (httpRequestFactoryObj == null){
            httpRequestFactoryObj = new HttpRequestFactory();
        }
        return httpRequestFactoryObj;
    }

    public static Gson getGsonObj(){
        if (gsonObj == null){

            GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(Person.class, new PersonJsonSerialiser()).setPrettyPrinting();
            gsonBuilder.registerTypeAdapter(Person.class, new PersonJsonDeserialiser());
            gsonObj = gsonBuilder.create();
        }
        return gsonObj;
    }


    public String getContent(String uri) {
        try{
            HttpRequest httpReq=HttpRequest.newBuilder()
                    .uri(new URI(siteUrl + uri))
                    .header("Accept", "application/json")
                    .timeout(Duration.of(2, SECONDS))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpReq, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpURLConnection.HTTP_OK){
                return response.body();
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String postContent(String uri, String jsonData) {

        try{
            HttpRequest httpReq=HttpRequest.newBuilder()
                    .uri(new URI(siteUrl + uri))
                    .header("Accept", "application/json")
                    .timeout(Duration.of(2, SECONDS))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpReq, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpURLConnection.HTTP_OK){
                return response.body();
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

package com.teamapp.gospy.testing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HttpRequestFactory{

    protected final Logger LOGGER = Logger.getLogger("HttpRequestFactory");

    public final static String siteUrl = "http://localhost:8080";

    private static HttpRequestFactory httpRequestFactoryObj = null;

    private HttpRequestFactory(){

    }

    public static HttpRequestFactory getInstance(){
        if (httpRequestFactoryObj == null){
            httpRequestFactoryObj = new HttpRequestFactory();
        }
        return httpRequestFactoryObj;
    }

    public Optional<String> getContent(String uri) {
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
                return Optional.of(response.body());
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<String> postContent(String uri, String jsonData) {

        try{
            HttpRequest httpReq=HttpRequest.newBuilder()
                    .uri(new URI(siteUrl + uri))
                    .header("Content-Type", "application/json")
                    .timeout(Duration.of(2, SECONDS))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpReq, HttpResponse.BodyHandlers.ofString());

            //debug
            System.out.println("Response status: " +  response.statusCode() + ". Response body = : " + response.body());
            if (response.statusCode() == HttpURLConnection.HTTP_OK){
                return Optional.of(response.body());
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}

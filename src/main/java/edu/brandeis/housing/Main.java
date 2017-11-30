package edu.brandeis.housing;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.brandeis.housing.models.Apartment;
import edu.brandeis.housing.models.Rating;
import edu.brandeis.housing.models.User;
import okhttp3.*;
import org.apache.http.client.utils.URIBuilder;

public class Main {
    public static void main(String[] args)throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Rating r = new Rating();
        r.setStarCount(4);
        r.setContent("This place rooles");
        System.out.println(mapper.writeValueAsString(r));
//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\n" +
//                "\t\"name\": \"Daniel Fenton\",\n" +
//                "\t\"userName\": \"inviso-bill\",\n" +
//                "\t\"passwordHash\": \"goingghost\",\n" +
//                "\t\"profilePictureUrl\": \"whydidtheycancelme.com\",\n" +
//                "\t\"isTenant\": \"true\"\n" +
//                "}");
//        Request request = new Request.Builder().url(buildUrl()).post(body).addHeader("Accept", "application/json")
//                .addHeader("Content-Type", "application/json")
//                .build();
//        System.out.println(buildUrl());
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response.code());
//            System.out.println(response.body().string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        Rating r = new Rating();
//        r.setContent("hello world");
//        r.setStarCount(4);
//        r.setRatingID(25);
//        r.setComments(new ArrayList<>());
//        User u = new User();
//        u.setUserName("dvilisnky");
//        u.setName("Danie");
//        u.setTenant(true);
//        u.setRatingsIWrote(Arrays.asList(r));
//        u.setUserID(400);
//        r.setWriter(u);
//        System.out.println(mapper.writeValueAsString(r));
//        System.out.println(r);
//        Apartment rated = mapper.readValue("{\n" +
//                "\t\"address\": \"1 Infinite Loop Rd\",\n" +
//                "\t\"description\": \"Apples\",\n" +
//                "\t\"squareFeet\": \"76767676\",\n" +
//                "\t\"roomCount\": \"2500\",\n" +
//                "\t\"price\": \"2000000\"\n" +
//                "}", Apartment.class);
//        System.out.println(rated);
        }


    private static URL buildUrl() {
        URL url = null;
        try {
            url =new URL( new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(8080)
                    .setPath("/users").build().toString());
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        return url;
    }

//        u.setPasswordHash("2345678");
//        u.setApartments(new ArrayList<>());
//        u.setUserID(25);
//        u.setUserName("goliathSucccy");
//        u.setMyLandlords(new ArrayList<>());
//        u.setMyTenants(new ArrayList<>());
//        u.setName("DAvid");
//        u.setOwnedByMe(new ArrayList<>());
//        u.setProfilePictureUrl("rickandmorty.com");
//        u.setRatingAboutMe(new ArrayList<>());
//        u.setRatingsIWrote(new ArrayList<>());
//        u.setTenant(true);
//        System.out.println(mapper.writeValueAsString(u));
//        Rating r = mapper.readValue("{\n" +
//                "\t\"starCount\": \"4\",\n" +
//                "\t\"content\": \"This place rocks! Bitchin landlord too\"\n" +
//                "}", Rating.class);
//        User writer = mapper.readValue("{\n" +
//                "\t\"name\": \"Darth Vader\",\n" +
//                "\t\"userName\": \"xXforceUs3rXx\",\n" +
//                "\t\"passwordHash\": \"90191\",\n" +
//                "\t\"profilePictureUrl\": \"deathstar.com\",\n" +
//                "\t\"isTenant\": \"true\",\n" +
//                "\t\"apartments\": \"[]\"\n" +
//                "}", User.class);
//        System.out.println(writer);
//        r.setWriter(writer);
//        Apartment rated = mapper.readValue("{\n" +
//                "\t\"address\": \"1 Infinite Loop Rd\",\n" +
//                "\t\"description\": \"Apples\",\n" +
//                "\t\"squareFeet\": \"76767676\",\n" +
//                "\t\"roomCount\": \"2500\",\n" +
//                "\t\"price\": \"2000000\"\n" +
//                "}", Apartment.class);
//        rated.setRatings(new ArrayList<>());
//        rated.addRating(r);
//        r.setRatingFor(rated);
//        System.out.println(r);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost rateApartment = new HttpPost(uri);
//        HttpEntity entity = new StringEntity("{\n" +
//                "\t\"starCount\": \"4\",\n" +
//                "\t\"content\": \"This place rocks! Bitchin landlord too\"\n" +
//                "}");
//        rateApartment.setEntity(entity);
//        rateApartment.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//        httpClient.execute(rateApartment);
    }

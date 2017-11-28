package edu.brandeis.housing;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

import edu.brandeis.housing.models.User;

public class Main {
    public static void main(String[] args)throws IOException {
        ObjectMapper mapper = new ObjectMapper();

//        User u = new User();
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
        User writer = mapper.readValue("{\n" +
                "\t\"name\": \"Darth Vader\",\n" +
                "\t\"userName\": \"xXforceUs3rXx\",\n" +
                "\t\"passwordHash\": \"90191\",\n" +
                "\t\"profilePictureUrl\": \"deathstar.com\",\n" +
                "\t\"isTenant\": \"true\",\n" +
                "\t\"apartments\": \"[]\"\n" +
                "}", User.class);
        System.out.println(writer);
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
//        URI uri = null;
//        try {
//            uri = new URIBuilder()
//                    .setScheme("http")
//                    .setHost("localhost:8080")
//                    .setPath("/apartments/150/ratings")
//                    .setParameter("writerId", "2")
//                    .setParameter("aboutWhomId", null)
//                    .build();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        HttpPost rateApartment = new HttpPost(uri);
//        HttpEntity entity = new StringEntity("{\n" +
//                "\t\"starCount\": \"4\",\n" +
//                "\t\"content\": \"This place rocks! Bitchin landlord too\"\n" +
//                "}");
//        rateApartment.setEntity(entity);
//        rateApartment.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//        httpClient.execute(rateApartment);
    }
}

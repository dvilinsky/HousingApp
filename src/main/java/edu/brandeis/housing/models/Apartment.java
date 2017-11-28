package edu.brandeis.housing.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "apartments")
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartments_seq_gen")
    @SequenceGenerator(name = "apartments_seq_gen", sequenceName = "apartments_apartmentid_seq", allocationSize = 1)
    private Integer apartmentID;

    private String address;

    private String description;

    private Integer squareFeet;

    @Column(name = "roomCount")
    private Integer roomCount;

    private BigDecimal price;

    //Many to many because it is a history of tenants, not who is currently living
    //in the apartment
    @ManyToMany(mappedBy = "apartments")
    private List<User> tenants;

    @ManyToOne
    @JoinColumn(name = "landlordID")
    @JsonBackReference(value = "myApartments")
    private User landlord;

    @OneToMany(mappedBy = "belongingTo")
    private List<Picture> pictures;

    @OneToMany(mappedBy = "ratingFor")
    @JsonManagedReference
    private List<Rating> ratings;

    public Apartment() {
        //For jpa
    }


    @Override
    public String toString() {
        return "Address: " + address + "\nDescription: " + description +
                "\nSquare Feet: " + squareFeet + "\nRoom Count: " + roomCount +
                "\nPrice: $" + price + "\nLandlord: " + landlord;
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    //Getters and setters below for (de)serialization
    public String getAddress() {
        return address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getApartmentID() {
        return apartmentID;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public Integer getSquareFeet() {
        return squareFeet;
    }

    public String getDescription() {
        return description;
    }

    public List<User> getTenants() {
        return tenants;
    }

    public User getLandlord() {
        return landlord;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApartmentID(Integer apartmentID) {
        this.apartmentID = apartmentID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public void setSquareFeet(Integer squareFeet) {
        this.squareFeet = squareFeet;
    }

    public void setTenants(List<User> tenants) {
        this.tenants = tenants;
    }
}

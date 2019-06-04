package edu.brandeis.housing.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_userid_seq", allocationSize = 1)
    private Integer userID;

    private String name;

    private String passwordHash;

    private String userName;

    private String profilePictureUrl; //TODO: Should be part of some URL object to check validity

    private boolean isTenant;

    //Describes a history of landlords, not current landlord
    @ManyToMany
    @JoinTable(
            name = "users_landlords",
            joinColumns = @JoinColumn(name = "userID"), //tenant
            inverseJoinColumns = @JoinColumn(name = "landlordID") //landlord
    )
    private List<User> myLandlords;

    @ManyToMany(mappedBy = "myLandlords")
    private List<User> myTenants;

    @OneToMany(mappedBy = "writer")
    @JsonManagedReference(value = "user-ratingsWritten")
    private List<Rating> ratingsIWrote;

    @OneToMany(mappedBy = "aboutWhom")
    @JsonManagedReference(value = "user-ratingAboutWhom")
    private List<Rating> ratingAboutMe;

    //This is many to many because it describes a history of apartments, not so much where
    //this user is currently living
    @ManyToMany
    @JoinTable(
            name = "users_apartments",
            joinColumns = @JoinColumn(name = "userID", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "apartmentID", referencedColumnName = "apartmentID")
    )
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userID")
    @JsonBackReference
    private List<Apartment> apartments;

    @OneToMany(mappedBy = "landlord")
    @JsonManagedReference(value = "myApartments")
    private List<Apartment> ownedByMe;

    public User() {
        //for JPA
    }

    public void addRatingIWrote(Rating rating) {
        this.ratingsIWrote.add(rating);
    }

    public void addRatingAboutMe(Rating rating) {
        this.ratingAboutMe.add(rating);
    }

    public void addApartment(Apartment apartment) {
        this.ownedByMe.add(apartment);
    }

    public String getName() {
        return name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    @JsonProperty("passwordHash")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    @JsonProperty("isTenant")
    public boolean getIsTenant() {
        return isTenant;
    }

    public List<Apartment> getOwnedByMe() {
        return ownedByMe;
    }

    public List<Rating> getRatingAboutMe() {
        return ratingAboutMe;
    }

    public List<Rating> getRatingsIWrote() {
        return ratingsIWrote;
    }

    public List<User> getMyLandlords() {
        return myLandlords;
    }

    public List<User> getMyTenants() {
        return myTenants;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMyLandlords(List<User> myLandlords) {
        this.myLandlords = myLandlords;
    }

    public void setMyTenants(List<User> myTenants) {
        this.myTenants = myTenants;
    }

    public void setOwnedByMe(List<Apartment> ownedByMe) {
        this.ownedByMe = ownedByMe;
    }

    public void setRatingAboutMe(List<Rating> ratingAboutMe) {
        this.ratingAboutMe = ratingAboutMe;
    }

    public void setRatingsIWrote(List<Rating> ratingsIWrote) {
        this.ratingsIWrote = ratingsIWrote;
    }

    public void setTenant(boolean tenant) {
        isTenant = tenant;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nUsername: " + this.userName
                + "\npasswordHash: " + this.passwordHash + "\nuserID: " + this.userID
                + "\nprofilePictureUrl: " + this.profilePictureUrl + "\nisTenant: " + this.isTenant;
    }
}

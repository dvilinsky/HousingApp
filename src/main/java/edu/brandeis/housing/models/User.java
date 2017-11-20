package edu.brandeis.housing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_userid_seq")
    @NotNull
    private Integer userID;

    private String name;

    @JsonIgnore
    private String passwordHash;

    private String userName;

    private String profilePictureUrl; //TODO: should this be some sort of URL object? Nah, we don't give a shit about the validity of it

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
    private List<Rating> ratings;

    //TODO: do I want to do user.getcomments, or do I want to do user.getRatings.foreach(getcomments)?

    //This is many to many because it describes a history of apartments, not so much where
    //this user is currently living
    @ManyToMany
    @JoinTable(
            name = "users_apartments",
            joinColumns = @JoinColumn(name = "userID", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "apartmentID", referencedColumnName = "apartmentID")
    )
    private List<Apartment> apartments;

    @OneToMany(mappedBy = "landlord")
    private List<Apartment> ownedByMe;

    public User() {
        //for JPA
    }
}

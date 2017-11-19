package edu.brandeis.housing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
    @NotNull
    private Long userID;

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
            joinColumns = @JoinColumn(name = "tenant", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "landlord", referencedColumnName = "userID")
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
            joinColumns = @JoinColumn(name = "user", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "apartment", referencedColumnName = "apartmentID")
    )
    private List<Apartment> apartments;

    @OneToMany(mappedBy = "landlord")
    private List<Apartment> ownedByMe;

    public User() {
        //for JPA
    }
}

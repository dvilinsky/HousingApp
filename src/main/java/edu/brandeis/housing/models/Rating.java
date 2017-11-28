package edu.brandeis.housing.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq_gen")
    @SequenceGenerator(name = "rating_seq_gen", sequenceName = "ratings_ratingid_seq", allocationSize = 1)
    @NotNull
    private Integer ratingID;

    private Integer starCount;

    private String content;

    @ManyToOne
    @JoinColumn(name = "ratinguserID")
    @JsonBackReference(value = "user-ratingsWritten")
    private User writer;

    @ManyToOne
    @JoinColumn(name = "userratedId")
    @JsonBackReference(value = "user-ratingAboutWhom")
    private User aboutWhom;

    @OneToMany(mappedBy = "parent")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "apartmentID")
    @JsonBackReference
    private Apartment ratingFor;

    @Override
    public String toString() {
        return "Star count: " + starCount + "\nContent: " + content +
                "\nWriter: " + writer + "\nPerson being rated: " + aboutWhom +
                "\nApartment being rated: " + ratingFor;
    }

    //Getters and setter for (de)serialization
    public Apartment getRatingFor() {
        return ratingFor;
    }

    public Integer getRatingID() {
        return ratingID;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getContent() {
        return content;
    }

    public User getAboutWhom() {
        return aboutWhom;
    }

    public User getWriter() {
        return writer;
    }

    public void setAboutWhom(User aboutWhom) {
        this.aboutWhom = aboutWhom;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRatingFor(Apartment ratingFor) {
        this.ratingFor = ratingFor;
    }

    public void setRatingID(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}

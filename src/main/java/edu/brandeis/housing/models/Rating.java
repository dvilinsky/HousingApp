package edu.brandeis.housing.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq_gen")
    @SequenceGenerator(name = "rating_seq_gen", sequenceName = "ratings_ratingid_seq")
    @NotNull
    private Integer ratingID;

    private Integer starCount;

    private String content;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User writer;

    @OneToMany(mappedBy = "parent")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "apartmentID")
    private Apartment ratingFor;
}

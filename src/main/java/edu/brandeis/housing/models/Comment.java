package edu.brandeis.housing.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_id_gen")
    @SequenceGenerator(name = "comments_id_gen", sequenceName = "comments_commentid_seq")
    @NotNull
    private Long commentId;

    private String content;

    @ManyToOne
    @JoinColumn(name = "ratingID")
    private Rating parent;
}

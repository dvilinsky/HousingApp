package edu.brandeis.housing.models;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "picture_seq_gen")
    @SequenceGenerator(name = "picture_seq_gen", sequenceName = "pictures_pictureid_seq")
    private Long pictureID;

    @ManyToOne
    @JoinColumn(name = "apartmentID")
    private Apartment belongingTo;

    private String path;
}

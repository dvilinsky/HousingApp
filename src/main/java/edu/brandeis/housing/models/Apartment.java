package edu.brandeis.housing.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartments_seq_generator")
    @SequenceGenerator(name = "apartments_seq_gen", sequenceName = "apartments_apartmentid_seq")
    @NotNull
    private Long apartmentID;

    private String address;

    private String description;

    private Integer squareFeet;

    private Integer roomCount;

    private BigDecimal price;

    //Many to many because it is a history of tenants, not who is currently living
    //in the apartment
    @ManyToMany(mappedBy = "apartments")
    private List<User> tenants;

    @ManyToOne
    @JoinColumn(name = "landlordID")
    private User landlord;

    private List<String> pictures;

    public Apartment() {

    }
}

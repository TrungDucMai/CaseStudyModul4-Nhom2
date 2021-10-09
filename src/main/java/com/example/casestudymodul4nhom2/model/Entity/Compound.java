package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Compound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private int percent;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;




}

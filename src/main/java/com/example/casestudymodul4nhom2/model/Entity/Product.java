package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private int number;
    private String description;
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AppUser appuser;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @OneToMany(targetEntity = Compound.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Compound> compoundList;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> commentList;






}

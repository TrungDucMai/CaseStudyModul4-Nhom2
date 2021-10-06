package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
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
    private String description;
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appuser;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Compound> compoundList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> commentList;






}

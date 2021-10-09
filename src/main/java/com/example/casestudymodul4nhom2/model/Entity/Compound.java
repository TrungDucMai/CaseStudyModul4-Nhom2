package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
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
    private Product product;




}

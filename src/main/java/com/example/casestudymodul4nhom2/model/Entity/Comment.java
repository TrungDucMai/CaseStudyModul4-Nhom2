package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private String content;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser AppUser;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;



}

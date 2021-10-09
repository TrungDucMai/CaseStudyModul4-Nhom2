package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    private String code;

    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> productList;

    private LocalDate pickupDate;
    private LocalDate orderDay;
    private String status;

    public double getTotalMoney(){
        double total =0;
        for (int i = 0; i < productList.size(); i++){
           total += productList.get(i).getQuantity()* productList.get(i).getPrice();
        }
        return total;
    }










}

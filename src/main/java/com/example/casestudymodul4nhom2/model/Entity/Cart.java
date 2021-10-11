package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    private String code;

    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
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

    public double getMoneyProduct(Long id){
        double total =0;
        for (int i = 0; i < productList.size(); i++){
           if(productList.get(i).getId()==id){
               total = productList.get(i).getQuantity()* productList.get(i).getPrice();
           }
        }
        return total;
    }










}

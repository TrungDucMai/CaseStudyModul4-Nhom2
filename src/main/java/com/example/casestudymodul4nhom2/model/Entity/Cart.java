package com.example.casestudymodul4nhom2.model.Entity;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import lombok.Data;

import javax.persistence.*;
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

    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
    private List<Product> productList;

    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    private Bill bill;



    public double TotalMoney(){
        double total =0;
        for (int i = 0; i < productList.size(); i++){
           total += productList.get(i).getQuantity()* productList.get(i).getPrice();
        }
        return total;
    }










}

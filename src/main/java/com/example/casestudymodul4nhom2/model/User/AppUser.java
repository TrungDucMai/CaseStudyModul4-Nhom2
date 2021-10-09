package com.example.casestudymodul4nhom2.model.User;

import com.example.casestudymodul4nhom2.model.Entity.Cart;
import com.example.casestudymodul4nhom2.model.Entity.Comment;
import com.example.casestudymodul4nhom2.model.Entity.Compound;
import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String avatar;
    private String email;
    private String status;
    @ManyToOne
    private AppRole roll;

    @OneToOne(mappedBy = "appUser",cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne(mappedBy = "AppUser",cascade = CascadeType.ALL)
    private Comment comment;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;



    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(roll.getAuthority()));
        return authorities;
    }



}

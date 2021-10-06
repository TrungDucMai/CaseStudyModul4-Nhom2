package com.example.casestudymodul4nhom2.model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}

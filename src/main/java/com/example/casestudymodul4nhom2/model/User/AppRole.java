package com.example.casestudymodul4nhom2.model.User;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
public class AppRole  implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

}

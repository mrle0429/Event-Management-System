package com.eventmanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
public class User extends Account {
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Ticket> tickets;
    
    public User(String name, String email, String password) {
        super(name, email, password);
        setRole("USER");
    }
}
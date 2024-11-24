package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;


@MappedSuperclass @Data @NoArgsConstructor
public abstract class Account {
    @GeneratedValue @Id
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;
    public Account(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = Role.CUSTOMER;
    }
}

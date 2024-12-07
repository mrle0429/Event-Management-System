package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.Role;

/**
 * Abstract base class for all account entities in the system.
 * Defines common properties shared by administrators, organisers, and customers.
 * 
 * Core attributes include:
 * - Unique identifier
 * - Basic user information (name, email)
 * - Authentication credentials
 * - Role-based access control
 * 
 * @see Administrator
 * @see Organiser  
 * @see Customer
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Account {
    @GeneratedValue
    @Id
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

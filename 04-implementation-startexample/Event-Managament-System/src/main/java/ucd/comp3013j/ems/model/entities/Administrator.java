package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ucd.comp3013j.ems.model.enums.Role;

/**
 * Administrator account entity.
 * Extends the base Account class to represent system administrators.
 * Administrators have the highest level of system access and can manage:
 * - User accounts
 * - Venues
 * - Events
 * - System configurations
 * 
 * @see Account
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Administrator extends Account {


    public Administrator(String email, String name, String password) {
        super(email, name, password);
        this.setRole(Role.ADMINISTRATOR);
    }

    public Administrator() {
        super();
        this.setRole(Role.ADMINISTRATOR);
    }
}

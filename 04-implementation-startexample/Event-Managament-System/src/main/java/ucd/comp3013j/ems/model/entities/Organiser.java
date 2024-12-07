package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.Role;

/**
 * Organiser account entity.
 * Extends the base Account class to represent event organisers.
 * Contains additional fields for company and contact information.
 * 
 * Organisers can:
 * - Create and manage events
 * - Configure ticket pricing and seating
 * - Monitor ticket sales
 * - Update event details
 * 
 * @see Account
 * @see Event
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Organiser extends Account {
    private String companyName;
    private String address;
    private String phoneNumber;

    public Organiser(String email, String name, String password, String companyName, String address, String phoneNumber) {
        super(email, name, password);
        this.setRole(Role.ORGANISER);
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

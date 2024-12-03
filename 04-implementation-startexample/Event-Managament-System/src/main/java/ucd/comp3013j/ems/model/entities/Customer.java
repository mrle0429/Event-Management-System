package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.enums.Role;

/**
 * Customer account entity.
 * Extends the base Account class to represent regular users of the system.
 * 
 * Customers can:
 * - Browse available events
 * - Purchase tickets
 * - View purchase history
 * - Manage their profile
 * 
 * @see Account
 * @see Ticket
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Customer extends Account {

    public Customer(AccountDTO registrationDTO) {
        super(registrationDTO.getEmail(), registrationDTO.getName(), registrationDTO.getPassword());
        this.setRole(Role.CUSTOMER);
    }

    public Customer() {
        super();
        this.setRole(Role.CUSTOMER);
    }

    public Customer(String email, String name, String password) {
        super(email, name, password);
        this.setRole(Role.CUSTOMER);
    }
}

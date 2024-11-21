package ucd.comp3013j.ems.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ucd.comp3013j.ems.model.dto.AccountDTO;

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

    public Customer(String name, String email, String pass){
        super(email, name, pass);
        this.setRole(Role.CUSTOMER);
    }
}

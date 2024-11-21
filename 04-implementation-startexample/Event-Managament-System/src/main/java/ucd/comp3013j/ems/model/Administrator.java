package ucd.comp3013j.ems.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

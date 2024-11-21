package ucd.comp3013j.ems.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity @Data @NoArgsConstructor
public class Organiser extends Account {
    private String companyName;
    private String address;
    private String phoneNumber;

    public Organiser(String name, String email, String password, String companyName, String address, String phoneNumber) {
        super(name, email, password);
        this.setRole(Role.ORGANISER);
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

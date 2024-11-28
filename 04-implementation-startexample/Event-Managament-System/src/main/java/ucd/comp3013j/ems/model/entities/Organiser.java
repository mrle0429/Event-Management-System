package ucd.comp3013j.ems.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ucd.comp3013j.ems.model.enums.Role;

@EqualsAndHashCode(callSuper = true)
@Entity @Data @NoArgsConstructor
public class Organiser extends Account {
    private String companyName;
    private String companyAddress;
    private String companyPhone;

    public Organiser(String email, String name, String password, String companyName, String address, String phoneNumber) {
        super(email, name, password);
        this.setRole(Role.ORGANISER);
        this.companyName = companyName;
        this.companyAddress = address;
        this.companyPhone = phoneNumber;
    }
}

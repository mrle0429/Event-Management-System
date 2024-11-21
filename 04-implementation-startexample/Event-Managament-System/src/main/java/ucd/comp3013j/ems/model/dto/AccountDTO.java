package ucd.comp3013j.ems.model.dto;

import lombok.Data;

@Data
public class AccountDTO {
    public String name;
    public String email;
    public String password;
    public String role;
    public String companyName;
    public String companyAddress;
    public String companyPhone;
}

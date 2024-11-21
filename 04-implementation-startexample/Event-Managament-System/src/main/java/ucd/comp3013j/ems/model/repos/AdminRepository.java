package ucd.comp3013j.ems.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucd.comp3013j.ems.model.Administrator;

public interface AdminRepository extends JpaRepository<Administrator, Long> {
    Administrator findByEmail(String email);
}

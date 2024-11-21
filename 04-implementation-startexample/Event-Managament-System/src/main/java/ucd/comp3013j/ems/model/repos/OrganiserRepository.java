package ucd.comp3013j.ems.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucd.comp3013j.ems.model.Organiser;

public interface OrganiserRepository extends JpaRepository<Organiser, Long> {
    Organiser findByEmail(String email);
}

package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.entities.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}

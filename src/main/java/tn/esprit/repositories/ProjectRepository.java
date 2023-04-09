package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.entities.Project;
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}

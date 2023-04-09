package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.entities.Role;
import tn.esprit.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.firstName=?1 And u.lastName=?2")
    User retrieveScrumMasterByNameEtPrenom(String fName, String lName);

    User findByFirstNameAndLastNameAndRole(String fName, String lName, Role role);


}
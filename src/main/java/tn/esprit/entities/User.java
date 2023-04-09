package tn.esprit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;
	private String pwd;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private Role role;

	@ManyToMany
	@JsonIgnore
	Set<Project> projectsDev;

	 @OneToMany
	 @JsonIgnore
	 Set<Project> projectsScrum;
}

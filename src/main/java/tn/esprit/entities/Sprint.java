package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sprint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	Project project;
}

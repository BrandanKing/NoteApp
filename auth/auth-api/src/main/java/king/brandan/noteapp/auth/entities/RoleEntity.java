package king.brandan.noteapp.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleEntity {

	@Id
	@Column(name = "guid", updatable = false)
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 255)
	private String code;

	@NotNull
	@Size(max = 255)
	private String name;

}

package king.brandan.noteapp.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name = "guid", updatable = false)
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@NotNull
	private String username;

	@NotNull
	private String email;

	@NotNull
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;
}

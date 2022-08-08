package king.brandan.noteapp.auth.repositories;

import king.brandan.noteapp.auth.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByCode(String code);
}

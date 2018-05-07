package rocks.gebsattel.hochzeit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.gebsattel.hochzeit.domain.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}

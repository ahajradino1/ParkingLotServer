package ba.unsa.etf.zavrsni.server.repositories;

import ba.unsa.etf.zavrsni.server.models.auth.Role;
import ba.unsa.etf.zavrsni.server.models.auth.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

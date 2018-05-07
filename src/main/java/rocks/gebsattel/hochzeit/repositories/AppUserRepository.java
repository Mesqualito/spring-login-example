package rocks.gebsattel.hochzeit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.gebsattel.hochzeit.domain.AppUser;

@Repository("appUserRepository")
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
    AppUser findByCode(String code);

}

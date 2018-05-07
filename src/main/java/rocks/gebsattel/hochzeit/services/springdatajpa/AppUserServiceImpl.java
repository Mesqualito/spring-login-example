package rocks.gebsattel.hochzeit.services.springdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rocks.gebsattel.hochzeit.domain.AppUser;
import rocks.gebsattel.hochzeit.domain.Role;
import rocks.gebsattel.hochzeit.enums.RoleStatus;
import rocks.gebsattel.hochzeit.repositories.AppUserRepository;
import rocks.gebsattel.hochzeit.repositories.RoleRepository;
import rocks.gebsattel.hochzeit.services.AppUserService;

import java.util.Arrays;
import java.util.HashSet;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser findAppUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public AppUser findAppUserByCode(String code) {
        return appUserRepository.findByCode(code);
    }

    @Override
    public void saveAppUser(AppUser appUser) {
        user.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setActive(1);
        Role appUserRole = roleRepository.findByRole(RoleStatus.ADMIN);
        appUser.setRoles(new HashSet<Role>(Arrays.asList(appUserRole)));
        appUserRepository.save(appUser);
    }

}

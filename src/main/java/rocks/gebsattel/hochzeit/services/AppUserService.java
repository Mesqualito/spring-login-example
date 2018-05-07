package rocks.gebsattel.hochzeit.services;

import rocks.gebsattel.hochzeit.domain.AppUser;

public interface AppUserService {

    public AppUser findAppUserByEmail(String email);
    public AppUser findAppUserByCode(String code);
    public void saveAppUser(AppUser appUser);

}

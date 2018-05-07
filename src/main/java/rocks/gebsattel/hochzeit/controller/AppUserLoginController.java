package rocks.gebsattel.hochzeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rocks.gebsattel.hochzeit.domain.AppUser;
import rocks.gebsattel.hochzeit.services.AppUserService;

import javax.validation.Valid;

@Controller
public class AppUserLoginController {

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        AppUser appUser = new AppUser();
        modelAndView.addObject("appUser", appUser);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public ModelAndView createNewAppUser(@Valid AppUser appUser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        AppUser appUserExists = appUserService.findAppUserByEmail(appUser.getEmail());
        if(appUserExists != null) {
            bindingResult
                    .rejectValue("email", "error.appUser",
                            "Es gibt bereits einen Zugang mit dieser eMail-Adresse!");
        }
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        } else {
            appUserService.saveAppUser(appUser);
            modelAndView.addObject("successMessage", "Zugang wurde erfolgreich angelegt!");
            modelAndView.addObject("appUser", new AppUser());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserService.findAppUserByEmail(auth.getName());
        modelAndView.addObject("appUserName", "Willkommen " + appUser.getFirstName() + " "
            + appUser.getLastName() + " (" + appUser.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Zugang nur für Benutzer mit ADMIN-Role verfügbar");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}

package rocks.gebsattel.hochzeit.domain;

import org.hibernate.validator.constraints.Length;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appuser_id")
    private int appuser_id;

    @Column(name = "email")
    @Email(message = "Bitte eine gültige eMail-Adresse angeben!")
    @NotEmpty(message = "Bitte eine eMail-Adresse angeben!")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "Dein Passwort muss mindestens fünf Zeichen umfassen!")
    @NotEmpty(message = "Bitte ein Passwort eingeben!")
    @Transient
    private String password;

    @Column(name = "code")
    @Length(min = 5, message = "Dein Code muss mindestens fünf Zeichen umfassen!")
    @NotEmpty(message = "Bitte den Code eingeben!")
    private String code;

    @Column(name = "first_name")
    @NotEmpty(message = "Bitte gib Deinen Vornamen ein!")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Bitte gib Deinen Nachnamen an!")
    private String lastName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "appuser_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return appuser_id;
    }

    public void setId(int appuser_id) {
        this.appuser_id = appuser_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

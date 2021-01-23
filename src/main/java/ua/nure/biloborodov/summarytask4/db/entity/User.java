package ua.nure.biloborodov.summarytask4.db.entity;

import ua.nure.biloborodov.summarytask4.db.Role;

/**
 * Implements the Student entity.
 */
public class User extends Entity {

    private int role;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return Role.define(role);
    }

    public void setRole(Role role) {
        this.role = role.ordinal();
    }
}

/**
 * FuelMe APP
 * Enterprise Application Development - SE4040
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 */

package com.example.fuelme.models.auth;

/**
 * Model class for User
 *
 * @author IT19180526 - S.A.N.L.D. Chandrasiri
 * @version 1.0
 */
public class User {

    // Defined variables
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String role;

    // Default constructor
    public User() {
    }

    // Overload constructor
    public User(String id, String username, String fullName, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // String print method
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    // Getters and Setters for User
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

package com.example.COGIP.model;
import com.example.COGIP.VariableType.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private UserType role;
    public User(){

    }
    public User(String username, String password, UserType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    @Column(name = "Username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "Password", nullable = false)

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "Role")

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }
}

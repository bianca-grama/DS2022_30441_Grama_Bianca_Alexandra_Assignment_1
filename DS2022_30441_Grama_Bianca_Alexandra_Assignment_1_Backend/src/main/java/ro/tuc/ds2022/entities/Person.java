package ro.tuc.ds2022.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Person  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Person() {
    }

    public Person(Integer id, String name, Integer role, String username, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

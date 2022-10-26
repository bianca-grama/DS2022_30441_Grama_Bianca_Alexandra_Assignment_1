package ro.tuc.ds2022.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
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

}

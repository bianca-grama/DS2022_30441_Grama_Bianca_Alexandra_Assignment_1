package ro.tuc.ds2022.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class PersonDTO extends RepresentationModel<PersonDTO> {

    private Integer id;
    private String name;
    private Integer role;
    private String username;
    private String password;

    public PersonDTO() {
    }

    public PersonDTO(Integer id, String name, Integer role, String username, String password){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(role, personDTO.role) &&
                Objects.equals(name, personDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role);
    }
}

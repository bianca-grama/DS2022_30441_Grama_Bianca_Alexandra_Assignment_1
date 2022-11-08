package ro.tuc.ds2022.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "max_consumption")
    private Integer maxConsumption;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Person owner;

    public Device() {
    }

    public Device(Integer id, String description, String address, Integer maxConsumption, Person owner) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaxConsumption() {
        return maxConsumption;
    }

    public void setMaxConsumption(Integer maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}

package ro.tuc.ds2022.entities;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
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
}

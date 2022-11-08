package ro.tuc.ds2022.entities;

import javax.persistence.*;

@Entity
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "consumption")
    private Integer consumption;

    public Consumption() {
    }

    public Consumption(Integer id, Device device, String timestamp, Integer consumption) {
        this.id = id;
        this.device = device;
        this.timestamp = timestamp;
        this.consumption = consumption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }
}

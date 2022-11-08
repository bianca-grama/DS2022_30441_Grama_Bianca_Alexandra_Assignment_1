package ro.tuc.ds2022.dtos;

import org.springframework.hateoas.RepresentationModel;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {

    private Integer id;
    private String description;
    private String address;
    private Integer maxConsumption;
    private Integer ownerId;

    public DeviceDTO(Integer id, String description, String address, Integer maxConsumption, Integer ownerId) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.ownerId = ownerId;
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}

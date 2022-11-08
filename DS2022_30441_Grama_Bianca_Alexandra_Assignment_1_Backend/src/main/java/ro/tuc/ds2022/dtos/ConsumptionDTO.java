package ro.tuc.ds2022.dtos;

import org.springframework.hateoas.RepresentationModel;

public class ConsumptionDTO extends RepresentationModel<ConsumptionDTO> {

    private Integer id;
    private Integer deviceId;
    private String timestamp;
    private Integer consumption;

    public ConsumptionDTO(Integer id, Integer deviceId, String timestamp, Integer consumption){
        this.id = id;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.consumption = consumption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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

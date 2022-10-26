package ro.tuc.ds2022.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
public class DeviceDTO extends RepresentationModel<DeviceDTO> {

    private Integer id;
    private String description;
    private String address;
    private Integer maxConsumption;
    private Integer ownerId;

}

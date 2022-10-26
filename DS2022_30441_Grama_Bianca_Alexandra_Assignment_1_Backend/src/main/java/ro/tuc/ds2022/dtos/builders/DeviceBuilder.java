package ro.tuc.ds2022.dtos.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.dtos.DeviceDTO;
import ro.tuc.ds2022.dtos.PersonDTO;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.entities.Person;
import ro.tuc.ds2022.repositories.PersonRepository;

import java.util.Optional;

@Component
public class DeviceBuilder {

    @Autowired
    PersonRepository personRepository;

    public DeviceDTO toDTO(Device device) {
        Person owner = device.getOwner();
        return new DeviceDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMaxConsumption(), owner.getId());
    }


    public Device toEntity(DeviceDTO deviceDTO) {
        Device device = new Device();

        Optional<Person> ownerOpt = personRepository.findById(deviceDTO.getOwnerId());
        if (ownerOpt.isPresent()) {
            device.setOwner(ownerOpt.get());
        } else {
            throw new ResourceNotFoundException("Device's owner not found");
        }

        if (deviceDTO.getId() == null)
            device.setId(0);
        else
            device.setId(deviceDTO.getId());

        device.setDescription(deviceDTO.getDescription());
        device.setAddress(deviceDTO.getAddress());
        device.setMaxConsumption(deviceDTO.getMaxConsumption());

        return device;
    }

}

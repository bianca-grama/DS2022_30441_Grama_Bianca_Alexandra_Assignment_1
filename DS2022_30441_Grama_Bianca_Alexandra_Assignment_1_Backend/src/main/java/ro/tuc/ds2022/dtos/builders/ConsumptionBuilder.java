package ro.tuc.ds2022.dtos.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.dtos.ConsumptionDTO;
import ro.tuc.ds2022.entities.Consumption;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.repositories.DeviceRepository;

import java.util.Optional;

@Component
public class ConsumptionBuilder {

    @Autowired
    DeviceRepository deviceRepository;

    public ConsumptionDTO toDTO(Consumption consumption) {
        Device device = consumption.getDevice();
        return new ConsumptionDTO(consumption.getId(), device.getId(), consumption.getTimestamp(), consumption.getConsumption());
    }


    public Consumption toEntity(ConsumptionDTO consumptionDTO) {
        Consumption consumption = new Consumption();

        Optional<Device> deviceOpt = deviceRepository.findById(consumptionDTO.getDeviceId());
        if (deviceOpt.isPresent()) {
            consumption.setDevice(deviceOpt.get());
        } else {
            throw new ResourceNotFoundException("Device not found");
        }

        if (consumptionDTO.getId() == null) {
            consumption.setId(0);
        } else {
            consumption.setId(consumptionDTO.getId());
        }
        consumption.setTimestamp(consumptionDTO.getTimestamp());
        consumption.setConsumption(consumptionDTO.getConsumption());

        return consumption;

    }

}

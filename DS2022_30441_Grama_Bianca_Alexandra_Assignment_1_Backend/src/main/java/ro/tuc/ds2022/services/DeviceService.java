package ro.tuc.ds2022.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.dtos.DeviceDTO;
import ro.tuc.ds2022.dtos.builders.DeviceBuilder;
import ro.tuc.ds2022.dtos.builders.PersonBuilder;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.entities.Person;
import ro.tuc.ds2022.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    PersonService personService;

    @Autowired
    DeviceBuilder deviceBuilder;

    @Autowired
    PersonBuilder personBuilder;

    public List<DeviceDTO> findAllDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(deviceBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO findDeviceById(Integer id) {
        Optional<Device> deviceOpt = deviceRepository.findById(id);
        if (!deviceOpt.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return deviceBuilder.toDTO(deviceOpt.get());
    }

    public List<DeviceDTO> findDevicesForOwner(Integer ownerId){
        List<Device> deviceList = deviceRepository.findByOwnerId(ownerId);
        return deviceList.stream()
                .map(deviceBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public Integer insert(DeviceDTO deviceDTO) {
        Device device = deviceRepository.save(deviceBuilder.toEntity(deviceDTO));
        LOGGER.debug("Device with id {} was inserted in db", device.getId());
        return device.getId();
    }

    public DeviceDTO updateDevice(Integer id, DeviceDTO newDevice) {

        Optional<Device> deviceInDb = deviceRepository.findById(id);
        if (!deviceInDb.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        } else {
            deviceInDb.get().setDescription(newDevice.getDescription());
            deviceInDb.get().setAddress(newDevice.getAddress());
            deviceInDb.get().setMaxConsumption(newDevice.getMaxConsumption());
            Person owner = personBuilder.toEntity(personService.findPersonById(newDevice.getOwnerId()));
            deviceInDb.get().setOwner(owner);

            Device updatedDevice = deviceRepository.save(deviceInDb.get());
            LOGGER.debug("Device with id {} was updated", id);
            return deviceBuilder.toDTO(updatedDevice);
        }
    }

    public boolean deleteDeviceById(Integer id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            deviceRepository.delete(device.get());
            return true;
        }
        return false;
    }

}

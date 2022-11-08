package ro.tuc.ds2022.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.dtos.DeviceDTO;
import ro.tuc.ds2022.services.DeviceService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping()
    public ResponseEntity<String> insertDevice(@RequestBody DeviceDTO deviceDTO) {
        Integer deviceId = deviceService.insert(deviceDTO);
        if (deviceId != null) {
            return new ResponseEntity<>(String.format("Inserted device with ID %s.", deviceId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid data provided", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> getDevice(@PathVariable("id") Integer deviceId) {
        DeviceDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDTO>> getDevices() {
        List<DeviceDTO> dtos = deviceService.findAllDevices();
        for (DeviceDTO dto : dtos) {
            Link deviceLink = linkTo(methodOn(DeviceController.class)
                    .getDevice(dto.getId())).withRel("device");
            dto.add(deviceLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/owned-by/{ownerId}")
    public ResponseEntity<List<DeviceDTO>> getDevicesByOwner(@PathVariable("ownerId") Integer ownerId) {

        List<DeviceDTO> dtos = deviceService.findDevicesForOwner(ownerId);
        for (DeviceDTO dto : dtos) {
            Link deviceLink = linkTo(methodOn(DeviceController.class)
                    .getDevice(dto.getId())).withRel("device");
            dto.add(deviceLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable("id") Integer id, @RequestBody DeviceDTO deviceDTO) {
        DeviceDTO updatedDevice = deviceService.updateDevice(id, deviceDTO);
        if (updatedDevice != null) {
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable("id") Integer id) {
        boolean success = deviceService.deleteDeviceById(id);
        if (success) {
            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }
    }

}

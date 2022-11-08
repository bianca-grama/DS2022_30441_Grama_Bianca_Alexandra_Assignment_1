package ro.tuc.ds2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.dtos.ConsumptionDTO;
import ro.tuc.ds2022.services.ConsumptionService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(value = "/consumption")
public class ConsumptionController {

    private final ConsumptionService consumptionService;

    @Autowired
    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @PostMapping()
    public ResponseEntity<String> insertConsumption(@RequestBody ConsumptionDTO consumptionDTO) {
        Integer consumptionId = consumptionService.insert(consumptionDTO);
        if (consumptionId != null) {
            return new ResponseEntity<>(String.format("Inserted consumption with ID %s.", consumptionId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid data provided", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<ConsumptionDTO>> getConsumptions(@PathVariable("id") Integer deviceId) {
        List<ConsumptionDTO> dtos = consumptionService.findForDeviceId(deviceId);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}

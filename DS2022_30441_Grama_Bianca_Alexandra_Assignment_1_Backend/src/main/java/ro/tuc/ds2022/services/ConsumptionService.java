package ro.tuc.ds2022.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.dtos.ConsumptionDTO;
import ro.tuc.ds2022.dtos.DeviceDTO;
import ro.tuc.ds2022.dtos.builders.ConsumptionBuilder;
import ro.tuc.ds2022.entities.Consumption;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.repositories.ConsumptionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumptionService {

    @Autowired
    ConsumptionRepository consumptionRepository;

    @Autowired
    ConsumptionBuilder consumptionBuilder;

    public Integer insert(ConsumptionDTO consumptionDTO) {
        Consumption consumption = consumptionRepository.save(consumptionBuilder.toEntity(consumptionDTO));
        return consumption.getId();
    }

    public List<ConsumptionDTO> findForDeviceId(Integer deviceId){
        List<Consumption> consumptionList = consumptionRepository.findByDeviceId(deviceId);
        return consumptionList.stream()
                .map(consumptionBuilder::toDTO)
                .collect(Collectors.toList());
    }
}

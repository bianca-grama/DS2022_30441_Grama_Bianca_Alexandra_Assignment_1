package ro.tuc.ds2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.tuc.ds2022.entities.Consumption;
import ro.tuc.ds2022.entities.Device;

import java.util.List;

public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {

    @Query(nativeQuery = true, value = "SELECT * from consumption c where c.device_id = :id")
    List<Consumption> findByDeviceId(Integer id);

}

package ro.tuc.ds2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.tuc.ds2022.entities.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Query(nativeQuery = true, value = "SELECT * from device d where d.owner_id = :id")
    List<Device> findByOwnerId(Integer id);

}

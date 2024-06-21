package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dminior.backendSCM.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value="SELECT * FROM campaign_city WHERE campaign_id = ?1", nativeQuery = true)
    City getCityByProductId(Long campaignId); //dot pierwszego tutaj ?1
}

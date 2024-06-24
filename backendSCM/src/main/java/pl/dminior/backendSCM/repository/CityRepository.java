package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dminior.backendSCM.model.City;

import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    @Query(value = "SELECT * FROM city " +
            "LEFT JOIN campaign_city ON city.id = campaign_city.city_id " +
            "WHERE campaign_city.campaign_id = ?1",
            nativeQuery = true)
    City getCityByCampaignId(UUID campaignId);

    City getCityById(UUID cityId);

    City getByName(String city);
}

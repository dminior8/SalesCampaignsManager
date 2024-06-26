package pl.dminior.backendSCM.repository;

import pl.dminior.backendSCM.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {
    List<Campaign> getByProductId(UUID productId);
    Campaign getCampaignById(UUID campaignId);
    List<Campaign> findAllByAccountId(UUID id);
}
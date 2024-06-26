package pl.dminior.backendSCM.repository;

import org.springframework.data.jpa.repository.Query;
import pl.dminior.backendSCM.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dminior.backendSCM.model.Keyword;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {
    List<Campaign> getAllByProductIdAndAccountId(UUID productId, UUID userId);
    Campaign getCampaignById(UUID campaignId);
    List<Campaign> findAllByAccountId(UUID id);

}
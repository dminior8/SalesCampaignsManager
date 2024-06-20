package pl.dminior.backendSCM.service;

import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id).orElse(null);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Campaign campaign = campaignRepository.findById(id).orElse(null);
        if (campaign != null) {
            campaign.setName(campaignDetails.getName());
            campaign.setKeywords(campaignDetails.getKeywords());
            campaign.setBidAmount(campaignDetails.getBidAmount());
            campaign.setCampaignFund(campaignDetails.getCampaignFund());
            campaign.setStatus(campaignDetails.getStatus());
            campaign.setTown(campaignDetails.getTown());
            campaign.setRadius(campaignDetails.getRadius());
            return campaignRepository.save(campaign);
        }
        return null;
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}

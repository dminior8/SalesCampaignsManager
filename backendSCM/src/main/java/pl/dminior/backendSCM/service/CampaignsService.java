package pl.dminior.backendSCM.service;

import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.repository.CampaignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignsService {
    @Autowired
    private CampaignsRepository campaignsRepository;

    public List<Campaign> getAllCampaigns() {
        return campaignsRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignsRepository.findById(id).orElse(null);
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignsRepository.save(campaign);
    }

//    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
//        Campaign campaign = campaignsRepository.findById(id).orElse(null);
//        if (campaign != null) {
//            campaign.setName(campaignDetails.getName());
//            campaign.setKeywords(campaignDetails.getKeywords());
//            campaign.setBidAmount(campaignDetails.getBidAmount());
//            campaign.setCampaignFund(campaignDetails.getCampaignFund());
//            campaign.setStatus(campaignDetails.getStatus());
//            campaign.setTown(campaignDetails.getTown());
//            campaign.setRadius(campaignDetails.getRadius());
//            return campaignsRepository.save(campaign);
//        }
//        return null;
//    }

    public void deleteCampaign(Long id) {
        campaignsRepository.deleteById(id);
    }
}

package pl.dminior.backendSCM.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.dminior.backendSCM.dto.CampaignDTO;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;


    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }
//
//    private CampaignDTO convertToDto(Campaign campaign) {
//        CampaignDTO campaignDTO = new CampaignDTO();
//        campaignDTO.setId(campaign.getId());
//        campaignDTO.setName(campaign.getName());
//        campaignDTO.setKeywords(campaignRepository.getKeywordsByCampaingId(Long id));
//        campaignDTO.setBidAmount(campaign.getBidAmount());
//        campaignDTO.setFund(campaign.getFund());
//        campaignDTO.setStatus(campaign.getStatus());
//        campaignDTO.setCityId(campaign.getCityId());
//
//        // Pobieramy nazwę miasta z City, jeśli jest dostępne
//        if (campaign.getCityId() != null) {
//            campaignDTO.setCity(campaign.getCity().getName());
//        }
//
//        return campaignDTO;
//    }
}



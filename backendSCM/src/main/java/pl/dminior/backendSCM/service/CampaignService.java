package pl.dminior.backendSCM.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.dminior.backendSCM.dto.CreateCampaignDTO;
import pl.dminior.backendSCM.dto.EditCampaignDTO;
import pl.dminior.backendSCM.model.*;
import pl.dminior.backendSCM.repository.AccountRepository;
import pl.dminior.backendSCM.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import pl.dminior.backendSCM.repository.CityRepository;
import pl.dminior.backendSCM.repository.KeywordRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final AccountRepository accountRepository;
    private final KeywordRepository keywordRepository;
    private final CityRepository cityRepository;

    public Campaign getCampaignByCampaignId(UUID campaignId){
        return campaignRepository.getById(campaignId);
    }

    public List<Campaign> getAllCampaignsByProductIdAndUserId(UUID productId, UUID accountId) {
        return campaignRepository.getAllByProductIdAndAccountId(productId, accountId);
    }

    @Transactional
    public Campaign saveCampaign(CreateCampaignDTO createCampaignDTO) {
        Campaign campaign = new Campaign();
        campaign.setName(createCampaignDTO.getName());
        campaign.setBidAmount(createCampaignDTO.getBidAmount());
        campaign.setFund(createCampaignDTO.getFund());
        campaign.setStatus(createCampaignDTO.getStatus());
        campaign.setCity(createCampaignDTO.getCity());
        campaign.setRadius(createCampaignDTO.getRadius());
        campaign.setCreatedAt(createCampaignDTO.getCreatedAt());
        campaign.setProduct(createCampaignDTO.getProduct());
        campaign.setKeywords(createCampaignDTO.getKeywords());

        Optional<Account> account = accountRepository.findById(createCampaignDTO.getAccountId());
        campaign.setAccount(account.get());

        return campaignRepository.save(campaign);
    }

    @Transactional
    public void editCampaign(EditCampaignDTO editCampaignDTO) {
        Campaign campaign = campaignRepository.getCampaignById(editCampaignDTO.getId());
        if(campaign != null){
            campaign.setId(editCampaignDTO.getId());
            campaign.setName(editCampaignDTO.getName());
            campaign.setBidAmount(editCampaignDTO.getBidAmount());
            campaign.setFund(editCampaignDTO.getFund());
            campaign.setStatus(editCampaignDTO.getStatus());
            campaign.setCity(editCampaignDTO.getCity());
            campaign.setRadius(editCampaignDTO.getRadius());
            campaign.setCreatedAt(editCampaignDTO.getCreatedAt());
            campaign.setProduct(editCampaignDTO.getProduct());
            campaign.setKeywords(editCampaignDTO.getKeywords());

            Optional<Account> account = accountRepository.findById(editCampaignDTO.getAccountId());
            campaign.setAccount(account.get());

            campaignRepository.save(campaign);
        }

    }

    @Transactional
    public void deleteCampaign(UUID campaignId) {
        campaignRepository.deleteById(campaignId);
    }

    public List<Campaign> getAllCampaignsByAccountId(UUID id) {
        return campaignRepository.findAllByAccountId(id);
    }

    public List<Keyword> getAllKeywords() {
        return keywordRepository.getAll();
    }
    public List<City> getAllCities() {
        return cityRepository.getAll();
    }
}



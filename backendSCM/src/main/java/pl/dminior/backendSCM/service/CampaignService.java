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

import java.math.BigDecimal;
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
    private final UserService userService;

    public Optional<Campaign> getCampaignByCampaignId(UUID campaignId) {
        return campaignRepository.findById(campaignId);
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
    public Campaign editCampaign(EditCampaignDTO editCampaignDTO, UUID campaignId) {
        Double newFounds = Double.valueOf(editCampaignDTO.getFund().toString());
        Double oldFounds = campaignRepository.getFundById(campaignId).doubleValue();
        Double balance = Double.valueOf(accountRepository.getBalanceById(editCampaignDTO.getAccountId()).toString());

        if (newFounds < oldFounds) {
            return null;
        } else if (newFounds - oldFounds > balance) {
            return null;
        } else {
            Optional<Campaign> existingCampaign = campaignRepository.findById(campaignId);
            if (existingCampaign.isPresent()) {
                Campaign campaignToUpdate = existingCampaign.get();
                campaignToUpdate.setId(campaignId);
                campaignToUpdate.setName(editCampaignDTO.getName());
                campaignToUpdate.setBidAmount(editCampaignDTO.getBidAmount());
                campaignToUpdate.setFund(editCampaignDTO.getFund());
                campaignToUpdate.setStatus(editCampaignDTO.getStatus());
                campaignToUpdate.setCity(editCampaignDTO.getCity());
                campaignToUpdate.setRadius(editCampaignDTO.getRadius());
                campaignToUpdate.setCreatedAt(editCampaignDTO.getCreatedAt());
                campaignToUpdate.setProduct(editCampaignDTO.getProduct());
                campaignToUpdate.setKeywords(editCampaignDTO.getKeywords());

                campaignRepository.save(campaignToUpdate);
                BigDecimal newBalance = BigDecimal.valueOf(balance - (newFounds - oldFounds));
                userService.updateBalanceById(existingCampaign.get().getAccount().getId(), newBalance);

                return campaignRepository.save(campaignToUpdate);
            }
        }
        System.out.println("HEJKA NAKLEJKA4");
        return null;
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



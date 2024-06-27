package pl.dminior.backendSCM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dminior.backendSCM.dto.CampaignDTO;
import pl.dminior.backendSCM.dto.CampaignDetailsDTO;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.model.City;
import pl.dminior.backendSCM.model.Keyword;
import pl.dminior.backendSCM.repository.AccountRepository;
import pl.dminior.backendSCM.repository.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CampaignMapper {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CityRepository cityRepository;

//    @Named("mapToCampaignDTO")
//    @Mapping(target = "city", source = "name", qualifiedByName = "mapCityName")
//    @Mapping(target = "id", source = "campaign.id")
//    public abstract CampaignDTO mapToCampaignDTO(Campaign campaign);
//
//    @Named("mapCityName")
//    public String mapCityName(City city) {
//        return city.getName();
//    }

    public CampaignDTO mapToCampaignDTO(Campaign campaign, City city) {
        if (campaign == null) {
            return null;
        }
        CampaignDTO campaignDTO = new CampaignDTO();

        campaignDTO.setId(campaign.getId());

        if (city != null) {
            campaignDTO.setCity(city.getName());
        }
        if (campaign.getName() != null) {
            campaignDTO.setName(campaign.getName());
        }
        if (campaign.getKeywords() != null) {
            campaignDTO.setKeywords(mapToStringFromKeyword(campaign.getKeywords()));
        }
        if (campaign.getBidAmount() != null) {
            campaignDTO.setBidAmount(campaign.getBidAmount());
        }
        if (campaign.getFund() != null) {
            campaignDTO.setFund(campaign.getFund());
        }
        if (campaign.getStatus() != null) {
            campaignDTO.setStatus(campaign.getStatus());
        }
        if (campaign.getRadius() != 0) {
            campaignDTO.setRadius(campaign.getRadius());
        }
        if (campaign.getCreatedAt() != null) {
            campaignDTO.setCreatedAt(campaign.getCreatedAt());
        }

        return campaignDTO;
    }

    //    @Named("mapToCampaignDTO")
//    @Mapping(target = "city", source = "name", qualifiedByName = "mapCityName")
//    @Mapping(target = "id", source = "campaign.id")
//    public abstract CampaignDTO mapToCampaignDTO(Campaign campaign);
//
//    @Named("mapCityName")
//    public String mapCityName(City city) {
//        return city.getName();
//    }

    public CampaignDetailsDTO mapToCampaignDetailsDTO(Optional<Campaign> campaignOld) {
        if (campaignOld.isEmpty()) {
            return null;
        }
        Campaign campaign = campaignOld.get();
        CampaignDetailsDTO campaignDetailsDTO = new CampaignDetailsDTO();

        if (campaign.getName() != null) {
            campaignDetailsDTO.setName(campaign.getName());
        }

        if (campaign.getKeywords() != null) {
            campaignDetailsDTO.setKeywords(campaign.getKeywords());
        }

        if (campaign.getProduct() != null) {
            campaignDetailsDTO.setProduct(campaign.getProduct());
        }

        if (campaign.getBidAmount() != null) {
            campaignDetailsDTO.setBidAmount(campaign.getBidAmount());
        }

        if (campaign.getFund() != null) {
            campaignDetailsDTO.setFund(campaign.getFund());
        }

        if (campaign.getStatus() != null) {
            campaignDetailsDTO.setStatus(campaign.getStatus());
        }

        if (campaign.getCity() != null) {
            campaignDetailsDTO.setCity(campaign.getCity());
        }

        if (campaign.getRadius() >= 0) {
            campaignDetailsDTO.setRadius(campaign.getRadius());
        }

        return campaignDetailsDTO;
    }

    @Named("mapToStringFromKeyword")
    public List<String> mapToStringFromKeyword(List<Keyword> keywords) {
        return keywords.stream().map(Keyword::getName).toList();
    }

    public Campaign mapToCampaign(CampaignDTO campaignDTO) {
        if (campaignDTO == null) {
            return null;
        }

        Campaign campaign = new Campaign();

        // Mapowanie pól CampaignDTO na Campaign
        campaign.setName(campaignDTO.getName());
        campaign.setKeywords(mapToKeywordsFromString(campaignDTO.getKeywords()));
        campaign.setBidAmount(campaignDTO.getBidAmount());
        campaign.setFund(campaignDTO.getFund());
        campaign.setStatus(campaignDTO.getStatus());
        campaign.setRadius(campaignDTO.getRadius());
        campaign.setCreatedAt(campaignDTO.getCreatedAt());

        // Mapowanie city z nazwy na obiekt City
        if (campaignDTO.getCity() != null) {
            City city = cityRepository.getByName(campaignDTO.getCity());
            campaign.setCity(city);
        }

        return campaign;
    }

    @Named("mapToKeywordsFromString")
    public List<Keyword> mapToKeywordsFromString(List<String> keywordNames) {
        return keywordNames.stream().map(name -> {
            Keyword keyword = new Keyword();
            keyword.setName(name);
            return keyword;
        }).collect(Collectors.toList());
    }
}

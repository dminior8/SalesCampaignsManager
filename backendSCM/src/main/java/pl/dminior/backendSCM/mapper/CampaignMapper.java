package pl.dminior.backendSCM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dminior.backendSCM.dto.CampaignDTO;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.model.City;
import pl.dminior.backendSCM.model.Keyword;
import pl.dminior.backendSCM.repository.AccountRepository;
import pl.dminior.backendSCM.repository.CityRepository;

import java.util.List;

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
        if (city.getName() != null) {
            campaignDTO.setCity(city.getName());
        }
        campaignDTO.setId(campaign.getId());
        if (campaign.getName() != null) {
            campaignDTO.setName(campaign.getName());
        }


        campaignDTO.setKeywords(mapToStringFromKeyword(campaign.getKeywords()));


        campaignDTO.setBidAmount(campaign.getBidAmount());
        campaignDTO.setFund(campaign.getFund());
        campaignDTO.setStatus(campaign.getStatus());


        return campaignDTO;
    }

    @Named("mapToStringFromKeyword")
    public List<String> mapToStringFromKeyword(List<Keyword> keywords) {
        return keywords.stream().map(Keyword::getName).toList();
    }
}

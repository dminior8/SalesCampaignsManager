package pl.dminior.backendSCM.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import pl.dminior.backendSCM.dto.CampaignDTO;
import pl.dminior.backendSCM.mapper.CampaignMapper;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.model.City;
import pl.dminior.backendSCM.repository.CityRepository;
import pl.dminior.backendSCM.service.CampaignService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{productId}")
public class CampaignController {
    private final CampaignService campaignService;
    private final CityRepository cityRepository;
    private final CampaignMapper campaignMapper;


    //TODO: Odczytywanie listy produktów dla danej kampanii
    @GetMapping("/campaigns")
    public ResponseEntity<List<CampaignDTO>> getAllCampaignsByProductId(@PathVariable Long productId) {
        List<CampaignDTO> campaignDTOList = new ArrayList<>();
        List<Campaign> campaigns = campaignService.getAllCampaigns(); //getAllCampaignsByProductId(productId)
        campaigns.forEach(campaign -> {
            City city = cityRepository.getCityByProductId(campaign.getId());
            campaignDTOList.add(campaignMapper.mapToCampaignDTO(campaign,city));
        });
        return ResponseEntity.ok(campaignDTOList);
    }

    //TODO: Pobieranie szczegółów konkretnej kampanii
//    @GetMapping("/{campaignId}")
//    public ResponseEntity<CampaignDTO> getCampaignByCampaignId(@PathVariable Long productId) {
//        List<CampaignDTO> campaigns = campaignService.getCampaignByCampaignId(productId);
//        return ResponseEntity.ok(campaigns);
//    }

    //TODO: Dodawanie nowej kampanii dla produktu
//    @PostMapping("/campaigns")
//    public ResponseEntity<CampaignDTO> getCampaignByCampaignId(@PathVariable Long productId) {
//        List<CampaignDTO> campaigns = campaignService.getCampaignByCampaignId(productId);
//        return ResponseEntity.ok(campaigns);
//    }

    //TODO: Aktualizacja istniejącej kampanii
//    @PutMapping("/{campaignId}")
//    public ResponseEntity<CampaignDTO> putCampaignByCampaignId(@PathVariable Long productId) {
//        //List<CampaignDTO> campaigns = campaignService.getCampaignByCampaignId(productId);
//        //return ResponseEntity.ok(campaigns);
//    }

    //TODO: Usuwanie kampanii
//    @DeleteMapping("/{campaignId}")
//    public ResponseEntity<CampaignDTO> deleteCampaignByCampaignId(@PathVariable Long productId) {
//        //List<CampaignDTO> campaigns = campaignService.getCampaignByCampaignId(productId);
//        //return ResponseEntity.ok(campaigns);
//    }
}

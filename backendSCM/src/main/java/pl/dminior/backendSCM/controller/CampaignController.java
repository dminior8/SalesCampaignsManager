package pl.dminior.backendSCM.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pl.dminior.backendSCM.dto.CampaignDTO;
import pl.dminior.backendSCM.dto.CreateCampaignDTO;
import pl.dminior.backendSCM.dto.EditCampaignDTO;
import pl.dminior.backendSCM.mapper.CampaignMapper;
import pl.dminior.backendSCM.model.*;
import pl.dminior.backendSCM.payloads.response.MessageResponse;
import pl.dminior.backendSCM.repository.CityRepository;
import pl.dminior.backendSCM.security.services.AccountDetailsImpl;
import pl.dminior.backendSCM.service.CampaignService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
public class CampaignController {
    private final CampaignService campaignService;
    private final CityRepository cityRepository;
    private final CampaignMapper campaignMapper;


    //Odczytywanie listy wszystkich kampanii dla danego produktu
    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getAllCampaignsByProductId(@PathVariable UUID productId, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            AccountDetailsImpl accountDetailsImpl = (AccountDetailsImpl) authentication.getPrincipal();
            UUID accountId = accountDetailsImpl.getId();
            List<CampaignDTO> campaignDTOList = new ArrayList<>();
            List<Campaign> campaigns = campaignService.getAllCampaignsByProductIdAndUserId(productId, accountId);
            campaigns.forEach(campaign -> {
                City city = cityRepository.getCityByCampaignId(campaign.getId());
                campaignDTOList.add(campaignMapper.mapToCampaignDTO(campaign, city));
            });
            return ResponseEntity.ok(campaignDTOList);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    //Pobieranie wszystkich dostępnych kampanii
    @GetMapping
    public ResponseEntity<?> getAllCampaigns(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            AccountDetailsImpl accountDetailsImpl = (AccountDetailsImpl) authentication.getPrincipal();
            UUID id = accountDetailsImpl.getId();
            List<CampaignDTO> campaignDTOList = new ArrayList<>();
            List<Campaign> campaigns = campaignService.getAllCampaignsByAccountId(id);
            campaigns.forEach(campaign -> {
                City city = cityRepository.getCityByCampaignId(campaign.getId());
                campaignDTOList.add(campaignMapper.mapToCampaignDTO(campaign, city));
            });
            return ResponseEntity.ok(campaignDTOList);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    //Odczytywanie szczegółów o konkretnej kampanii (dot. konkretnego produktu)
    @GetMapping("/{campaignId}")
    public ResponseEntity<?> getCampaignById(@PathVariable UUID campaignId, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Optional<Campaign> campaign = campaignService.getCampaignByCampaignId(campaignId);
            if (campaign.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(campaignMapper.mapToCampaignDetailsDTO(campaign));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    //Dodawanie nowej kampanii dla produktu
    @PostMapping("/products/{productId}/add")
    public ResponseEntity<?> createCampaignForProduct(@RequestBody CreateCampaignDTO createCampaignDTO, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            campaignService.saveCampaign(createCampaignDTO);
            return ResponseEntity.ok().body(new MessageResponse("Campaign added successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    //Aktualizacja istniejącej kampanii
    @PutMapping("/{campaignId}/edit")
    public ResponseEntity<?> editCampaignForProduct(@PathVariable UUID campaignId, @RequestBody EditCampaignDTO editCampaignDTO, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {

            Campaign editedCampaign = campaignService.editCampaign(editCampaignDTO, campaignId);
            if (editedCampaign != null) {
                return ResponseEntity.ok().body(editedCampaign);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong values");
    }


    //Usuwanie kampanii
    @DeleteMapping("/{campaignId}")
    public ResponseEntity<?> deleteCampaignByCampaignId(@PathVariable UUID campaignId, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            campaignService.deleteCampaign(campaignId);
            return ResponseEntity.ok().body(new MessageResponse("Campaign deleted successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}

package pl.dminior.backendSCM.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import pl.dminior.backendSCM.dto.CampaignDTO;
import pl.dminior.backendSCM.dto.CreateCampaignDTO;
import pl.dminior.backendSCM.dto.EditCampaignDTO;
import pl.dminior.backendSCM.mapper.CampaignMapper;
import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.model.City;
import pl.dminior.backendSCM.security.payloads.response.MessageResponse;
import pl.dminior.backendSCM.repository.AccountRepository;
import pl.dminior.backendSCM.repository.CityRepository;
import pl.dminior.backendSCM.service.CampaignService;
import org.springframework.web.bind.annotation.*;
import pl.dminior.backendSCM.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
public class CampaignController {
    private final CampaignService campaignService;
    private final CityRepository cityRepository;
    private final CampaignMapper campaignMapper;
    private final ProductService productService;
    private final AccountRepository accountRepository;


    //Odczytywanie listy wszystkich kampanii dla danego produktu
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<CampaignDTO>> getAllCampaignsByProductId(@PathVariable UUID productId) {
        List<CampaignDTO> campaignDTOList = new ArrayList<>();
        List<Campaign> campaigns = campaignService.getAllCampaignsByProductId(productId);
        campaigns.forEach(campaign -> {
            City city = cityRepository.getCityByCampaignId(campaign.getId()); //getCityByProductId było wcześniej
            campaignDTOList.add(campaignMapper.mapToCampaignDTO(campaign,city));
        });
        return ResponseEntity.ok(campaignDTOList);
    }

    //TODO: Lista produtków dla danego użytkownika
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllProducts((@AuthenticationPrincipal UserDetails userDetails) {
//        List<Product> products = new ArrayList<>();
//        productService.getAllProducts();
//        return ResponseEntity.ok(productService.getAllProducts());
//    }

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
        List<CampaignDTO> campaignDTOList = new ArrayList<>();
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        campaigns.forEach(campaign -> {
            City city = cityRepository.getCityByCampaignId(campaign.getId()); //getCityByProductId było wcześniej
            campaignDTOList.add(campaignMapper.mapToCampaignDTO(campaign,city));
        });
        return ResponseEntity.ok(campaignDTOList);
    }

    //Odczytywanie szczegółów o konkretnej kampanii (dot. konkretnego produktu)
    @GetMapping("/{campaignId}")
    public ResponseEntity<CampaignDTO> getCampaignById(@PathVariable UUID campaignId) {
        Campaign campaign = campaignService.getCampaignByCampaignId(campaignId);
        if (campaign == null) {
            return ResponseEntity.notFound().build();
        }
        City city = cityRepository.getCityById(campaign.getCity().getId());
        CampaignDTO campaignDTO = campaignMapper.mapToCampaignDTO(campaign, city);
        return ResponseEntity.ok(campaignDTO);
    }

    //Dodawanie nowej kampanii dla produktu
    @PostMapping("/add") //dodałem, w razie czego sprawdzić
    //public ResponseEntity<CampaignDTO> createCampaignForProduct(@PathVariable UUID productId, @Valid @RequestBody CampaignDTO campaignDTO) {
    public ResponseEntity<MessageResponse> createCampaignForProduct(@RequestBody CreateCampaignDTO createCampaignDTO) {
        campaignService.saveCampaign(createCampaignDTO);
        return ResponseEntity.ok().body(new MessageResponse("Kampania dodana pomyślnie!")); //campaignService.saveCampaign(newCampaignDTO)
    }


    //Aktualizacja istniejącej kampanii
    @PutMapping
    public ResponseEntity<MessageResponse> editCampaignForProduct(@RequestBody EditCampaignDTO editCampaignDTO) {
        campaignService.editCampaign(editCampaignDTO);
        return ResponseEntity.ok().body(new MessageResponse("Kampania edytowana pomyślnie!")); //campaignService.saveCampaign(newCampaignDTO)
    }

    //Usuwanie kampanii
    @DeleteMapping("/{campaignId}")
    public ResponseEntity<MessageResponse> deleteCampaignByCampaignId(@PathVariable UUID campaignId) {
        campaignService.deleteCampaign(campaignId);
        return ResponseEntity.ok().body(new MessageResponse("Kampania usunięta pomyślnie!"));
    }
}

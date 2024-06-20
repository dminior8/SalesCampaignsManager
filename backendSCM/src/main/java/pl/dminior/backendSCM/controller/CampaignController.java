package pl.dminior.backendSCM.controller;


import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }

    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/{id}")
    public Campaign updateCampaign(@PathVariable Long id, @RequestBody Campaign campaignDetails) {
        return campaignService.updateCampaign(id, campaignDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
    }
}

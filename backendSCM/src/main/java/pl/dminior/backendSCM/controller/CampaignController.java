package pl.dminior.backendSCM.controller;


import pl.dminior.backendSCM.model.Campaign;
import pl.dminior.backendSCM.service.CampaignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
    @Autowired
    private CampaignsService campaignsService;

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignsService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public Campaign getCampaignById(@PathVariable Long id) {
        return campaignsService.getCampaignById(id);
    }

    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignsService.createCampaign(campaign);
    }

//    @PutMapping("/{id}")
//    public Campaign updateCampaign(@PathVariable Long id, @RequestBody Campaign campaignDetails) {
//        return campaignsService.updateCampaign(id, campaignDetails);
//    }

    @DeleteMapping("/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignsService.deleteCampaign(id);
    }
}

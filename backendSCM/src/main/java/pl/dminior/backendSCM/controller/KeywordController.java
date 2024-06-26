package pl.dminior.backendSCM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dminior.backendSCM.model.Keyword;
import pl.dminior.backendSCM.service.CampaignService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
public class KeywordController {
    private final CampaignService campaignService;

    @GetMapping("/keywords")
    public ResponseEntity<?> getAllKeywords(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            List<Keyword> keywords = campaignService.getAllKeywords();
            return ResponseEntity.ok(keywords);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}

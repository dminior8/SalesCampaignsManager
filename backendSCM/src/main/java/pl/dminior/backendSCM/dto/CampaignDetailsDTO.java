package pl.dminior.backendSCM.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dminior.backendSCM.model.City;
import pl.dminior.backendSCM.model.EnumCampaignStatus;
import pl.dminior.backendSCM.model.Keyword;
import pl.dminior.backendSCM.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDetailsDTO {
    private String name;
    private List<Keyword> keywords;
    private BigDecimal bidAmount;
    private BigDecimal fund;
    private EnumCampaignStatus status;
    private Product product;
    private City city;
    private Integer radius;
}


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
public class EditCampaignDTO {
    private UUID id;
    private String name;
    private BigDecimal bidAmount;
    private BigDecimal fund;
    private EnumCampaignStatus status;
    private City city;
    private Integer radius;
    private LocalDateTime createdAt;
    private Product product;
    private List<Keyword> keywords;
    private UUID accountId;
}


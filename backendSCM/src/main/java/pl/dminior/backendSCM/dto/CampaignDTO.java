package pl.dminior.backendSCM.dto;
import lombok.*;
import pl.dminior.backendSCM.model.EnumCampaignStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO {

    private UUID id;
    private String name;
    private List<String> keywords;
    private BigDecimal bidAmount;
    private BigDecimal fund;
    private EnumCampaignStatus status;
    private String city;
    private Integer radius;
    private LocalDateTime createdAt;

}

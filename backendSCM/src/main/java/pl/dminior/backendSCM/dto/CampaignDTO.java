package pl.dminior.backendSCM.dto;
import lombok.*;
import pl.dminior.backendSCM.model.CampaignStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//name
//keywords
//bidAmount
//fund
//status
//cityId
//radius
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO {

    private Long id;
    private String name;
    private List<String> keywords;
    private BigDecimal bidAmount;
    private BigDecimal fund;
    private CampaignStatus status;
    private String city; // Pole na nazwę miasta
    private Integer radius;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

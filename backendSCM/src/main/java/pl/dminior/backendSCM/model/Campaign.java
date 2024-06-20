package pl.dminior.backendSCM.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String keywords;
    private Double bidAmount;
    private Double campaignFund;
    private String status;
    private String town;
    private Integer radius;

}
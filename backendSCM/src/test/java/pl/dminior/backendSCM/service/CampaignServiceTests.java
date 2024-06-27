package pl.dminior.backendSCM.service;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.dminior.backendSCM.dto.CreateCampaignDTO;
import pl.dminior.backendSCM.dto.EditCampaignDTO;
import pl.dminior.backendSCM.model.*;
import pl.dminior.backendSCM.repository.AccountRepository;
import pl.dminior.backendSCM.repository.CampaignRepository;
import pl.dminior.backendSCM.repository.CityRepository;
import pl.dminior.backendSCM.repository.KeywordRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CampaignServiceTests {

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private KeywordRepository keywordRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CampaignService campaignService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCampaign() {
        // Arrange
        CreateCampaignDTO createCampaignDTO = new CreateCampaignDTO();
        createCampaignDTO.setName("Test Campaign");
        createCampaignDTO.setBidAmount(BigDecimal.valueOf(100));
        createCampaignDTO.setFund(BigDecimal.valueOf(1000));
        createCampaignDTO.setStatus(EnumCampaignStatus.ON);
        createCampaignDTO.setCity(new City());
        createCampaignDTO.setRadius(10);
        createCampaignDTO.setCreatedAt(LocalDateTime.now());
        createCampaignDTO.setProduct(new Product());
        createCampaignDTO.setKeywords(new ArrayList<>());
        UUID accountId = UUID.randomUUID();
        createCampaignDTO.setAccountId(accountId);

        Account account = new Account();
        account.setId(accountId);

        Campaign savedCampaign = new Campaign();
        savedCampaign.setId(UUID.randomUUID());
        savedCampaign.setName(createCampaignDTO.getName());
        savedCampaign.setBidAmount(createCampaignDTO.getBidAmount());
        savedCampaign.setFund(createCampaignDTO.getFund());
        savedCampaign.setStatus(createCampaignDTO.getStatus());
        savedCampaign.setCity(createCampaignDTO.getCity());
        savedCampaign.setRadius(createCampaignDTO.getRadius());
        savedCampaign.setCreatedAt(createCampaignDTO.getCreatedAt());
        savedCampaign.setProduct(createCampaignDTO.getProduct());
        savedCampaign.setKeywords(createCampaignDTO.getKeywords());
        savedCampaign.setAccount(account);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(campaignRepository.save(any(Campaign.class))).thenReturn(savedCampaign);

        // Act
        Campaign result = campaignService.saveCampaign(createCampaignDTO);

        // Assert
        assertEquals(savedCampaign.getId(), result.getId());
        assertEquals(createCampaignDTO.getName(), result.getName());
        assertEquals(createCampaignDTO.getBidAmount(), result.getBidAmount());
        assertEquals(createCampaignDTO.getFund(), result.getFund());
        assertEquals(createCampaignDTO.getStatus(), result.getStatus());
        assertEquals(createCampaignDTO.getCity(), result.getCity());
        assertEquals(createCampaignDTO.getRadius(), result.getRadius());
        assertEquals(createCampaignDTO.getCreatedAt(), result.getCreatedAt());
        assertEquals(createCampaignDTO.getProduct(), result.getProduct());
        assertEquals(createCampaignDTO.getKeywords(), result.getKeywords());
        assertEquals(account, result.getAccount());

        verify(accountRepository, times(1)).findById(accountId);
        verify(campaignRepository, times(1)).save(any(Campaign.class));
    }

    @Test
    public void testEditCampaign() {
        // Arrange
        EditCampaignDTO editCampaignDTO = new EditCampaignDTO();
        UUID campaignId = UUID.randomUUID();
        editCampaignDTO.setId(campaignId);
        editCampaignDTO.setName("Updated Campaign Name");
        editCampaignDTO.setBidAmount(BigDecimal.valueOf(200));
        editCampaignDTO.setFund(BigDecimal.valueOf(2000));
        editCampaignDTO.setStatus(EnumCampaignStatus.OFF);
        editCampaignDTO.setCity(new City());
        editCampaignDTO.setRadius(20);
        editCampaignDTO.setCreatedAt(LocalDateTime.now());
        editCampaignDTO.setProduct(new Product());
        editCampaignDTO.setKeywords(new ArrayList<>());
        UUID accountId = UUID.randomUUID();
        editCampaignDTO.setAccountId(accountId);

        Campaign existingCampaign = new Campaign();
        existingCampaign.setId(campaignId);
        existingCampaign.setAccount(new Account());
        when(campaignRepository.getCampaignById(campaignId)).thenReturn(existingCampaign);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(existingCampaign.getAccount()));

        // Act
        campaignService.editCampaign(editCampaignDTO);

        // Assert
        assertEquals(editCampaignDTO.getId(), existingCampaign.getId());
        assertEquals(editCampaignDTO.getName(), existingCampaign.getName());
        assertEquals(editCampaignDTO.getBidAmount(), existingCampaign.getBidAmount());
        assertEquals(editCampaignDTO.getFund(), existingCampaign.getFund());
        assertEquals(editCampaignDTO.getStatus(), existingCampaign.getStatus());
        assertEquals(editCampaignDTO.getCity(), existingCampaign.getCity());
        assertEquals(editCampaignDTO.getRadius(), existingCampaign.getRadius());
        assertEquals(editCampaignDTO.getCreatedAt(), existingCampaign.getCreatedAt());
        assertEquals(editCampaignDTO.getProduct(), existingCampaign.getProduct());

        verify(campaignRepository, times(1)).getCampaignById(campaignId);
        verify(accountRepository, times(1)).findById(accountId);
        verify(campaignRepository, times(1)).save(existingCampaign);
    }

    @Test
    public void testDeleteCampaign() {
        // Arrange
        UUID campaignId = UUID.randomUUID();

        // Act
        campaignService.deleteCampaign(campaignId);

        // Assert
        verify(campaignRepository, times(1)).deleteById(campaignId);
    }

    @Test
    public void testGetAllCampaignsByAccountId() {
        // Arrange
        UUID accountId = UUID.randomUUID();
        List<Campaign> expectedCampaigns = new ArrayList<>();
        when(campaignRepository.findAllByAccountId(accountId)).thenReturn(expectedCampaigns);

        // Act
        List<Campaign> result = campaignService.getAllCampaignsByAccountId(accountId);

        // Assert
        assertEquals(expectedCampaigns, result);
        verify(campaignRepository, times(1)).findAllByAccountId(accountId);
    }

    @Test
    public void testGetCampaignByCampaignId() {
        // Arrange
        UUID campaignId = UUID.randomUUID();
        Campaign expectedCampaign = new Campaign();
        expectedCampaign.setId(campaignId);
        when(campaignRepository.getById(campaignId)).thenReturn(expectedCampaign);

        // Act
        Campaign result = campaignService.getCampaignByCampaignId(campaignId);

        // Assert
        assertEquals(expectedCampaign, result);
        verify(campaignRepository, times(1)).getById(campaignId);
    }

    @Test
    public void testGetAllCampaignsByProductIdAndUserId() {
        // Arrange
        UUID productId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        List<Campaign> expectedCampaigns = new ArrayList<>();
        when(campaignRepository.getAllByProductIdAndAccountId(productId, accountId)).thenReturn(expectedCampaigns);

        // Act
        List<Campaign> result = campaignService.getAllCampaignsByProductIdAndUserId(productId, accountId);

        // Assert
        assertEquals(expectedCampaigns, result);
        verify(campaignRepository, times(1)).getAllByProductIdAndAccountId(productId, accountId);
    }

    @Test
    public void testGetAllKeywords() {
        // Arrange
        List<Keyword> expectedKeywords = new ArrayList<>();
        when(keywordRepository.getAll()).thenReturn(expectedKeywords);

        // Act
        List<Keyword> result = campaignService.getAllKeywords();

        // Assert
        assertEquals(expectedKeywords, result);
        verify(keywordRepository, times(1)).getAll();
    }

    @Test
    public void testGetAllCities() {
        // Arrange
        List<City> expectedCities = new ArrayList<>();
        when(cityRepository.getAll()).thenReturn(expectedCities);

        // Act
        List<City> result = campaignService.getAllCities();

        // Assert
        assertEquals(expectedCities, result);
        verify(cityRepository, times(1)).getAll();
    }

}

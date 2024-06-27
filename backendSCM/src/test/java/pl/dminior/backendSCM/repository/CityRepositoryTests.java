package pl.dminior.backendSCM.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dminior.backendSCM.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@DataJpaTest
public class CityRepositoryTests {

    @Mock
    private CityRepository cityRepository;

    @Test
    public void testSaveCity() {
        // Arrange
        City city = createCity("Warsaw");

        // Act
        cityRepository.save(city);
        when(cityRepository.findById(city.getId())).thenReturn(Optional.of(city));

        // Assert
        Optional<City> savedCity = cityRepository.findById(city.getId());
        Assertions.assertTrue(savedCity.isPresent());
        Assertions.assertEquals(city.getName(), savedCity.get().getName());
    }

    @Test
    public void testGetCityById() {
        // Arrange
        City city = createCity("Berlin");
        cityRepository.save(city);

        // Act
        when(cityRepository.getCityById(city.getId())).thenReturn(city);
        City foundCity = cityRepository.getCityById(city.getId());

        // Assert
        Assertions.assertNotNull(foundCity);
        Assertions.assertEquals(city, foundCity);
    }

    @Test
    public void testGetByName() {
        // Arrange
        City city = createCity("Paris");
        cityRepository.save(city);

        // Act
        when(cityRepository.getByName(city.getName())).thenReturn(city);
        City foundCity = cityRepository.getByName(city.getName());

        // Assert
        Assertions.assertNotNull(foundCity);
        Assertions.assertEquals(city, foundCity);
    }

    @Test
    public void testGetAll() {
        // Arrange
        City city1 = createCity("Madrid");
        City city2 = createCity("Rome");
        cityRepository.save(city1);
        cityRepository.save(city2);

        // Act
        when(cityRepository.findAll()).thenReturn(new ArrayList<>(List.of(city1, city2)));
        List<City> cities = cityRepository.findAll();

        // Assert
        Assertions.assertEquals(2, cities.size());
        Assertions.assertTrue(cities.contains(city1));
        Assertions.assertTrue(cities.contains(city2));
    }

    private City createCity(String name) {
        City city = new City();
        city.setName(name);
        return city;
    }
}

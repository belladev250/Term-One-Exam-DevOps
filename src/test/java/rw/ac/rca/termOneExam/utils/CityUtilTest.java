package rw.ac.rca.termOneExam.utils;
import org.junit.Test;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.service.CityService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CityUtilTest {


    @Autowired
    private ICityRepository cityRepository;

    @Test
    public void noCityWithWeatherMoreThan40_test() {

        assertEquals(0, cityRepository.countWeatherGreaterThan(40));
    }

    @Test
    public void noCityWithWeatherLessThan10_test() {

        assertEquals(0, cityRepository.countWeatherLessThan(10));
    }

    @Test
    public void citiesContainsMusanzeAndKigali() {
        assertTrue(cityRepository.existsByName("Musanze"));

        assertTrue(cityRepository.existsByName("Kigali"));
    }

    @RunWith(MockitoJUnitRunner.class)
    public static class TestingSpy {

        @Spy
        private ICityRepository cityRepositoryBySpy;

        @InjectMocks
        private CityService cityService;

        @Test
        public void testSpying() {
            when(cityRepositoryBySpy.findAll()).thenReturn(Arrays.asList(new City("Kampala", 32), new City("Kenya", 32)));

            List<City> cities = cityService.getAll();

            assertEquals(2, cities.size());
        }

    }


    @RunWith(MockitoJUnitRunner.class)
    public static class TestingMock {

        @Mock
        private ICityRepository cityRepositoryByMock;

        @InjectMocks
        private CityService cityService;

        @Test
        public void testMocking() {
            when(cityRepositoryByMock.findAll()).thenReturn(Arrays.asList(new City("Kampala", 32), new City("kenya", 32)));

            List<City> cities = cityService.getAll();

            assertEquals(2, cities.size());
        }

    }
}

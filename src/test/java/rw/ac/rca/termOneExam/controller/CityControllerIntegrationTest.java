package rw.ac.rca.termOneExam.controller;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/all", String.class);
        JSONAssert.assertEquals("[{id:1,name:Musanze,weather:12},{id:1,name:kigali,weather:11}]", response, false);
    }

    @Test
    public void getAll_404() throws JSONException {
        ResponseEntity<APICustomResponse> city =
                this.restTemplate.getForObject("/all", APICustomResponse.class);
        JSONAssert.assertEquals("cities not found", response, false);
    }

    @Test
    public void getById_success() {
        ResponseEntity<City>  city = this.restTemplate.getForEntity("/all/1", City.class);

        assertTrue(city.getStatusCode().is2xxSuccessful();
        assertEquals("musanze", response.getBody().getName());
        assertEquals(10, response.getBody().getWeather());
    }

    @Test
    public void getById_404() {
        ResponseEntity<APICustomResponse> city =
                this.restTemplate.getForEntity("/all/1", APICustomResponse.class);

        assertTrue(city.getStatusCodeValue()==404);
        assertFalse(city.getBody().isStatus());
        assertEquals("city not found",city.getBody().getMessage());
    }

    @Test
    public void createCity_Success() {

      CreateCityDTO cityDTO=new CreateCityDTO(3l,"Nyabihu",120);

        ResponseEntity<City> response = this.restTemplate.postForEntity("/add", cityDTO, City.class);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Nyabihu",response.getBody().getName());


    }

    @Test
    public void createCity_404() {

        CreateCityDTO cityDTO=new CreateCityDTO(3l,"Nyabihu",120);
        ResponseEntity<APICustomResponse> response = this.restTemplate.
                postForEntity("/add", cityDTO, City.class);
        assertEquals("city not able to be created", response, false));


    }


}

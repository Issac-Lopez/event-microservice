package edu.msudenver.city;

import edu.msudenver.city.City;
import edu.msudenver.city.CityController;
import edu.msudenver.city.CityRepository;
import edu.msudenver.city.CityService;
import edu.msudenver.country.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private EntityManagerFactory entityManagerFactory;

    @MockBean
    private EntityManager entityManager;

    @SpyBean
    private CityService cityService;

    @BeforeEach
    public void setup() {
        cityService.entityManager = entityManager;
    }

    // GET ALL CITIES
    @Test
    public void testGetCities() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cities/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        City testCity = new City();
        testCity.setPostalCode("80129");
        testCity.setCountryCode("us");
        testCity.setName("Highlands Ranch");

        Mockito.when(cityRepository.findAll()).thenReturn(Arrays.asList(testCity));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Highlands Ranch"));
    }
    // GET ONE CITY
    @Test
    public void testGetCity() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cities/us/80129")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        City testCity = new City();
        testCity.setPostalCode("80129");
        testCity.setCountryCode("us");
        testCity.setName("Highlands Ranch");

        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(testCity));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(response.getContentAsString().contains("Highlands Ranch"));
    }
    // GET ONE CITY NOT FOUND 404
    @Test
    public void testGetCityNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cities/notfound")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }
    // POST CITY
    @Test
    public void testCreateCountry() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cities/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"countryCode\": \"us\", \"postalCode\": \"80129\", \"name\": \"Highlands Ranch\"}")
                .contentType(MediaType.APPLICATION_JSON);

        City testCity = new City();
        testCity.setPostalCode("80129");
        testCity.setCountryCode("us");
        testCity.setName("Highlands Ranch");
        Mockito.when(cityRepository.save(Mockito.any())).thenReturn(testCity);
        Mockito.when(cityRepository.saveAndFlush(Mockito.any())).thenReturn(testCity);


        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        //Issue Here *************************************************************************************************
        assertTrue(response.getContentAsString().contains("Highlands Ranch"));

    }
    // POST CITY BAD REQUEST 400
    @Test
    public void testCreateCityBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cities/")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"countryCode\": \"us\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(cityRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);
        Mockito.when(cityRepository.saveAndFlush(Mockito.any())).thenThrow(IllegalArgumentException.class);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }

    // PUT CITY
    @Test
    public void testUpdateCity() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/cities/us/80129")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"countryCode\":\"us\", \"cityName\": \"Highlands Ranch Updated\"}/{\"postalCode\":\"80129\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Country testCountry = new Country().setCountryName("Mexico").setCountryCode("us");

        City testCity = new City();
        testCity.setPostalCode("80129");
        testCity.setCountry(testCountry);
        testCity.setCountryCode("us");
        testCity.setName("Highlands Ranch");
        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(testCity));


        City testCityUpdated = new City();
        testCityUpdated.setPostalCode("80129");
        testCityUpdated.setCountry(testCountry);
        testCityUpdated.setCountryCode("us");
        testCityUpdated.setName("Highlands Ranch Updated");
        Mockito.when(cityRepository.save(Mockito.any())).thenReturn(testCityUpdated);
        Mockito.when(cityRepository.saveAndFlush(Mockito.any())).thenReturn(testCityUpdated);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertTrue(response.getContentAsString().contains("Highlands Ranch Updated"));
    }


    // PUT CITY NOT FOUND 404
    @Test
    public void testUpdateCityNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/cities/notfound/80129")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"countryCode\":\"notfound\", \"postalCode\": \"80129\", \"name\": \"Highlands Ranch\"}")
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }
    // PUT CITY BAD REQUEST 400
    @Test
    public void testUpdateCityBadRequest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/cities/us/80129")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"countryCode\":\"us\", \"name\": \"Highlands Ranch\"}")
                .contentType(MediaType.APPLICATION_JSON);

        City testCity = new City();
        testCity.setPostalCode("80129");
        testCity.setCountryCode("us");
        testCity.setName("Highlands Ranch");
        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(testCity));

        Mockito.when(cityRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);
        Mockito.when(cityRepository.saveAndFlush(Mockito.any())).thenThrow(IllegalArgumentException.class);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }
    // DELETE CITY
    @Test
    public void testDeleteCity() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/cities/us/80129")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        City testCity = new City();
        testCity.setPostalCode("80129");
        testCity.setCountryCode("us");
        testCity.setName("Highlands Ranch");
        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.of(testCity));
        Mockito.when(cityRepository.existsById(Mockito.any())).thenReturn(true);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    public void testDeleteCityNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/cities/notfound/80129")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(cityRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(cityRepository.existsById(Mockito.any())).thenReturn(false);
        Mockito.doThrow(IllegalArgumentException.class)
                .when(cityRepository)
                .deleteById(Mockito.any());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertTrue(response.getContentAsString().isEmpty());
    }
}
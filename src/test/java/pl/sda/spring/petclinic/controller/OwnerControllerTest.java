package pl.sda.spring.petclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.spring.petclinic.aop.ApplicationErrorHandler;
import pl.sda.spring.petclinic.exception.OwnerNotFoundException;
import pl.sda.spring.petclinic.model.Adress;
import pl.sda.spring.petclinic.model.Owner;
import pl.sda.spring.petclinic.service.OwnerService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class OwnerControllerTest {

    @Autowired
    private OwnerController ownerController; //klasa do przetestowania

    @MockBean
    private OwnerService ownerService; //dostep do bazy danych

    private MockMvc mockMvc; //udawany obiekt

    private List<Owner> owners;

    @Before
    public void initOwners() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .setControllerAdvice(new ApplicationErrorHandler())
                .build();

        //logike obslugi wyjatkow oddzielamy od logiki biznesowej aplikacji
        // i przezucamy to z wykorzystaniem aop do applicationhandlera

        owners = new ArrayList<>();
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstname("Jan");
        owner.setLastname("Kowalski");
        Adress adress = new Adress();
        adress.setCity("Poznan");
        adress.setCountry("Poland");
        adress.setPostalcode("60-000");
        adress.setStreet("Witacza");
        owner.setAdress(adress);
        owners.add(owner);

        owner = new Owner();
        owner.setId(1L);
        owner.setFirstname("Adam");
        owner.setLastname("Nowak");
        adress = new Adress();
        adress.setCity("Witam");
        adress.setCountry("Poland");
        adress.setPostalcode("80-000");
        adress.setStreet("Witamwitam");
        owner.setAdress(adress);
        owners.add(owner);
    }

    @Test
    public void should_return_owner_by_id() throws Exception {
        //mockowanie wywolania metody findOwnerById(1), tzn gdy wywolana ta metoda,
        //zwroc pierwszy element z listy owners
        given(ownerService.findOwnerById(1)).willReturn(owners.get(0));

        //wywolanie zadania
        mockMvc.perform(
                get("/api/v1/owner/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("Jan"))
                .andExpect(jsonPath("$.lastname").value("Kowalski"))
                .andExpect(jsonPath("$.adress.country").value("Poland"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_error_when_owner_not_found() throws Exception {
        given(ownerService.findOwnerById(Mockito.anyLong())).willThrow(OwnerNotFoundException.class);
        mockMvc
                .perform(get("/api/v1/owner/21"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_bad_request_when_id_is_not_a_number() throws Exception {
        mockMvc.perform(get("/api/v1/owner/this-is-not-a-number"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_list_of_owners() throws Exception {
        given(ownerService.findAllOwners()).willReturn(owners);
        mockMvc.perform(get("/api/v1/owners")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstname").value("Jan"))
                .andExpect(jsonPath("$[0].lastname").value("Kowalski"))
                .andExpect(jsonPath("$[0].adress.country").value("Poland"))
                .andExpect(jsonPath("$[1].id").value(1))
                .andExpect(jsonPath("$[1].firstname").value("Adam"))
                .andExpect(jsonPath("$[1].lastname").value("Nowak"))
                .andExpect(jsonPath("$[1].adress.country").value("Poland"));
    }

    @Test
    public void should_create_owner() throws Exception {
        Owner owner = owners.get(0);
        owner.setId(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String ownerAsJson = objectMapper.writeValueAsString(owner);
        mockMvc.perform(post("/api/v1/owner")
                .content(ownerAsJson)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_update_owner() throws Exception {
        Owner owner = owners.get(0);
        owner.setFirstname("Witamwitam");
        ObjectMapper objectMapper = new ObjectMapper();
        String ownerAsJson = objectMapper.writeValueAsString(owner);

        mockMvc.perform(put("/api/v1/owner")
                .content(ownerAsJson)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void should_delete_owner() throws Exception {

    }
}
package pl.sda.spring.petclinic.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.spring.petclinic.model.Adress;
import pl.sda.spring.petclinic.model.Owner;
import pl.sda.spring.petclinic.service.OwnerService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
        MockitoAnnotations.initMocks(this);

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
}
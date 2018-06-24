package pl.sda.spring.petclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner {


    private Long id;
    private String firstname;
    private String lastname;
    private Adress adress;
    private Set<Pet> pets = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
    //cascade - zapis ownera powoduje zapis zwierzat, usuniecie powoduje usuniecie itp
    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}

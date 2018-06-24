package pl.sda.spring.petclinic.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pet {


    private Long id;
    private String name;
    private Date birthDate;
    private PetType pettype;
    private Owner owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getPettype() {
        return pettype;
    }

    public void setPettype(PetType pettype) {
        this.pettype = pettype;
    }

    @ManyToOne //many pets to one owner
    @JoinColumn(name = "owner_id")
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

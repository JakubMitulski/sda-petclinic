package pl.sda.spring.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Adress {

    private String city;
    private String postalcode;
    private String street;
    private String country;
}

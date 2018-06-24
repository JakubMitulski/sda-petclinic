package pl.sda.spring.petclinic.service;

import pl.sda.spring.petclinic.model.Owner;

import java.util.Collection;

public interface OwnerService {

    Owner findOwnerById(long id);

    Collection<Owner> findAllOwners();

    void saveOwner(Owner owner);

    Collection<Owner> findOwnerByLastname(String lastname);
}

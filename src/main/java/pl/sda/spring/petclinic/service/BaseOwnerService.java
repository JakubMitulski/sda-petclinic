package pl.sda.spring.petclinic.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.petclinic.model.Owner;

import java.util.Collection;

@Service
public class BaseOwnerService implements OwnerService{

    @Override
    public Owner findOwnerById(long id) {
        return null;
    }

    @Override
    public Collection<Owner> findAllOwners() {
        return null;
    }

    @Override
    public void saveOwner(Owner owner) {

    }

    @Override
    public Collection<Owner> findOwnerByLastname(String lastname) {
        return null;
    }
}

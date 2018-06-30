package pl.sda.spring.petclinic.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.petclinic.exception.OwnerNotFoundException;
import pl.sda.spring.petclinic.model.Owner;
import pl.sda.spring.petclinic.repository.OwnerRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class BaseOwnerService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public BaseOwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findOwnerById(long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        //optionalOwner może nie być dostępny (nie znajdzie obiektu dla danego id),
        //i wtedy zostanie wywolany wyjatek. Dzieki optional nie ma nullpiontera,
        //ponieważ optional nie zwróci nulla, tylko pusty zbiór.
        Owner owner = optionalOwner.orElseThrow(OwnerNotFoundException::new);
        return owner;

        //Krótszy zapis:
        //return ownerRepository.findById(id).orElseThrow(OwnerNotFoundException::new);
    }

    @Override
    public Collection<Owner> findAllOwners() {
        return null;
    }

    @Override
    public void saveOwner(Owner owner) {
        this.ownerRepository.save(owner);
    }

    @Override
    public Collection<Owner> findOwnerByLastname(String lastname) {
        return null;
    }

    @Override
    public void updateOwner(Owner owner) {

        if (owner.getId() == null || !this.ownerRepository.existsById(owner.getId())){
            throw new OwnerNotFoundException();
        }
        this.ownerRepository.save(owner);
    }
}

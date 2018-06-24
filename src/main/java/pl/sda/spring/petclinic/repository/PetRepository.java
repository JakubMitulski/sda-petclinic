package pl.sda.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.spring.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {

    Pet findByName(String name); //Spring sam uworzy implementację tej metody

}

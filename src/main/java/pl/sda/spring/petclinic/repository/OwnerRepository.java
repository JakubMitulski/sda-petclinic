package pl.sda.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.spring.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    //rozszerzenie o crudrepo daje nam dodatkowe metody do operacji na encji

}

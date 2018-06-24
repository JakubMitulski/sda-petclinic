package pl.sda.spring.petclinic.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.spring.petclinic.model.Owner;
import pl.sda.spring.petclinic.service.OwnerService;

@RestController
@RequestMapping("/api/v1")
public class OwnerController{

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(path = "/owner/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id){
        Owner ownerById = ownerService.findOwnerById(id);
        return ResponseEntity.ok(ownerById);
    }

    @GetMapping(path = "/owner/{lastname}")
    public ResponseEntity<Owner> getOwnerByLastName(@PathVariable String lastname){
        return null;
    }
}

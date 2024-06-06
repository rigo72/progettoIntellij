package com.jaewa.person.controller;


import com.jaewa.person.model.Person;
import com.jaewa.person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public final PersonService personService;



    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPerson();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.getPersonById(id);
        if(person.isPresent()) {
            return ResponseEntity.ok(person.get());
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.ok(createdPerson);
    }


    @PutMapping("/id")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personaDetails) {
        Optional<Person> existingPerson = personService.getPersonById(id);
        if(existingPerson.isPresent()) {
            Person personToUpdate = existingPerson.get();
            personToUpdate.setFirstName(personaDetails.getFirstName());
            personToUpdate.setLastName(personaDetails.getLastName());
            personToUpdate.setEmail(personaDetails.getEmail());
            Person updatedPerson = personService.createPerson(personToUpdate);
            return ResponseEntity.ok(updatedPerson);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}

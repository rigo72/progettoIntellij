package com.jaewa.person.controller;


import com.jaewa.person.controller.dto.PersonDto;
import com.jaewa.person.controller.mapper.PersonMapper;
import com.jaewa.person.model.Person;
import com.jaewa.person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
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
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto dto) {
        Person entity = personMapper.toEntity(dto);
        Optional<Person> optionalPerson = personService.updatePerson(id, entity);
        if(optionalPerson.isPresent()) {
            return ResponseEntity.ok(personMapper.toDto(optionalPerson.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}

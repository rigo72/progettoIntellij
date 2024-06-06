package com.jaewa.person.service;


import com.jaewa.person.model.Person;
import com.jaewa.person.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person createPerson(Person person){
        return personRepository.save(person);
    }

    public void deletePerson(Long  id){
        personRepository.deleteById(id);
    }

    public Optional<Person> getPersonById(Long id){
        return personRepository.findById(id);
    }

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

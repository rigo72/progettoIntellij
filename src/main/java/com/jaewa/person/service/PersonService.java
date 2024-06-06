package com.jaewa.person.service;


import com.jaewa.person.model.Person;
import com.jaewa.person.repository.PersonRepository;
import org.springframework.stereotype.Service;
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





}
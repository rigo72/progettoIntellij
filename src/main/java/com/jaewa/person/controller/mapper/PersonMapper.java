package com.jaewa.person.controller.mapper;

import com.jaewa.person.controller.dto.PersonDto;
import com.jaewa.person.model.Person;
import com.jaewa.person.repository.PersonRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonRepository.class})
public interface PersonMapper {


    Person toEntity(PersonDto dto);

    @Mapping(target = "password", ignore = true)
    PersonDto toDto(Person entity);

    List<PersonDto> toDtos(List<Person> entities);
}

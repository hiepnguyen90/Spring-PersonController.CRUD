package io.zipcoder.crudapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
    public Person findById(int id) {
        return personRepository.findOne(id);
    }
    
    public void deleteById(int id) {
        personRepository.delete(id);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }
}

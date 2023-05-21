package io.zipcoder.crudapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
        
    }

    @PostMapping("/people")
    public ResponseEntity<Person> creatPerson(@RequestBody Person p) {
        Person person = personService.save(p);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getPersonList() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") int id){
        personService.findById(id);
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person p){
        Person existingPerson = personService.findById(id);

        p.setId(id);
        Person savedPerson = personService.save(p);

        if(existingPerson == null){
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(savedPerson);
        }

    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> DeletePerson(@PathVariable int id){
    personService.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}
}

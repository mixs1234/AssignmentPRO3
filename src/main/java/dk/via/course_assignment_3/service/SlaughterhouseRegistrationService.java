package dk.via.course_assignment_3.service;

import dk.via.course_assignment_3.business.SlaughterhouseRegistrationSystem;
import dk.via.shared.business.persistence.NotFoundException;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.business.persistence.ValidationException;
import dk.via.shared.model.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class SlaughterhouseRegistrationService {

    private final SlaughterhouseRegistrationSystem system;

    public SlaughterhouseRegistrationService(SlaughterhouseRegistrationSystem system) {
        this.system = system;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleNotFoundException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @PostMapping
    public Animal registerAnimal(@RequestBody Animal animal) throws ValidationException, PersistenceException {
        return system.registerAnimal(animal.getWeight(), animal.getAnimalType(), animal.getArrivalDate(), animal.getOrigin());
    }

    @GetMapping("/{regNumber}")
    public Animal readAnimal(@PathVariable String regNumber) throws NotFoundException, PersistenceException {
        return system.readAnimal(regNumber);
    }

    @GetMapping("/arrival/{arrivalDate}")
    public List<Animal> readAllAnimalsArrivingOn(@PathVariable String arrivalDate) throws NotFoundException, PersistenceException {
        return system.readAllAnimalsArrivingOn(arrivalDate);
    }

    @GetMapping("/origin/{origin}")
    public List<Animal> readAllAnimalsFromOrigin(@PathVariable String origin) throws NotFoundException, PersistenceException {
        return system.readAllAnimalsFromOrigin(origin);
    }
}

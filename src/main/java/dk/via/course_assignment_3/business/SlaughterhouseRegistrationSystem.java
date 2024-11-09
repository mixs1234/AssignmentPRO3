package dk.via.course_assignment_3.business;

import dk.via.shared.business.persistence.NotFoundException;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.business.persistence.ValidationException;
import dk.via.shared.model.Animal;

import java.util.List;

public interface SlaughterhouseRegistrationSystem {
    Animal registerAnimal(double weight, String animalType, String arrivalDate, String origin) throws ValidationException, PersistenceException;
    Animal readAnimal(String regNumber) throws NotFoundException, PersistenceException;
    List<Animal> readAllAnimalsArrivingOn(String arrivalDate) throws NotFoundException, PersistenceException;
    List<Animal> readAllAnimalsFromOrigin(String origin) throws NotFoundException, PersistenceException;
}

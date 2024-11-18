package dk.via.course_assignment_3.business;

import dk.via.shared.business.persistence.AnimalPersistence;
import dk.via.shared.business.persistence.NotFoundException;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.business.persistence.ValidationException;
import dk.via.shared.model.Animal;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SlaughterhouseRegistrationSystemImpl implements SlaughterhouseRegistrationSystem {

    private final Map<String, Animal> animalCache = new HashMap<>();
    private final AnimalPersistence persistence;

    public SlaughterhouseRegistrationSystemImpl(AnimalPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public Animal registerAnimal(double weight, String animalType, String arrivalDate, String origin) throws ValidationException, PersistenceException {
        if (weight <= 0) throw new ValidationException();

        Animal animal = persistence.create(weight, animalType, arrivalDate, origin);
        animalCache.put(animal.getRegNumber(), animal);
        return animal;
    }

    @Override
    public Animal readAnimal(String regNumber) throws NotFoundException, PersistenceException {
        if (regNumber == null || regNumber.isEmpty()) throw new NotFoundException();
        if (!animalCache.containsKey(regNumber)) {
            Animal animal = persistence.read(regNumber);
            animalCache.put(regNumber, animal);
        }
        return animalCache.get(regNumber);
    }

    @Override
    public List<Animal> readAllAnimalsArrivingOn(String arrivalDate) throws NotFoundException, PersistenceException {
        if (arrivalDate == null || arrivalDate.isEmpty()) throw new NotFoundException();
        readAllAnimals();
        return animalCache.values().stream().filter(animal -> animal.getArrivalDate().equals(arrivalDate)).toList();
    }

    @Override
    public List<Animal> readAllAnimalsFromOrigin(String origin) throws NotFoundException, PersistenceException {
        if (origin == null || origin.isEmpty()) throw new NotFoundException();
        readAllAnimals();
        return animalCache.values().stream().filter(animal -> animal.getOrigin().equals(origin)).toList();
    }

    private void readAllAnimals() {
        try {
            List<Animal> animals = (List<Animal>) persistence.readAll();
            for (Animal animal : animals) {
                animalCache.put(animal.getRegNumber(), animal);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}

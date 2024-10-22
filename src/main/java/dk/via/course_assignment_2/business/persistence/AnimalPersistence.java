package dk.via.course_assignment_2.business.persistence;

import dk.via.course_assignment_2.model.Animal;

import java.util.Collection;

public interface AnimalPersistence {
    Animal create(double weight, String animalType) throws PersistenceException;
    Collection<Animal> readAll() throws PersistenceException;
    void update(Animal animal) throws PersistenceException;
    void delete(Animal animal) throws PersistenceException;
    Animal read(String regNumber) throws PersistenceException;
}

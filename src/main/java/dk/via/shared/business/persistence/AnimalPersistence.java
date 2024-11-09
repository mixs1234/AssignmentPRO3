package dk.via.shared.business.persistence;

import dk.via.shared.model.Animal;

import java.util.Collection;

public interface AnimalPersistence {
    Animal create(double weight, String animalType, String arrivalDate, String origin) throws PersistenceException;
    Collection<Animal> readAll() throws PersistenceException;
    void update(Animal animal) throws PersistenceException;
    void delete(Animal animal) throws PersistenceException;
    Animal read(String regNumber) throws PersistenceException;
}

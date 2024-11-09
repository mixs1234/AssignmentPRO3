package dk.via.shared.business.persistence;

import dk.via.shared.model.Part;

import java.util.Collection;

public interface PartPersistence {
    Part create(String animalRegNumber, String partType, double weight) throws PersistenceException;
    Collection<Part> readAll() throws PersistenceException;
    Collection<Part> readAllWithAnimalRegNumber(String animalRegNumber) throws PersistenceException;
    void update(Part part) throws PersistenceException;
    void delete(Part part) throws PersistenceException;
    Part read(String partID) throws PersistenceException;
}

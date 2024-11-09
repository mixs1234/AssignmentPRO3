package dk.via.course_assignment_2.business;

import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.model.Animal;
import dk.via.shared.model.Product;

import java.util.Collection;

public interface SlaughterhouseSystem {
    Collection<Animal> retrieveRegNumberFromAnimalsInProduct(String productID) throws PersistenceException;
    Collection<Product> retrieveAllProductsFromAnimal(String regNumber) throws PersistenceException;
}

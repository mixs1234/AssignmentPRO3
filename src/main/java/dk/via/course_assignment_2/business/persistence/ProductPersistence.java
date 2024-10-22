package dk.via.course_assignment_2.business.persistence;

import dk.via.course_assignment_2.model.Part;
import dk.via.course_assignment_2.model.Product;

import java.util.Collection;
import java.util.List;


public interface ProductPersistence {
    Product create(String productType) throws PersistenceException;
    Collection<Product> readAll() throws PersistenceException;
    Collection<Product> readAllFromPart(String partID) throws PersistenceException;
    void update(Product product) throws PersistenceException;
    void delete(Product product) throws PersistenceException;
    Product read(String productID) throws PersistenceException;
}

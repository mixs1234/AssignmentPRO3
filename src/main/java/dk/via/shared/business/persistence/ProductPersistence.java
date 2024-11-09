package dk.via.shared.business.persistence;

import dk.via.shared.model.Product;

import java.util.Collection;


public interface ProductPersistence {
    Product create(String productType) throws PersistenceException;
    Collection<Product> readAll() throws PersistenceException;
    Collection<Product> readAllFromPart(String partID) throws PersistenceException;
    void update(Product product) throws PersistenceException;
    void delete(Product product) throws PersistenceException;
    Product read(String productID) throws PersistenceException;
}

package dk.via.shared.business.persistence;

import dk.via.shared.model.ProductPart;

import java.util.Collection;

public interface ProductPartPersistence {
    void create(String partID, String productID) throws PersistenceException;
    void delete(String partID, String productID) throws PersistenceException;
    boolean exists(String partID, String productID) throws PersistenceException;
    ProductPart read(String partID, String productID) throws PersistenceException;
    Collection<ProductPart> readAllFromProductId(String productID) throws PersistenceException;
    Collection<ProductPart> readAllFromPartId(String partID) throws PersistenceException;
}

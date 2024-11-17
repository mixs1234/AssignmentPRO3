package dk.via.course_assignment_4.business;

import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.business.persistence.ValidationException;
import dk.via.shared.model.Part;
import dk.via.shared.model.Product;

import java.util.List;

public interface SlaughterhouseProcessingSystem {

    List<Part> cutAnimal(String animalRegNumber) throws ValidationException, PersistenceException;
    Part registerPart(String animalRegNumber, String partType, double weight) throws ValidationException, PersistenceException;
    List<Product> produceProduct(String productType, String... partIds) throws ValidationException, PersistenceException;
    void registerProduct(String productId, String partId) throws ValidationException, PersistenceException;
}

package dk.via.course_assignment_4.business;

import dk.via.shared.business.persistence.*;
import dk.via.shared.model.Animal;
import dk.via.shared.model.Part;
import dk.via.shared.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlaughterhouseProcessingSystemImpl implements SlaughterhouseProcessingSystem {

    private final ProductPersistence productPersistence;
    private final PartPersistence partPersistence;
    private final ProductPartPersistence productPartPersistence;
    private final AnimalPersistence animalPersistence;

    public SlaughterhouseProcessingSystemImpl(ProductPersistence productPersistence, PartPersistence partPersistence,
                                              ProductPartPersistence productPartPersistence,
                                              AnimalPersistence animalPersistence) {
        this.productPersistence = productPersistence;
        this.partPersistence = partPersistence;
        this.productPartPersistence = productPartPersistence;
        this.animalPersistence = animalPersistence;
    }

    @Override
    public List<Part> cutAnimal(String animalRegNumber) throws ValidationException, PersistenceException {
        if (animalRegNumber == null || animalRegNumber.isEmpty()) {
            throw new ValidationException();
        }

        List<Part> parts = new ArrayList<>();

        Animal animalToBeCut = animalPersistence.read(animalRegNumber);
        double animalWeight = animalToBeCut.getWeight();
        while (animalWeight > 0) {
            double partWeight = Math.min(animalWeight, 10);
            Part part = new Part(null, animalRegNumber, "Meat", partWeight);
            parts.add(part);
            animalWeight -= partWeight;
        }
        for (Part part : parts) {
            registerPart(part.getAnimalRegNumber(), part.getPartType(), part.getWeight());
        }

        return parts;
    }

    @Override
    public Part registerPart(String animalRegNumber, String partType, double weight) throws ValidationException, PersistenceException {
        if (animalRegNumber == null || animalRegNumber.isEmpty() || partType == null || partType.isEmpty() || weight <= 0) {
            throw new ValidationException();
        }

        Part part = new Part(null, animalRegNumber, partType, weight);
        partPersistence.create(part.getAnimalRegNumber(), part.getPartType(), part.getWeight());
        return part;
    }

    @Override
    public List<Product> produceProduct(String productType, String... partIds) throws ValidationException, PersistenceException {
        for (String partId : partIds) {
            if (partId == null || partId.isEmpty()) {
                throw new ValidationException();
            }
        }

        List<Product> products = new ArrayList<>();
        Product product = productPersistence.create(productType);
        for (String partId : partIds) {
            Part part = partPersistence.read(partId);
            registerProduct(product.getProductID(), part.getPartID());
            products.add(product);
        }

        return products;
    }

    @Override
    public void registerProduct(String productId, String partId) throws ValidationException, PersistenceException {
        if (productId == null || productId.isEmpty() || partId == null || partId.isEmpty()) {
            throw new ValidationException();
        }

        productPartPersistence.create(productId, partId);
    }
}


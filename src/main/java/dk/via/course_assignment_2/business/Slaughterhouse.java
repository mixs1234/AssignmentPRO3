package dk.via.course_assignment_2.business;

import dk.via.course_assignment_2.business.persistence.*;
import dk.via.course_assignment_2.model.Animal;
import dk.via.course_assignment_2.model.Part;
import dk.via.course_assignment_2.model.Product;
import dk.via.course_assignment_2.model.ProductPart;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Slaughterhouse implements SlaughterhouseSystem {

    private final Map<String, Product> productCache = new HashMap<>();
    private final Map<String, Part> partCache = new HashMap<>();
    private final Map<String, Animal> animalCache = new HashMap<>();

    private final ProductPersistence productPersistence;
    private final PartPersistence partPersistence;
    private final ProductPartPersistence productPartPersistence;
    private final AnimalPersistence animalPersistence;

    public Slaughterhouse(ProductPersistence productPersistence, PartPersistence partPersistence,
                          ProductPartPersistence productPartPersistence, AnimalPersistence animalPersistence) {
        this.productPersistence = productPersistence;
        this.partPersistence = partPersistence;
        this.productPartPersistence = productPartPersistence;
        this.animalPersistence = animalPersistence;
    }

    @Override
    public List<Animal> retrieveRegNumberFromAnimalsInProduct(String productID)
            throws PersistenceException {
        if (productID == null || productID.isEmpty()) {
            throw new ValidationException();
        }
        List<Animal> animals = new ArrayList<>();

        for(ProductPart productPart : productPartPersistence.readAllFromProductId(productID)) {
            if (!partCache.containsKey(productPart.getPartID())) {
                partCache.put(productPart.getPartID(), partPersistence.read(productPart.getPartID()));
            }
            for(Part part : partCache.values()) {
                if(part.getPartID().equals(productPart.getPartID())) {
                    if (!animalCache.containsKey(part.getAnimalRegNumber())) {
                        animalCache.put(part.getAnimalRegNumber(), animalPersistence.read(part.getAnimalRegNumber()));
                    }
                    if(!animals.contains(animalCache.get(part.getAnimalRegNumber()))) {
                        animals.add(animalCache.get(part.getAnimalRegNumber()));
                    }
                }
            }
        }

        return animals;
    }


    @Override
    public List<Product> retrieveAllProductsFromAnimal(String regNumber)
            throws PersistenceException {

        if (regNumber == null || regNumber.isEmpty()) {
            throw new ValidationException();
        }

        List<Product> products = new ArrayList<>();

        for(Part part : partPersistence.readAllWithAnimalRegNumber(regNumber)) {
            for(ProductPart productPart : productPartPersistence.readAllFromPartId(part.getPartID())) {
                if (!productCache.containsKey(productPart.getProductID())) {
                    productCache.put(productPart.getProductID(), productPersistence.read(productPart.getProductID()));
                }
                if(!products.contains(productCache.get(productPart.getProductID()))) {
                    products.add(productCache.get(productPart.getProductID()));
                }
            }
        }

        if (products.isEmpty()) {
            throw new NotFoundException();
        }
        return products;

    }
}

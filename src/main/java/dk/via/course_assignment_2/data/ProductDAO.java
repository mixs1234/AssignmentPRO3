package dk.via.course_assignment_2.data;

import dk.via.course_assignment_2.business.persistence.NotFoundException;
import dk.via.course_assignment_2.business.persistence.NotImplementedException;
import dk.via.course_assignment_2.business.persistence.PersistenceException;
import dk.via.course_assignment_2.business.persistence.ProductPersistence;
import dk.via.course_assignment_2.model.Product;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class ProductDAO implements ProductPersistence {

    private DatabaseHelper<Product> helper;

    public ProductDAO(DatabaseHelper<Product> helper) {
        this.helper = helper;
    }

    @Override
    public Product create(String productType) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Product> readAll() throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Product> readAllFromPart(String partID) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public void update(Product product) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Product product) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public Product read(String productID) throws PersistenceException {
        try {
            Product product = helper.mapSingle(this::createProduct, "SELECT * FROM product WHERE product_id = CAST(? AS UUID)", productID);
            if(product == null) throw new NotFoundException();
            return product;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        String productID = resultSet.getString("product_id");
        String productType = resultSet.getString("product_type");
        return new Product(productID, productType);
    }

}

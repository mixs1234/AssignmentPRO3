package dk.via.shared.data;

import dk.via.shared.business.persistence.NotFoundException;
import dk.via.shared.business.persistence.NotImplementedException;
import dk.via.shared.business.persistence.PersistenceException;
import dk.via.shared.business.persistence.ProductPartPersistence;
import dk.via.shared.model.ProductPart;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Component
public class ProductPartDAO implements ProductPartPersistence {

    private DatabaseHelper<ProductPart> helper;

    public ProductPartDAO(DatabaseHelper<ProductPart> helper) {
        this.helper = helper;
    }

    @Override
    public List<ProductPart> create(String partID, String productID) throws PersistenceException {
        try {
            return helper.map(this::createProductPart,
                    "INSERT INTO product_part (part_id, product_id) VALUES (CAST(? AS UUID), CAST(? AS UUID))", partID, productID);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(String partID, String productID) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public boolean exists(String partID, String productID) throws PersistenceException {
        throw new NotImplementedException();
    }

    @Override
    public ProductPart read(String partID, String productID) throws PersistenceException {
        try {
            ProductPart productPart = helper.mapSingle(this::createProductPart,
                    "SELECT * FROM product_part WHERE part_id = CAST(? AS UUID) AND product_id = CAST(? AS UUID)", partID, productID);
            if(productPart == null) throw new NotFoundException();
            return productPart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<ProductPart> readAllFromProductId(String productID) throws PersistenceException {
        try {
            return helper.map(this::createProductPart, "SELECT * FROM product_part WHERE product_id = CAST(? AS UUID)", productID);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Collection<ProductPart> readAllFromPartId(String partID) throws PersistenceException {
        try {
            return helper.map(this::createProductPart, "SELECT * FROM product_part WHERE part_id = CAST(? AS UUID)", partID);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private ProductPart createProductPart(ResultSet resultSet) throws SQLException {
        String partID = resultSet.getString("part_id");
        String productID = resultSet.getString("product_id");
        return new ProductPart(partID, productID);
    }
}

package dk.via.course_assignment_2.model;

import java.util.List;

public class Product {

    private String productID;
    private String productType;

    public Product(String productID, String productType) {
        this.productID = productID;
        this.productType = productType;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


}

package dk.via.shared.model;

public class ProductPart {

    private String partID;
    private String productID;

    public ProductPart(String partID, String productID) {
        this.partID = partID;
        this.productID = productID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

}

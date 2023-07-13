import java.util.Date;

/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 商品类
 * @date 2023/7/12 11:15:38
 */
public class Product {
    //属性
    String productCode;
    String productName;
    String manufacturer;
    Date manufacturingDate = new Date();  // 使用 java.util.Date
    String model;
    double purchasePrice; //进价
    double retailPrice; //售价
    int quantity;

    //构造函数
    public Product(String productCode, String productName, String manufacturer, Date manufacturingDate, String model, double purchasePrice, double retailPrice, int quantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.manufacturingDate = manufacturingDate;
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.quantity = quantity;
    }

    //toString方法重载，输出可读性更强
    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", model='" + model + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", retailPrice=" + retailPrice +
                ", quantity=" + quantity +
                '}';
    }

    //Getter和Setter
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

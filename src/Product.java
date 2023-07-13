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
}

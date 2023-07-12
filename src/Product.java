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
    String productID;
    String productName;
    String producer;
    Date producedTime;
    int productType;
    float purchasePrice; //进价
    float salePrice; //售价
    int quantity;
}

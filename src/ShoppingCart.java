import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 购物车类
 * @date 2023/7/13 10:47:37
 */
public class ShoppingCart {
    private String customerID; //唯一的客户ID
    private List<Product> productList; //购物清单
    private double totalAmount; //总金额
    private LocalDateTime creationTime; //创建时间

    // 构造方法
    public ShoppingCart() {}
    public ShoppingCart(String customerID) {
        this.customerID = customerID;
        this.productList = new ArrayList<>();
        this.totalAmount = 0.0;
        this.creationTime = LocalDateTime.now();
    }

    // 添加商品到购物车
    public void addProduct(Product product) {
        productList.add(product);
        totalAmount += product.getRetailPrice();
    }

    // 从购物车中移除商品
    public void removeProduct(Product product) {
        productList.remove(product);
        totalAmount -= product.getRetailPrice();
    }

    // 获取购物车中的商品列表
    public List<Product> getProductList() {
        return productList;
    }

    // 获取购物车总金额
    public double getTotalAmount() {
        return totalAmount;
    }

    // 获取购物车创建时间
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}

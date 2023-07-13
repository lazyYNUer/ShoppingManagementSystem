/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 商品管理接口，可以被管理员和客户实现
 * @date 2023/7/12 11:24:19
 */
public interface ProductManage {
    public void showProduct();
    public void addProduct();
    public void deleteProduct();
    public void searchProduct();
    public void updateProduct();
}

import java.util.ArrayList;
import java.util.Date;

/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 客户类
 * @date 2023/7/12 11:11:43
 */
public class Customer implements ProductManage{
    //属性
    String account;
    String password;
    String customerID;
    String customerName;
    String customerLevel;
    Date registrationTime = new Date();
    float totalAmountSpent;
    String phoneNumber;
    String email;
    ArrayList<Product> shoppingCart = new ArrayList<>();

    //方法
    //登录和退出
    public void login() {}
    public void logout() {}

    //密码管理
    public void alterPassword() {}
    public void resetPassword() {}

    //购物车管理
    public void addProduct() {}
    public void deleteProduct() {}
    public void alterProduct() {}
    public void showProduct() {}
    public void searchProduct() {}
    public void pay() {}
    public void showHistory() {}
}

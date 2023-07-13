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
    String customerID;
    String customerName;
    String password;
    String customerLevel;
    Date registrationTime = new Date();
    double totalAmountSpent;
    String phoneNumber;
    String email;
    ShoppingCart shoppingCart = new ShoppingCart(); //购物车

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
    public void updateProduct() {}
    public void showProduct() {}
    public void searchProduct() {}
    public void pay() {}
    public void showHistory() {}

    //方法重载
    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                ", customerLevel=" + customerLevel +
                ", registrationTime='" + registrationTime + '\'' +
                ", totalAmountSpent=" + totalAmountSpent +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                '}';
    }

    //构造方法
    public Customer() {}
    public Customer(String customerID, String customerName, String password, String customerLevel, Date registrationTime, double totalAmountSpent, String phoneNumber, String email) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.password = password;
        this.customerLevel = customerLevel;
        this.registrationTime = registrationTime;
        this.totalAmountSpent = totalAmountSpent;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //Setter和Getter方法
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public double getTotalAmountSpent() {
        return totalAmountSpent;
    }

    public void setTotalAmountSpent(double totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}

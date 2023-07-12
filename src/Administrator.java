/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 管理员类
 * @date 2023/7/12 11:09:52
 */
public class Administrator implements ProductManage{

    //属性
    String account;
    String password;

    //方法
    //登录和退出
    public void login() {
        /**
         * 主函数中：
         * 要求输入账号和密码，与数据库中的信息比较
         * 账号与密码都匹配，成功登陆，进入管理员菜单进行操作
         * 若不匹配，显示提示信息，要求重新输入密码
         */
    }
    public void logout() {
        /**
         * 退出登录，退出管理员菜单，返回最开始的登录界面
         */
    }

    //密码管理
    public void changePassword() {
        /**
         * 输入旧密码，确认身份
         * 不匹配，重新输入
         * 匹配，输入新的密码两次，修改数据库里的信息
         */
    }
    public void resetCustomerPassword() {
        /**
         * 直接将某个用户的密码改为某一个默认值
         */
    }

    //客户管理
    public void showCustomer() {}
    public void addCustomer() {}
    public void deleteCustomer() {}
    public void alterCustomer() {}
    public void searchCustomer() {}

    //商品管理
    public void showProduct() {}
    public void addProduct() {}
    public void deleteProduct() {}
    public void alterProduct() {}
    public void searchProduct() {}

}
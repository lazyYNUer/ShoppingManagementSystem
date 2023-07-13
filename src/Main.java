/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 主类，实现菜单相关操作
 * @date 2023/7/12 11:28:00
 */
public class Main {
    //主函数
    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        mySQL.connect();
        Administrator administrator = new Administrator();
        administrator.addProduct();
        mySQL.showProduct();
        //mySQL.deleteProduct("1");
        mySQL.showProduct();
        mySQL.disconnect();
        mySQL.showProduct();
    }

    //登录菜单
    public void loginMenu() {

    }

    //管理员菜单
    public void administratorMenu() {

    }

    //客户菜单
    public void customerMenu() {

    }
}

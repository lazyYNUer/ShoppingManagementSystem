import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 管理员类
 * @date 2023/7/12 11:09:52
 */
public class Administrator implements ProductManage{

    //属性
    private  final String username = "admin";
    private  String password = "zcxzcx123";
    MySQL mySQL = new MySQL();

    //方法
    //Setter
    public void setPassword(String password) {
        this.password = password;
    }

    //登录和退出
    /**
     * 主函数中：
     * 要求输入账号和密码，与数据库中的信息比较
     * 账号与密码都匹配，成功登陆，进入管理员菜单进行操作
     * 若不匹配，显示提示信息，要求重新输入密码
     */
    public boolean login(MySQL mySQL) {
        boolean loggedIn = false;
        try (Connection connection = DriverManager.getConnection(mySQL.getUrl(), mySQL.getUsername(), mySQL.getPassword());) {
            Scanner scanner = new Scanner(System.in);
            //布尔值loggedIn表示是否登录成功
            while (true) {
                System.out.print("请输入管理员用户名：");
                String username = scanner.nextLine();
                System.out.print("请输入管理员密码：");
                String password = scanner.nextLine();

                String query = "SELECT * FROM admins WHERE adminName = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        System.out.println("管理员登录成功");
                        loggedIn = true;
                        return true;
                        // 进入管理员菜单
                    } else {
                        System.out.println("管理员用户名或密码错误，请重新输入");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("登录时发生数据库错误: " + e.getMessage());
            return loggedIn;
        }
    }
    public boolean logout() {
        /**
         * 退出登录，退出管理员菜单，返回最开始的登录界面
         */
        boolean loggedOut = true;
        System.out.println("管理员退出登录");
        return loggedOut;
    }

    //密码管理
    public void changePassword() {
        /**
         * 输入旧密码，确认身份
         * 不匹配，重新输入
         * 匹配，输入新的密码两次，修改数据库里的信息
         */
        Scanner scanner = new Scanner(System.in);
        //确认身份
        try (Connection connection = DriverManager.getConnection(mySQL.getUrl(), mySQL.getUsername(), mySQL.getPassword());) {
            while (true) {
                System.out.println("确认身份：");
                System.out.print("请输入管理员用户名：");
                String username = scanner.nextLine();
                System.out.print("请输入管理员密码：");
                String password = scanner.nextLine();

                String query = "SELECT * FROM admins WHERE adminName = ? AND password = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        System.out.println("身份确认成功");
                        // 进入管理员菜单
                    } else {
                        System.out.println("管理员用户名或密码错误，请重新输入");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("确认身份时发生数据库错误: " + e.getMessage());
        }

        //更新密码
        while (true) {
            System.out.print("请输入新密码：");
            String newPassword1 = scanner.nextLine();
            System.out.print("再次输入新密码：");
            String newPassword2 = scanner.nextLine();
            if (newPassword1 == newPassword2) {
                //更新该对象的密码
                password = newPassword1;
                //在数据库中更新密码
                mySQL.updateAdminPassword(username, newPassword1);
                System.out.println("密码更新成功！");
                break;
            } else System.out.println("两次输入的新密码不一致！请重新输入！");
        }


    }
    public void resetCustomerPassword() {
        /**
         * 直接将某个用户的密码改为某一个默认值
         */
        //输入重置的客户信息
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要修改的客户ID:");
        String customerID = scanner.nextLine();
        System.out.print("请输入重置后的密码:");
        String password = scanner.nextLine();
        mySQL.resetCustomerPassword(customerID, password);
    }

    //客户管理
    public void showCustomer() {
        mySQL.showCustomers();
    }
    public void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入添加客户的信息:");
        System.out.print("请输入客户ID:");
        String customerID = scanner.nextLine();
        System.out.print("请输入客户ID:");
        String customerName = scanner.nextLine();
        System.out.println("请输入客户名称:");
        String password = scanner.nextLine();
        System.out.println("请输入客户密码:");
        String customerLevel = scanner.nextLine();
        System.out.println("请输入客户:");
        Date registrationTime = java.sql.Date.valueOf(scanner.nextLine());
        System.out.println("请输入添加客户的信息:");
        double totalAmountSpent = scanner.nextDouble();
        System.out.println("请输入添加客户的信息:");
        String phoneNumber = scanner.nextLine();
        System.out.println("请输入添加客户的信息:");
        String email = scanner.nextLine();
    }
    public void deleteCustomer() {
        //提示信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的客户的ID:");
        String customerID = scanner.nextLine();
        //再次询问
        System.out.println("确定要删除客户吗？Y/N");
        String option = scanner.nextLine();
        if (option.equals("Y")) {
            mySQL.deleteCustomer(customerID);
            System.out.println("删除成功！");
        }
        else if (option.equals("N")) {
            System.out.println("取消删除");
        }
    }
    public void updateCustomer() {

    }
    public void searchCustomer() {}

    //商品管理
    public void showProduct() {
        mySQL.showProduct();
    }
    public void addProduct() {
        mySQL.addProduct("P123", "watermelon", "HuaQiang", new java.util.Date(), "model 3", 1.25, 2.5, 27);
    }
    public void deleteProduct() {}
    public void updateProduct() {
        Scanner scanner = new Scanner(System.in);
    }

    public void searchProduct() {}

}
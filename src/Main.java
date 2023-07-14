import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 主类，实现菜单相关操作
 * @date 2023/7/12 11:28:00
 */
public class Main {
    public static MySQL mySQL = new MySQL();
    //主函数
    public static void main(String[] args) {
        loginMenu();
    }

    //登录菜单
    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---- 登录菜单 ----");
            System.out.println("1. 客户登录");
            System.out.println("2. 管理员登录");
            System.out.println("3. 退出");
            System.out.print("请选择登录方式：");
            int option = 0;
            while (true) {
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("输入错误" + e.getMessage());
                }
            }

            switch (option) {
                case 1:
                    // 客户登录逻辑
                    Customer customer = new Customer();
                    break;
                case 2:
                    // 管理员登录逻辑
                    Administrator administrator = new Administrator();
                    administratorMenu(administrator, administrator.login(mySQL));
                    break;
                case 3:
                    System.out.println("退出登录菜单");
                    return;
                default:
                    System.out.println("无效选项，请重新输入");
                    break;
            }
        }
    }

    //管理员菜单

    //管理员菜单
    public static void administratorMenu(Administrator administrator, boolean loggedIn) {
        if (loggedIn == false) {
            System.out.println("登录失败！");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---- 管理员菜单 ----");
            System.out.println("1. 显示所有商品信息");
            System.out.println("2. 添加商品");
            System.out.println("3. 删除商品");
            System.out.println("4. 修改商品");
            System.out.println("5. 查找商品");
            System.out.println("6. 显示所有客户信息");
            System.out.println("7. 添加客户");
            System.out.println("8. 删除客户");
            System.out.println("9. 修改客户");
            System.out.println("10. 查找客户");
            System.out.println("11. 重置客户密码");
            System.out.println("12. 修改管理员密码");
            System.out.println("13. 返回登录界面");
            System.out.print("请选择操作：");

            int option = 0;
            while (true) {
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (Exception e) {
                    System.out.println("管理员登录输入错误" + e.getMessage());
                    break;
                }
            }

            // 根据选项执行相应的逻辑
            switch (option) {
                case 1 -> administrator.showProduct(); // 显示所有商品信息逻辑
                case 2 -> administrator.addProduct(); // 添加商品逻辑
                case 3 -> administrator.deleteProduct(); // 删除商品逻辑
                case 4 -> administrator.updateProduct(); // 修改商品信息逻辑
                case 5 -> administrator.searchProduct(); //查找商品信息逻辑
                case 6 -> administrator.showCustomer(); // 显示所有客户信息逻辑
                case 7 -> administrator.addCustomer(); // 添加客户逻辑
                case 8 -> administrator.deleteCustomer(); // 删除客户逻辑
                case 9 -> administrator.updateCustomer(); //修改客户信息逻辑
                case 10 -> administrator.searchCustomer(); //查找客户逻辑
                case 11 -> administrator.resetCustomerPassword(); // 修改客户密码逻辑
                case 12 -> administrator.changePassword(); //修改密码逻辑
                case 13 -> {
                    System.out.println("返回登录菜单");
                    return;
                }
                default -> System.out.println("无效选项，请重新输入");
            }
        }
    }

// 其他方法实现...
}

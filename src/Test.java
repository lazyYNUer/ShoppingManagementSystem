import java.sql.Date;
import java.time.LocalDate;

/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 测试类，用来测试各种方法
 * @date 2023/7/13 13:56:58
 */
public class Test {
    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        mySQL.connect();
        mySQL.showProduct();
        //mySQL.addProduct("RJ048", "ThinkBook", "Lenovo", Date.valueOf("2021-5-12"),"model 4", 4399.4, 5699, 2300);
        //mySQL.addProduct("RTX", "RTX4090", "NVIDIA", Date.valueOf("2022-12-3"), "GPU", 12798.3, 25799, 40);
        mySQL.searchProduct("RTX", null, 0);
        mySQL.searchProduct("ThinkBook", "Lenovo", 0);
        mySQL.showProduct();
        mySQL.updateAdminPassword("admin", "zcxzcx123");
        Customer customer = new Customer("3", "kendrick", "lamar", "SILVER", Date.valueOf("2022-5-20"), 350000.34, "911", "Compton");
        mySQL.addCustomer(customer);
        mySQL.showCustomers();
        mySQL.deleteCustomer("2");
        mySQL.searchCustomers(null, "zcx");
        mySQL.searchCustomers("3", null);
    }
}

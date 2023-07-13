import java.time.LocalDate;
import java.util.List;

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
        mySQL.updateProduct("1", "manufacturingDate", LocalDate.of(2012, 12, 3), "purchasePrice", 3.45);
        mySQL.showProduct();
        List<Product> productList = mySQL.searchProduct(null, "联想", 1000.0);
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

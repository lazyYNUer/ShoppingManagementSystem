/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 对MySQL数据库进行操作
 * @date 2023/7/13 08:50:34
 */
import java.sql.*;
import java.util.Date;

public class MySQL {
    private Connection connection; //连接
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/shoppingmanagementsystem";
    private final String username = "root";
    private final String password = "zcxzcx123";

    //方法
    //连接数据库
    public void connect() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("成功连接到数据库！");
        } catch (SQLException exception) {
            System.out.println("连接数据库失败：" + exception.getMessage());
        } catch (Exception exception) {
            System.out.println("连接数据库失败：" + exception.getMessage());
        }
    }
    //断开连接
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("已断开与数据库的连接！");
            }
        } catch (SQLException e) {
            System.out.println("断开与数据库的连接失败: " + e.getMessage());
        }
    }

    /**
     * 对每一个表格，都定义一个修改的方法，管理员在自己的方法中调用这些方法来实现
     */
    //展示商品信息
    public void showProduct() {
        String query = "SELECT * FROM products";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("-----------------------------");
            while (resultSet.next()) {
                //获取数据
                String productCode = resultSet.getString("productCode");
                String productName = resultSet.getString("productName");
                String manufacturer = resultSet.getString("manufacturer");
                Date manufacturingDate = resultSet.getDate("manufacturingDate");  // 使用 java.util.Date
                String model = resultSet.getString("model");
                double purchasePrice = resultSet.getDouble("purchasePrice"); //进价
                double retailPrice = resultSet.getDouble("retailPrice"); //售价
                int quantity = resultSet.getInt("quantity");

                //显示数据
                System.out.println("productCode="+productCode+",productName="+productName
                        +",manufacturer="+manufacturer+",manufacturingDate="+manufacturingDate+",model="+model+",purchasePrice="+purchasePrice+",retailPrice"+retailPrice
                        +",quantity"+quantity);
            }
        } catch (SQLException e) {
            System.out.println("查询商品信息失败: " + e.getMessage());
        }
    }

    //添加商品信息
    public void addProduct(String productCode, String productName, String manufacturer, Date manufacturingDate, String model, double purchasePrice, double retailPrice, int quantity) {
        //SQL语句
        String query = "INSERT INTO products (productCode, productName, manufacturer, manufacturingDate, model, purchasePrice, retailPrice, quantity)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productCode);
            statement.setString(2, productName);
            statement.setString(3, manufacturer);
            statement.setDate(4, new java.sql.Date(manufacturingDate.getTime()));
            statement.setString(5, model);
            statement.setDouble(6, purchasePrice);
            statement.setDouble(7, retailPrice);
            statement.setInt(8, quantity);

            int rowsAffected = statement.executeUpdate();
            System.out.println("成功插入 " + rowsAffected + " 行数据到表中！");
        } catch (SQLException e) {
            System.out.println("插入数据失败: " + e.getMessage());
        }
    }

    //根据商品编号删除商品
    public void deleteProduct(String productCode) {
        String query = "DELETE FROM products WHERE productCode = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productCode);
            int rowsAffected = statement.executeUpdate();
            System.out.println("成功删除 " + rowsAffected + " 行商品数据！");
        } catch (SQLException e) {
            System.out.println("删除商品失败: " + e.getMessage());
        }
    }
    public void updateProduct() {

    }
    public void searchProduct() {}
}

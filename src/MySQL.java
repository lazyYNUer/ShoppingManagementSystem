/**
 * @author zcxshuaibi
 * @version 1.0
 * @project ShoppingManagementSystem
 * @description 对MySQL数据库进行操作
 * @date 2023/7/13 08:50:34
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQL {
    private Connection connection; //连接
    private final String url = "jdbc:mysql://localhost:3306/shoppingmanagementsystem";
    private final String username = "root";
    private final String password = "zcxzcx123";

    //方法
    //连接数据库
    public void connect() {
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("成功连接到数据库！");
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
    //商品管理
    //从resultSet中提出出product
    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        String productCode = resultSet.getString("productCode");
        String productName = resultSet.getString("productName");
        String manufacturer = resultSet.getString("manufacturer");
        Date manufacturingDate = resultSet.getDate("manufacturingDate");
        String model = resultSet.getString("model");
        double purchasePrice = resultSet.getDouble("purchasePrice");
        double retailPrice = resultSet.getDouble("retailPrice");
        int quantity = resultSet.getInt("quantity");

        //返回根据resultSet中提取的信息组成的一个新的product对象
        return new Product(productCode, productName, manufacturer, manufacturingDate, model, purchasePrice, retailPrice, quantity);
    }

    //展示商品信息
    public void showProduct() {
        String query = "SELECT * FROM products";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("-----------------------------");
            while (resultSet.next()) {
                //获取数据
                //使用createProductFromResultSet()方法，降低代码重复率
                Product product = createProductFromResultSet(resultSet);
                //显示数据
                System.out.println(product);
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

    //修改商品信息
    public void updateProduct(String productCode, Object... properties) {
        //调用generateUpdateQuery获取需要用到的SQL语句
        String query = generateUpdateQuery(properties);

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (int i = 1; i < properties.length; i += 2) {
                statement.setObject(index++, properties[i]);
            }
            statement.setString(index, productCode);

            System.out.println(statement);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("商品信息已成功更新！");
            } else {
                System.out.println("找不到要更新的商品！");
            }
        } catch (SQLException e) {
            System.out.println("更新商品信息失败: " + e.getMessage());
        }
    }

    //根据需要更新商品信息，自动生成对应的SQL语句
    private String generateUpdateQuery(Object... properties) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE products SET ");

        for (int i = 0; i < properties.length; i += 2) {
            String propertyName = String.valueOf(properties[i]);
            Object propertyValue = properties[i + 1];
            queryBuilder.append(propertyName).append(" = ?, ");
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(" WHERE productCode = ?");
        return queryBuilder.toString();
    }

    //根据商品名、制造商、查找商品
    public List<Product> searchProduct(String productName, String manufacturer, double minRetailPrice) {
        List<Product> results = new ArrayList<>();
        //根据要查找的信息构造SQL语句
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM products WHERE 1 = 1");

        //记录有效的参数
        List<Object> params = new ArrayList<>();

        //对传递的参数进行判断
        if (productName != null) { //不需要使用productName，就传入null
            queryBuilder.append(" AND productName LIKE ?");
            params.add("%" + productName + "%");
        }

        if (manufacturer != null) { //不需要使用manufacturer，就传入null
            queryBuilder.append(" AND manufacturer LIKE ?");
            params.add("%" + manufacturer + "%");
        }

        if (minRetailPrice >= 0) { //不需要使用minRetailPrice，就传入0
            queryBuilder.append(" AND retailPrice >= ?");
            params.add(minRetailPrice);
        }

        //query转换成SQL字符串
        String query = queryBuilder.toString();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //使用createProductFromResultSet()方法，降低代码重复率
                Product product = createProductFromResultSet(resultSet);
                results.add(product);
            }
        } catch (SQLException e) {
            System.out.println("商品查询失败: " + e.getMessage());
        }
        //查询完毕，输出提示信息
        if (results.isEmpty()) {
            System.out.println("找不到符合条件的商品。");
        } else {
            System.out.println("搜索结果：");
            for (Product product : results) {
                System.out.println(product.toString());
            }
        }
        return results;
    }

    //更新管理员的密码
    public boolean updateAdminPassword(String adminName, String newPassword) {
        //预处理SQL语句
        String query = "UPDATE admins SET password = ? WHERE adminName = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPassword);
            statement.setString(2, adminName);

            int rowsAffected = statement.executeUpdate();
            System.out.println("更新管理员密码成功！");

            //返回布尔值，表示更新是否成功
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("更新管理员密码失败: " + e.getMessage());
            return false;
        }
    }

    //重置用户的密码，与更新管理员密码的方法基本一致
    public boolean resetCustomerPassword(String customerID, String newPassword) {
        String query = "UPDATE customers SET password = ? WHERE customerID = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newPassword);
            statement.setString(2, customerID);

            int rowsAffected = statement.executeUpdate();
            System.out.println("重置客户密码成功！");

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("重置客户密码失败: " + e.getMessage());
            return false;
        }
    }

    private Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        String customerID = resultSet.getString("customerID");
        String customerName = resultSet.getString("customerName");
        String password = resultSet.getString("password");
        String customerLevel = resultSet.getString("customerLevel");
        Date registrationTime = resultSet.getDate("registrationTime");
        double totalAmountSpent = resultSet.getDouble("totalAmountSpent");
        String phoneNumber = resultSet.getString("phoneNumber");
        String email = resultSet.getString("email");

        //返回根据resultSet中提取的信息组成的一个新的product对象
        return new Customer(customerID, customerName, password, customerLevel, registrationTime, totalAmountSpent, phoneNumber, email);
    }

    //列出所有客户信息
    public void showCustomers() {
        String query = "SELECT * FROM customers";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("-----------------------------");
            while (resultSet.next()) {
                Customer customer = createCustomerFromResultSet(resultSet);

                System.out.println(customer.toString());
            }
        } catch (SQLException e) {
            System.out.println("查询客户信息失败: " + e.getMessage());
        }
    }

    //添加客户
    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO customers (customerID, customerName, password, customerLevel, registrationTime, totalAmountSpent, phoneNumber, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customer.getCustomerID());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getCustomerLevel());
            statement.setDate(5, new java.sql.Date(customer.getRegistrationTime().getTime()));
            statement.setDouble(6, customer.getTotalAmountSpent());
            statement.setString(7, customer.getPhoneNumber());
            statement.setString(8, customer.getEmail());

            int rowsAffected = statement.executeUpdate();
            System.out.println("添加客户成功！");
            System.out.println(customer);
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("添加客户失败: " + e.getMessage());
            return false;
        }
    }

    //删除客户
    public boolean deleteCustomer(String customerID) {
        String query = "DELETE FROM customers WHERE customerID = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, customerID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("客户ID " + customerID + " 不存在。");
                return false;
            } else {
                System.out.println("删除客户成功！");
            }
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("删除客户失败: " + e.getMessage());
            return false;
        }
    }

    //查询客户
    public List<Customer> searchCustomers(String customerID, String customerName) {
        List<Customer> results = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM customers WHERE 1 = 1");

        List<Object> params = new ArrayList<>();

        if (customerID != null) {
            queryBuilder.append(" AND customerID = ?");
            params.add(customerID);
        }

        if (customerName != null) {
            queryBuilder.append(" AND customerName = ?");
            params.add(customerName);
        }

        String query = queryBuilder.toString();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            int index = 1;
            for (Object param : params) {
                statement.setObject(index++, param);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = createCustomerFromResultSet(resultSet);

                //添加客户信息
                results.add(customer);
                System.out.println("查询到的客户信息: ");
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println("查询客户信息失败: " + e.getMessage());
        }

        return results;
    }

}

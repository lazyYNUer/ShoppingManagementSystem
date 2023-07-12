
import java.sql.*;

@SuppressWarnings("all")   // 注解警告信息
public class jdbcTest {
    public static void main(String[] args) throws Exception {
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2 创建和数据库之间的连接
        String username = "root";
        String password = "zcxzcx123";
        String url = "jdbc:mysql://localhost:3306/shoppingmanagementsystem";
        Connection conn = DriverManager.getConnection(url,username,password);
        // 3.准备发送SQL
        String sql = "select * from products";
        PreparedStatement pstm = conn.prepareStatement(sql);
        // 4.执行SQL，接收结果集
        ResultSet rs = pstm.executeQuery();
        // 5 处理结果集
        while(rs.next()){
     /*
        rs.getXxx(列顺序从1开始) 或者 rs.getXxx("列名") 获取指定列的数据，Xxx为数据类型
        实战中多使用列名，可读性强
      */
            /*
            int personId2 = rs.getInt(1);
            String personName2 = rs.getString(2);
            int age2 = rs.getInt(3);
            String sex2 = rs.getString(4);
            String mobile2 = rs.getString(5);
            String address2 = rs.getString(6);
            System.out.println("personId="+personId2+",personName="+personName2
                    +",age="+age2+",sex="+sex2+",mobile="+mobile2+",address="+address2);
            */
            String productID = rs.getString("productID");
            String productName = rs.getString("productName");
            String producer = rs.getString("producer");
            Date producedTime = rs.getDate("producedTime");
            int productType = rs.getInt("productType");
            float purchasePrice = rs.getFloat("purchasePrice");
            float salePrice = rs.getFloat("salePrice");
            int quantity = rs.getInt("quantity");
            System.out.println("productID="+productID+",productName="+productName
                    +",producer="+producer+",producedTime="+producedTime+",productType="+productType+",purchasePrice="+purchasePrice+",salePrice"+salePrice
                    +",quantity"+quantity);
        }
        // 6.释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}

package cn.techtutorial.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	private static Connection connection = null;
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","minhthien","123456");
            System.out.print("connected");
        }
        return connection;
    }
//	public static Connection getConnection() throws SQLException {
//        String jdbcUrl = "jdbc:mysql://google/fruitstore?cloudSqlInstance=fruitstore-408707:us-central1:fruitstore-123456&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=root&password=";
//        System.out.print("connected");
//        return DriverManager.getConnection(jdbcUrl);
//    }
}

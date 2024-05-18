package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import cn.techtutorial.model.*;

public class OrderDao {
	
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Order order) {
	    boolean result = false;
	    try {
	        String insertOrderQuery = "INSERT INTO orders (UserId, Address, status, o_date, o_quantity, total_price) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement pst = this.con.prepareStatement(insertOrderQuery);
	        pst.setInt(1, order.getUserId());
	        pst.setString(2, order.getAddress());
	        pst.setString(3, order.getStatus());
	        pst.setString(4, order.getO_date());
	        pst.setInt(5, order.getO_quantity());
	        pst.setDouble(6, order.getTotal_price());
	        
	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            result = true;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return result;
	}
	public String getOrderStatus(int orderId) {
	    String status = null;
	    try {
	        String query = "SELECT status FROM orders WHERE orderId = ?";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setInt(1, orderId);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            status = rs.getString("status");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return status;
	}

	public List<Order> getAllOrders() {
	    List<Order> list = new ArrayList<>();
	    try {
	        query = "SELECT * FROM orders ORDER BY o_date DESC";
	        pst = this.con.prepareStatement(query);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            Order order = new Order();
	            order.setOrderId(rs.getInt("orderId"));
	            order.setUserId(rs.getInt("UserId"));
	            order.setAddress(rs.getString("Address"));
	            order.setStatus(rs.getString("status"));
	            order.setO_date(rs.getString("o_date"));
	            order.setO_quantity(rs.getInt("o_quantity"));
	            order.setTotal_price(rs.getDouble("total_price"));
	            list.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return list;
	}
	public List<Order> searchOrder(String searchTerm) {
	    List<Order> orders = new ArrayList<>();
	    String query = "SELECT * FROM orders WHERE LOWER(Address) LIKE LOWER(?)";

	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	        preparedStatement.setString(1, "%" + searchTerm + "%");

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Order order = new Order();
	                order.setOrderId(resultSet.getInt("orderId"));
	                order.setUserId(resultSet.getInt("UserId"));
	                order.setAddress(resultSet.getString("Address"));
	                order.setStatus(resultSet.getString("status"));
	                order.setO_date(resultSet.getString("o_date"));
	                order.setO_quantity(resultSet.getInt("o_quantity"));
	                order.setTotal_price(resultSet.getDouble("total_price"));
	                orders.add(order);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return orders;
	}



//	public boolean insertOrder(Order model) {
//        boolean result = false;
//        try {
//            query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
//            pst = this.con.prepareStatement(query);
//            pst.setInt(1, model.getId());
//            pst.setInt(2, model.getUid());
//            pst.setInt(3, model.getQunatity());
//            pst.setString(4, model.getDate());
//            pst.executeUpdate();
//            result = true;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }
	

//    public List<Order> userOrders(int id) {
//        List<Order> list = new ArrayList<>();
//        try {
//            query = "select * from orders where u_id=? order by orders.o_id desc";
//            pst = this.con.prepareStatement(query);
//            pst.setInt(1, id);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                Order order = new Order();
//                ProductDao productDao = new ProductDao(this.con);
//                int pId = rs.getInt("p_id");
//                Product product = productDao.getSingleProduct(pId);
//                order.setOrderId(rs.getInt("o_id"));
//                order.setId(pId);
//                order.setName(product.getName());
//                order.setCategory(product.getCategory());
//                order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
//                order.setQunatity(rs.getInt("o_quantity"));
//                order.setDate(rs.getString("o_date"));
//                list.add(order);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return list;
//    }
	public List<Order> getUserOrders(int id) {
	    List<Order> list = new ArrayList<>();
	    try {
	        query = "SELECT * FROM orders WHERE UserId=? ORDER BY o_date DESC";
	        pst = this.con.prepareStatement(query);
	        pst.setInt(1, id);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            Order order = new Order();
	            order.setOrderId(rs.getInt("orderId"));
	            order.setUserId(rs.getInt("UserId"));
	            order.setAddress(rs.getString("Address"));
	            order.setStatus(rs.getString("status"));
	            order.setO_date(rs.getString("o_date"));
	            order.setO_quantity(rs.getInt("o_quantity"));
	            order.setTotal_price(rs.getDouble("total_price"));
	            list.add(order);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	    }
	    return list;
	}


    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "delete from orders where orderId=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
    public int getLastOrderId() {
        int lastOrderId = -1;
        try {
            String query = "SELECT MAX(orderId) AS lastOrderId FROM orders";
            PreparedStatement pst = this.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lastOrderId = rs.getInt("lastOrderId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastOrderId;
    }

    public String getAddressByOrderId(int orderId) {
        String address = null;
        String query = "SELECT address FROM orders WHERE orderId = ?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                address = rs.getString("address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
    public boolean updateOrderStatus(int orderId, String status) {
        boolean result = false;
        try {
            String query = "UPDATE orders SET status = ? WHERE orderId = ?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, status);
            pst.setInt(2, orderId);
            
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}

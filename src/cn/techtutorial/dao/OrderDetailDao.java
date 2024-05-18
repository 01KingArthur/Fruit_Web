package cn.techtutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.OrderDetail;

public class OrderDetailDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;

    public OrderDetailDao(Connection con) {
        super();
        this.con = con;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) {
        boolean result = false;
        try {
            query = "INSERT INTO order_detail (order_id, product_id) VALUES (?, ?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, orderDetail.getOrderId());
            pst.setInt(2, orderDetail.getProductId());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    public boolean addOrderDetail(int orderId, int productId, int quantity) {
        boolean result = false;
        try {
            String query = "INSERT INTO orderdetail (orderId, productId, quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, orderId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            int rowsAffected = pst.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT od.OrderDetailId, od.orderId, od.productId, od.quantity, p.name, p.category, p.price FROM orderdetail od JOIN products p ON od.productId = p.id WHERE orderId=?";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(rs.getInt("orderDetailId"));
                orderDetail.setOrderId(rs.getInt("orderId"));
                orderDetail.setProductId(rs.getInt("productId"));
                orderDetail.setProductName(rs.getString("name"));
                orderDetail.setCategory(rs.getString("category"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getDouble("price")); // Thêm dòng này để đặt giá
                System.out.print(rs.getDouble("price"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

}

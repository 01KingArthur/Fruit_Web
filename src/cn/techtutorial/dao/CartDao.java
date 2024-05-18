package cn.techtutorial.dao;

import java.sql.*;
import java.util.*;
import cn.techtutorial.model.Cart;

public class CartDao {
    private Connection con;

    public CartDao(Connection con) {
        this.con = con;
    }

    // Lấy giỏ hàng của người dùng từ cơ sở dữ liệu
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartList = new ArrayList<>();
        String query = "SELECT * FROM cart WHERE user_id=?";
        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity")) ;
                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }
//    public Cart getCartByUserId(int userId) {
//        Cart cart = null;
//        try {
//            String query = "SELECT * FROM cart WHERE user_id=?";
//            PreparedStatement pst = this.con.prepareStatement(query);
//            pst.setInt(1, userId);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                cart = new Cart();
//                cart.setId(rs.getInt("id"));
//                cart.setUserId(rs.getInt("user_id"));
//                cart.setProductId(rs.getInt("product_id"));
//                cart.setQuantity(rs.getInt("quantity"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return cart;
//    }

    public Cart getCartByUserAndProductId(int userId, int productId) {
        Cart cart = null;
        try {
            String query = "SELECT * FROM cart WHERE user_id=? AND product_id=?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, productId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    public boolean updateQuantity(int userId, int productId, int quantity) {
        boolean result = false;
        try {
            String query = "UPDATE cart SET quantity=? WHERE user_id=? AND product_id=?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, quantity);
            pst.setInt(2, userId);
            pst.setInt(3, productId);
            int rowCount = pst.executeUpdate();
            result = rowCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Thêm sản phẩm vào giỏ hàng của người dùng
    public boolean addToCart(int userId, int productId, int quantity) {
        boolean result = false;
        String query = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";
        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeFromCart(int userId, int productId) {
        boolean result = false;
        String query = "DELETE FROM cart WHERE user_id=? AND product_id=?";
        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, productId);
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int getTotalQuantity(int userId) {
        int totalQuantity = 0;
        String query = "SELECT COUNT(DISTINCT product_id) AS total_quantity FROM cart WHERE user_id=?";
        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                totalQuantity = rs.getInt("total_quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalQuantity;
    }
    public boolean removeAll(int userId) {
        boolean result = false;
        String query = "DELETE FROM cart WHERE user_id=?";
        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            result = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}

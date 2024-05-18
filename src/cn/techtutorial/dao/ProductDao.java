//package cn.techtutorial.dao;
//
//import java.sql.*;
//import java.util.*;
//
//import cn.techtutorial.model.Cart;
//import cn.techtutorial.model.Product;
//
//public class ProductDao {
//	private Connection con;
//
//	private String query;
//    private PreparedStatement pst;
//    private ResultSet rs;
//    
//
//	public ProductDao(Connection con) {
//		super();
//		this.con = con;
//	}
//	
//	public void deleteProduct(int id){
//        try{
//            
//           String query= "delete from products where id=?";
//           PreparedStatement pt = this.con.prepareStatement(query);
//           pt.setInt(1, id);
//           pt.execute();
//            
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//	
//	public boolean editProductInfo(Product product){
//		boolean test = false;
//		String query = "update products set name=?, category=?, price=?, image=? where id=?";
//		try{
//            PreparedStatement pt = this.con.prepareStatement(query);
//            pt.setString(1, product.getName());  
//            pt.setString(2, product.getCategory());
//            pt.setDouble(3, product.getPrice());
//            pt.setString(4, product.getImage());
//            pt.setInt(5, product.getId());
//            
//            pt.executeUpdate();
//            test= true;
//        }catch(Exception ex){
//            ex.printStackTrace();;
//        }
//		return test;
//	}
//	public boolean addProduct(Product product){
//        boolean test = false;
//        String query =  "insert into products (name,category,price,image) values(?,?,?,?)";
//        try{
//            PreparedStatement pst = this.con.prepareStatement(query);
//            pst.setString(1, product.getName());
//            pst.setString(2, product.getCategory());
//            pst.setDouble(3, product.getPrice());
//            pst.setString(4, product.getImage());
//            pst.executeUpdate();
//            test= true;
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return test;
//    }
//	
//	public List<Product> searchProducts(String searchTerm) {
//	    List<Product> products = new ArrayList<>();
//	    String query = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?)";
//
//	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//	        preparedStatement.setString(1, "%" + searchTerm + "%");
//
//	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
//	            while (resultSet.next()) {
//	                int id = resultSet.getInt("id");
//	                String name = resultSet.getString("name");
//	                String category = resultSet.getString("category");
//	                double price = resultSet.getDouble("price");
//	                String image = resultSet.getString("image");
//
//	                Product product = new Product(id, name, category, price, image);
//	                products.add(product);
//	            }
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return products;
//	}
//
//	
//	public List<Product> getAllProducts() {
//        List<Product> book = new ArrayList<>();
//        try {
//
//            query = "select * from products";
//            pst = this.con.prepareStatement(query);
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//            	Product row = new Product();
//                row.setId(rs.getInt("id"));
//                row.setName(rs.getString("name"));
//                row.setCategory(rs.getString("category"));
//                row.setPrice(rs.getDouble("price"));
//                row.setImage(rs.getString("image"));
//
//                book.add(row);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return book;
//    }
//	
//	
//	 public Product getSingleProduct(int id) {
//		 Product row = null;
//	        try {
//	            query = "select * from products where id=? ";
//
//	            pst = this.con.prepareStatement(query);
//	            pst.setInt(1, id);
//	            ResultSet rs = pst.executeQuery();
//
//	            while (rs.next()) {
//	            	row = new Product();
//	                row.setId(rs.getInt("id"));
//	                row.setName(rs.getString("name"));
//	                row.setCategory(rs.getString("category"));
//	                row.setPrice(rs.getDouble("price"));
//	                row.setImage(rs.getString("image"));
//	            }
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            System.out.println(e.getMessage());
//	        }
//
//	        return row;
//	    }
//	
//	public double getTotalCartPrice(ArrayList<Cart> cartList) {
//        double sum = 0;
//        try {
//            if (cartList.size() > 0) {
//                for (Cart item : cartList) {
//                    query = "select price from products where id=?";
//                    pst = this.con.prepareStatement(query);
//                    pst.setInt(1, item.getId());
//                    rs = pst.executeQuery();
//                    while (rs.next()) {
//                        sum+=rs.getDouble("price")*item.getQuantity();
//                    }
//
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return sum;
//    }
//
//    
//    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
//        List<Cart> book = new ArrayList<>();
//        try {
//            if (cartList.size() > 0) {
//                for (Cart item : cartList) {
//                    query = "select * from products where id=?";
//                    pst = this.con.prepareStatement(query);
//                    pst.setInt(1, item.getId());
//                    rs = pst.executeQuery();
//                    while (rs.next()) {
//                        Cart row = new Cart();
//                        row.setId(rs.getInt("id"));
//                        row.setName(rs.getString("name"));
//                        row.setCategory(rs.getString("category"));
//                        row.setPrice(rs.getDouble("price")*item.getQuantity());
//                        row.setQuantity(item.getQuantity());
//                        book.add(row);
//                    }
//
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return book;
//    }
//    public List<Product> getProductsByCategory(String category) {
//        List<Product> products = new ArrayList<>();
//        String query = "SELECT * FROM products WHERE category = ?";
//        
//        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//            preparedStatement.setString(1, category);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String name = resultSet.getString("name");
//                    double price = resultSet.getDouble("price");
//                    String image = resultSet.getString("image");
//
//                    Product product = new Product(id, name, category, price, image);
//                    products.add(product);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return products;
//    }
//}
package cn.techtutorial.dao;

import java.sql.*;
import java.util.*;

import cn.techtutorial.model.Cart;
import cn.techtutorial.model.Product;

public class ProductDao {
    private Connection con;

    public ProductDao(Connection con) {
        super();
        this.con = con;
    }

    public void deleteProduct(int id){
        try{
            String query= "DELETE FROM products WHERE id=?";
            PreparedStatement pt = this.con.prepareStatement(query);
            pt.setInt(1, id);
            pt.execute();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean editProductInfo(Product product){
        boolean test = false;
        String query = "UPDATE products SET name=?, category=?, price=?, image=?, description=?, season=?, origin=? WHERE id=?";
        try{
            PreparedStatement pt = this.con.prepareStatement(query);
            pt.setString(1, product.getName());
            pt.setString(2, product.getCategory());
            pt.setDouble(3, product.getPrice());
            pt.setString(4, product.getImage());
            pt.setString(5, product.getDescription());
            pt.setString(6, product.getSeason());
            pt.setString(7, product.getOrigin());
            pt.setInt(8, product.getId());

            pt.executeUpdate();
            test= true;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return test;
    }

    public boolean addProduct(Product product){
        boolean test = false;
        String query =  "INSERT INTO products (name,category,price,image,description,season,origin) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, product.getName());
            pst.setString(2, product.getCategory());
            pst.setDouble(3, product.getPrice());
            pst.setString(4, product.getImage());
            pst.setString(5, product.getDescription());
            pst.setString(6, product.getSeason());
            pst.setString(7, product.getOrigin());
            pst.executeUpdate();
            test= true;
        } catch(Exception e){
            e.printStackTrace();
        }
        return test;
    }

    public List<Product> searchProducts(String searchTerm) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String category = resultSet.getString("category");
                    double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");
                    String description = resultSet.getString("description");
                    String season = resultSet.getString("season");
                    String origin = resultSet.getString("origin");

                    Product product = new Product(id, name, category, price, image, description, season, origin);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT * FROM products";
            PreparedStatement pst = this.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String season = rs.getString("season");
                String origin = rs.getString("origin");

                Product product = new Product(id, name, category, price, image, description, season, origin);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getSingleProduct(int id) {
        Product product = null;
        try {
            String query = "SELECT * FROM products WHERE id=?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                String season = rs.getString("season");
                String origin = rs.getString("origin");

                product = new Product(id, name, category, price, image, description, season, origin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (!cartList.isEmpty()) {
                for (Cart item : cartList) {
                    String query = "SELECT price FROM products WHERE id=?";
                    PreparedStatement pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getProductId());
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        sum += rs.getDouble("price") * item.getQuantity();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
    public List<Cart> getCartProducts(int userId) {
    	System.out.println("====");
    	System.out.print(userId);
        List<Cart> productsInCart = new ArrayList<>();
        try {
            String query = "SELECT p.id AS productId, p.name, p.category, p.price, c.quantity " +
                           "FROM products p INNER JOIN cart c ON p.id = c.product_id " +
                           "WHERE c.user_id = ?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                Product product = new Product(productId, name, category, price);
                Cart cartItem = new Cart(productId ,name, category, price, quantity);
                productsInCart.add(cartItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsInCart;
    }


//    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
//        List<Cart> productsInCart = new ArrayList<>();
//        try {
//            if (!cartList.isEmpty()) {
//                for (Cart item : cartList) {
//                    String query = "SELECT * FROM products WHERE id=?";
//                    PreparedStatement pst = this.con.prepareStatement(query);
//                    pst.setInt(1, item.getId());
//                    ResultSet rs = pst.executeQuery();
//                    if (rs.next()) {
//                        int id = rs.getInt("id");
//                        String name = rs.getString("name");
//                        String category = rs.getString("category");
//                        double price = rs.getDouble("price") * item.getQuantity();
//                        int quantity = item.getQuantity();
//
//                        Cart cartItem = new Cart(id, name, category, price, quantity);
//                        productsInCart.add(cartItem);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return productsInCart;
//    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category = ?";
        
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, category);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");
                    String description = resultSet.getString("description");
                    String season = resultSet.getString("season");
                    String origin = resultSet.getString("origin");

                    Product product = new Product(id, name, category, price, image, description, season, origin);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public List<Product> getProductsByFilters(String category, String origin, String season) {
        List<Product> products = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM products WHERE 1=1");

        if (category != null && !category.equals("All")) {
            query.append(" AND category = ?");
        }
        if (origin != null && !origin.equals("All")) {
            query.append(" AND origin = ?");
        }
        if (season != null && !season.equals("All")) {
            query.append(" AND season = ?");
        }

        try (PreparedStatement preparedStatement = con.prepareStatement(query.toString())) {
            int paramIndex = 1;

            if (category != null && !category.equals("All")) {
                preparedStatement.setString(paramIndex++, category);
            }
            if (origin != null && !origin.equals("All")) {
                preparedStatement.setString(paramIndex++, origin);
            }
            if (season != null && !season.equals("All")) {
                preparedStatement.setString(paramIndex++, season);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String image = resultSet.getString("image");
                    String description = resultSet.getString("description");
                    String productSeason = resultSet.getString("season");
                    String productOrigin = resultSet.getString("origin");

                    Product product = new Product(id, name, category, price, image, description, productSeason, productOrigin);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

}



package cn.techtutorial.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cn.techtutorial.model.*;
public class UserDao {
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}
	public void deleteUser(int id){
        try{
            
           String query= "delete from users where id=?";
           PreparedStatement pt = this.con.prepareStatement(query);
           pt.setInt(1, id);
           pt.execute();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
	public List<User> getAllUser() {
        List<User> user = new ArrayList<>();
        try {
            query = "select * from users";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	User row = new User();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setEmail(rs.getString("email"));
                row.setPassword(rs.getString("password"));

                user.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return user;
    }
	public List<User> searchUser(String searchTerm) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE LOWER(name) LIKE LOWER(?) OR LOWER(email) LIKE LOWER(?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
	public User userLogin(String email, String password) {
		User user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            	user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
	 public boolean registerUser(User newUser) {
	        boolean registrationSuccess = false;
	        try {
	            query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
	            pst = this.con.prepareStatement(query);
	            pst.setString(1, newUser.getName());
	            pst.setString(2, newUser.getEmail());
	            pst.setString(3, newUser.getPassword());
	            pst.setString(4, newUser.getRole());

	            int rowsAffected = pst.executeUpdate();
	            registrationSuccess = rowsAffected > 0;
	        } catch (SQLException e) {
	            System.out.print(e.getMessage());
	        }
	        return registrationSuccess;
	    }
}

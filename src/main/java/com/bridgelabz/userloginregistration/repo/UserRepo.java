package com.bridgelabz.userloginregistration.repo;

import com.bridgelabz.userloginregistration.pojo.User;

import java.sql.*;

public class UserRepo {

    private static String url = "jdbc:mysql://localhost:3306/userregistration";
    private static String userName = "root";
    private static String password = "Iamcool@1996";

    Connection conn;
    PreparedStatement myStmt = null;

    public UserRepo() {
        createConnection();
    }

    private void createConnection(){
        try {
            this.conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public User saveUserInDatabase(User user) throws SQLException {
        String sql = "insert into userdetail "+" (firstName, lastName, email, password, phoneNo)"
                                              +" values (?, ?, ?, ?, ?)";
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, user.firstName);
        myStmt.setString(2, user.lastName);
        myStmt.setString(3, user.email);
        myStmt.setString(4, user.password);
        myStmt.setString(5, user.phoneNo);
        myStmt.executeQuery(sql);
        return user;
    }
    
    public User findByEmailId(String email) throws SQLException {
        String sql = "select * from userdetail where email=?";
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, email);
        ResultSet rs = myStmt.executeQuery(sql);
        User user = new User();
        user.firstName = rs.getString("firstName");
        user.lastName = rs.getString("lastName");
        user.email = rs.getString("email");
        user.password = rs.getString("password");
        user.phoneNo = rs.getString("phoneNo");
     return user;
    }

    public User updateUser(User user, String input, String type) throws SQLException {
        String sql = "update userdetail " + "set ?=?" + "where email =?";
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, type);
        myStmt.setString(1, input);
        myStmt.setString(3, user.email);
        myStmt.executeQuery(sql);
        user = findByEmailId(user.email);
        return user;
    }

    public void deleteByEmailId(String email) throws SQLException {
        String sql = "delete from userdetail where email=?";
        myStmt = conn.prepareStatement(sql);
        myStmt.setString(1, email);
        myStmt.executeQuery(sql);
    }
}

package com.bridgelabz.userloginregistration.repo;

import com.bridgelabz.userloginregistration.pojo.User;

import java.sql.*;

public class UserRepo {

    private static String url = "jdbc:mysql://localhost:3306/userregistration";
    private static String userName = "root";
    private static String password = "Iamcool@1996";

    Connection conn;
    Statement myStmt;

    public UserRepo() throws SQLException {
        this.conn = DriverManager.getConnection(url, userName, password);
        this.myStmt = conn.createStatement();
    }

    public User saveUserInDatabase(User user) throws SQLException {
        String sql = new StringBuilder().append("insert into userdetail ")
                                        .append(" (firstName, lastName, email, password, phoneNo)")
                                        .append(" values ('").append(user.firstName).append("', '")
                                        .append(user.lastName).append("', '").append(user.email)
                                        .append("', '").append(user.password).append("', '")
                                        .append(user.phoneNo).append("')").toString();
        myStmt.executeUpdate(sql);
        return user;
    }
    
    public User findByEmailId(String email) throws SQLException {
        String sql1 = "select * from userdetail where email='"+email+"'";
        ResultSet rs = myStmt.executeQuery(sql1);
        User user = new User();
        user.firstName = rs.getString("firstName");
        user.lastName = rs.getString("lastName");
        user.email = rs.getString("email");
        user.password = rs.getString("password");
        user.phoneNo = rs.getString("phoneNo");
     return user;
    }

    public User updateUser(User user, String input, String type) throws SQLException {
        String sql = "update userdetail"
                   + "set "+ type +"='"+input+"'"
                   + "where email ='"+user.email+"'";
        myStmt.executeUpdate(sql);
        user = findByEmailId(user.email);
        return user;
    }

    public void deleteByEmailId(String email) throws SQLException {
        String sql = "delete from userdetail where email='"+email+"'";
        myStmt.executeUpdate(sql);
    }
}

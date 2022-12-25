/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author urvas
 */
public class DBConnection {
    
    static Connection connect = null;
    
    public static Connection getConnection(){
        try {
          //System.out.println("DBConnection.getConnection()");
          Class.forName("org.postgresql.Driver");
          //System.out.println("Class.forname");
          connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library Management System","postgres", "123456");
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
      return connect;
    }
    
}

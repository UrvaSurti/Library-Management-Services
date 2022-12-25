/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lms;

import java.sql.Connection;
/**
 *
 * @author urvas
 */
public class LMS {

    static Connection connect = null;
    
    public static void main(String[] args) {
        System.out.println("Hello World");
        connect = new DBConnection().getConnection();
        
        System.out.println("Connection Successful");
        
        LoginPage login = new LoginPage();
        login.setVisible(true);
    }
    
}

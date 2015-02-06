/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoa2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdeboer1
 */
public final class DatabaseFactory {
    public static String filePath 
            = "src" + File.separatorChar + "config.properties";
    public static String connectionClass = "db.connector";
    
    private DatabaseFactory(){
    }
    
    
    public static Connection getConnection() throws IOException, SQLException,
            ClassNotFoundException {
        Connection connection = null;
        File file = new File(filePath);
        Properties properties = new Properties();
        FileInputStream input;
        try{
            input = new FileInputStream(file);
            properties.load(input);
            input.close();
            
            String driverClass = properties.getProperty("driver.class");
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            Class.forName (driverClass);
            connection = DriverManager.getConnection(url,username, password);
            
        }catch(IOException | ClassNotFoundException | SQLException ex){
            //add custom exception for file not provided
        }
        return connection;
    }
//    
//    public static void main(String[] args) {
//        Connection conn = null;
//        try {
//            conn = DatabaseFactory.getConnection();
//        } catch (IOException | SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseFactory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println(conn.toString());
//    }
}

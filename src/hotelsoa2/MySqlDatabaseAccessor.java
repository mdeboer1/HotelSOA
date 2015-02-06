package hotelsoa2;


import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mdeboer1
 */
public class MySqlDatabaseAccessor implements DatabaseAccessorStrategy {
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    public MySqlDatabaseAccessor(){
        
    }
    
    @Override
    public final void openConnection() throws IOException, SQLException, ClassNotFoundException{
        connection = DatabaseFactory.getConnection();
//        System.out.println(connection.toString());
//        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel",
//                "root", "admin");
        
    }
    
    @Override
    public final void closeConnection() throws SQLException{
        try {
            statement.close();
        } catch (SQLException se){
            // throws to caller
        }
    }
    
    @Override
    public final List<Map<String, Object>> getHotelRecords(String tableName)
        throws SQLException, IOException, ClassNotFoundException {
        
        openConnection();
        List<Map<String, Object>> hotelRecords =
                new ArrayList<>();
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {

        }
        
        String sqlStatement = "SELECT * FROM " + tableName;
        result = statement.executeQuery(sqlStatement);
        
        ResultSetMetaData metaData = result.getMetaData();	
        final int fields = metaData.getColumnCount();
        
        while(result.next()) {
                Map<String,Object> record = new LinkedHashMap<>();
                for( int i=1; i <= fields; i++ ) {
                    record.put( metaData.getColumnName(i), result.getObject(i) );
                } // end for
                hotelRecords.add(record);
        }
        closeConnection();
        
        return hotelRecords;
    }
    
       
    public static void main(String[] args) throws SQLException, IOException, 
            ClassNotFoundException {
        List<Map<String, Object>> list;
        DatabaseAccessorStrategy accessor = new MySqlDatabaseAccessor();
        list = accessor.getHotelRecords("hotels");
        for (Map<String,Object> record : list){
            System.out.println(record.toString());
        }
        
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoa2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mdeboer1
 */
public interface DatabaseAccessorStrategy {

    public abstract void closeConnection() throws SQLException;

    public abstract List<Map<String, Object>> getHotelRecords(String tableName) 
            throws SQLException, IOException, ClassNotFoundException;

    public abstract void openConnection() throws IOException, 
            SQLException, ClassNotFoundException;
    
}

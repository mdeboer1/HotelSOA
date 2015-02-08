/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoa2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mdeboer1
 */
public interface HotelDAOStrategy {

    public abstract List<Hotel> getHotelRecords(String tableName) throws 
            SQLException, IOException, ClassNotFoundException;
    
    public abstract Hotel getOneHotelRecordById(int hotelId, String tableName)
        throws IOException, SQLException, ClassNotFoundException;
    
}

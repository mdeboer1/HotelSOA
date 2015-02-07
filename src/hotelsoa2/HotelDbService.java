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
public class HotelDbService {
    private HotelDAOStrategy dao;
    public HotelDbService(){
        try{
            dao = MySqlDatabaseFactory.getDAO();
        } catch(IOException | ClassNotFoundException | InstantiationException 
                | IllegalAccessException ex){
            
        }
    }
    
    public final List<Hotel> retrieveHotels(String tableName)throws 
            SQLException, IOException, ClassNotFoundException, NullPointerException {
        
        List<Hotel> records = null;
        
        try {
            records = dao.getHotelRecords(tableName);
        } catch (ClassNotFoundException | SQLException | IOException | 
                NullPointerException ex){
            
        }
        return records;
    }
    
    public static void main(String[] args) {
        HotelDbService service;
        List<Hotel> list = null;
        try {
            service = new HotelDbService();
            list = service.retrieveHotels("hotels");
        } catch (ClassNotFoundException | SQLException | IOException | 
                NullPointerException ex){
            System.out.println("Oops");
        }
        for(Hotel h : list){
            System.out.println(h.toString());
        }
    }
}

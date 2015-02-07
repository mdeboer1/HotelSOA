/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoa2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mdeboer1
 */
public class MySqlHotelReportDAO implements HotelDAOStrategy {
    
    private DatabaseAccessorStrategy database;
    
    /**
     * No argument constructor receives the type of accessor from the 
     * MySqlDatabaseFactory
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public MySqlHotelReportDAO() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException{
        try {
            this.database = MySqlDatabaseFactory.getAccessor();
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException ex) {
            
        }
    }
    
    @Override
    public final List<Hotel> getHotelRecords(String tableName) throws 
            SQLException, IOException, ClassNotFoundException, NullPointerException{
        
        List<Map<String, Object>> records = null;

        try {
            records = database.getHotelRecords(tableName);
        } catch (ClassNotFoundException | SQLException | IOException | 
                NullPointerException ex) {
            //throw to caller
        }
        List<Hotel> list = new ArrayList<>();
        for (Map<String,Object> map : records){
            Object obj = map.get("hotel_id");
            String id = obj == null ? "Test" : obj.toString();
            int hotelId = Integer.parseInt(id);
            obj = map.get("hotel_name");
            String hotelName = obj == null ? "Test" : obj.toString();
            obj = map.get("hotel_address");
            String hotelAddress = obj == null ? "Test" : obj.toString();
            obj = map.get("hotel_city");
            String hotelCity = obj == null ? "Test" : obj.toString();
            obj = map.get("hotel_state");
            String hotelState = obj == null ? "Test" : obj.toString();
            obj = map.get("hotel_zip");
            String hotelZip = obj == null ? "Test" : obj.toString();
            Hotel hotel = new Hotel(hotelId, hotelName, hotelAddress, hotelCity,
                    hotelState, hotelZip);
            list.add(hotel);
        }
        return list;
    }
    
    public static void main(String[] args){
        List<Hotel> list = null;
        try {
            MySqlHotelReportDAO dao = new MySqlHotelReportDAO();
            list = dao.getHotelRecords("hotels");
        } catch (ClassNotFoundException | SQLException | IOException | 
                NullPointerException | InstantiationException  |
                        IllegalAccessException ex){
            System.out.println("oops");
        }
        for (Hotel h : list){
            System.out.println(h.toString());
        }
        
    }
}
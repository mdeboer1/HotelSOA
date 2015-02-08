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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public final Hotel retrieveHotelById(int hotelId, String tableName)
        throws IOException, SQLException, ClassNotFoundException,
            NullPointerException {
        Hotel hotel = null;
        
        try {
            hotel = dao.getOneHotelRecordById(hotelId, tableName);
        }catch(IOException | SQLException | NullPointerException |
                ClassNotFoundException e){
            
        }
        return hotel;
    }
    
    public final void deleteHotelById(int hotelId)throws IOException, 
            SQLException, ClassNotFoundException{
        try {
            dao.deleteHotelById(hotelId);
        } catch (IOException | SQLException | ClassNotFoundException ex) {
           
        }
    }
    
    public final void addHotel(Hotel hotel)throws IOException, SQLException,
            ClassNotFoundException{
        try {
            dao.addHotel(hotel);
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            
        }
    }
    public final void addHotels(List<Hotel> list) throws IOException, 
            SQLException, ClassNotFoundException{
        try {
            dao.addHotels(list);
        } catch (IOException | SQLException | ClassNotFoundException e){
            
        }
    }
    
    public static void main(String[] args) {
        try {
            HotelDbService service = new HotelDbService();
            Hotel hotel1 = new Hotel(7, "Hotel7", "159 Here", "Oconomowoc", "WI",
                    "53066");
            Hotel hotel2 = new Hotel(8, "Hotel8", "357 Yellow", "Oconomowoc", "WI",
                    "53066");
            Hotel hotel3 = new Hotel(9, "Hotel9", "654 White", "Oconomowoc", "WI",
                    "53066");
            List<Hotel> list = new ArrayList<>();
            list.add(hotel1);
            list.add(hotel2);
            list.add(hotel3);
            service.addHotels(list);

        } catch (IOException ex) {
            Logger.getLogger(HotelDbService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HotelDbService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HotelDbService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

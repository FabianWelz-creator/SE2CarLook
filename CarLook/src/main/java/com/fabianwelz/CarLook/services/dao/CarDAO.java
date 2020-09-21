package com.fabianwelz.CarLook.services.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fabianwelz.CarLook.model.object.dto.CarDTO;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.services.db.*;
import com.fabianwelz.CarLook.model.object.dto.UserDTO;

public class CarDAO implements DAO<Integer, CarDTO> {
private static CarDAO me;
    
    private CarDAO() {}
    
    public static CarDAO getInstance() {
        if(me == null){
            me = new CarDAO();
        }
        return me;
    }
    // Autos anlegen
    @Override
    public void create(CarDTO obj) throws DatabaseException {
        try {
            PreparedStatement s = JDBCConnectionHandler.getInstance().getPreparedStatement("INSERT INTO carlookdb.auto "
                                                                                         + "VALUES (?,?,?,?)");
            s.setString(1, obj.getBrand());
            s.setString(2, obj.getYear());
            s.setString(3, obj.getInfo());
            s.setString(4, obj.getEmail());
            // position 5: auto-generated id;
            
            
            s.executeUpdate();
            
            
        } catch (SQLException ex) {       
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler beim Anlegen des Autos in der Datenbank! Bitte Programmierer informieren.", ex.getSQLState());
        } finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }     
    }
    //Autos einlesen
    @Override
    public CarDTO read(Integer id) throws DatabaseException {
        
        try {                  
            
            PreparedStatement s = JDBCConnectionHandler.getInstance().getPreparedStatement("SELECT * FROM carlookdb.auto "
                                                                                    + "WHERE carlookdb.auto.id = (?)");
            
            s.setInt(1, id);
            
            ResultSet set = s.executeQuery();
            
            CarDTO car = new CarDTO();
            
            car.setBrand(set.getString(1));
            car.setYear(set.getString(2));
            car.setInfo(set.getString(3));
            car.setEmail(set.getString(4));
            car.setId(set.getInt(5));
       
            
            return car;
            
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler beim Lesen eines Autos aus der Datenbank! Bitte Programmierer informieren.");
        } finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }       
        
    }
    // Autos als liste zurueckgeben
    public List<CarDTO> getAllmycars(String email) throws DatabaseException {
        try {
            
        	PreparedStatement s = JDBCConnectionHandler.getInstance().getPreparedStatement("SELECT * FROM carlookdb.auto "
                                                                                            + "WHERE carlookdb.auto.Email = (?)");
            s.setString(1, email);
            ResultSet set = s.executeQuery();
            ArrayList<CarDTO> ret = new ArrayList<>();
            
            while(set.next()){
                CarDTO car = new CarDTO();
            
                car.setBrand(set.getString(1));
                car.setYear(set.getString(2));
                car.setInfo(set.getString(3));
                car.setEmail(set.getString(4));
                car.setId(set.getInt(5));
                
                
                ret.add(car);
            }
            
            return ret;
        }
        catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler beim Lesen aller Autos aus der Datenbank! Bitte Programmierer informieren.");
        }
        finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }
    }

}

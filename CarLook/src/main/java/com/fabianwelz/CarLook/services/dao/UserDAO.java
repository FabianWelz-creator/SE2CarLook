package com.fabianwelz.CarLook.services.dao;
import java.sql.*;
import com.fabianwelz.CarLook.model.object.dto.SellerDTO;
import com.fabianwelz.CarLook.model.object.dto.LoginDTO;
import com.fabianwelz.CarLook.model.object.dto.UserDTO;
import com.fabianwelz.CarLook.services.db.JDBCConnectionHandler;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.jsoup.select.Collector;

public class UserDAO implements DAO<String, UserDTO> {

    private static UserDAO me;
    
    private UserDAO() {}
    
    public static UserDAO getInstance() {
        if(me == null){
            me = new UserDAO();
        }
        return me;
    }
    
    public void create(UserDTO obj) throws DatabaseException {
        try {    
            // User does not exist -> Create new user now
        	PreparedStatement s = JDBCConnectionHandler.getInstance().getPreparedStatement("INSERT INTO carlookdb.nutzer "
                                                                                          + "VALUES (?,?,?)");

            s.setString(1, obj.getEmail());
            s.setString(2, obj.getPassword());
            s.setString(3, obj.getName());

            s.executeUpdate();
        } catch (SQLException ex) {       
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler beim Anlegen eines neuen Nutzers in der Datenbank! Bitte Programmierer informieren.", ex.getSQLState());
        } finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }    
    } 
    
    public UserDTO readLogin(LoginDTO login) throws DatabaseException {
        UserDTO user = null;
        try {
            
            PreparedStatement ps = JDBCConnectionHandler.getInstance().getPreparedStatement("SELECT * FROM carlookdb.nutzer " + 
                                                                                            "WHERE carlookdb.nutzer.mail = ? " +
                                                                                            "AND carlookdb.nutzer.passwort = ?");
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getPassword());
            ResultSet set = ps.executeQuery();
            if (set.next()) {
            	user = new UserDTO(set.getString(1));

            }
            

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Programmierer benachrichtigen.");
        }  finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }    
        return user;
 }
    // fuer junit test
    public void delete(String email) throws DatabaseException {
        try {
            PreparedStatement s = JDBCConnectionHandler.getInstance().getPreparedStatement("SELECT * FROM CarLookDB.Nutzer WHERE CarLookDB.Nutzer.mail = ?");
            s.setString(1, email);
            
            s.executeQuery();
            

            
            PreparedStatement userDel = JDBCConnectionHandler.getInstance().getPreparedStatement("DELETE FROM CarLookDB.Nutzer WHERE CarLookDB.Nutzer.mail = ?");
            userDel.setString(1, email);
            
            userDel.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DatabaseException("Fehler beim Löschen eines Datensatzes! Bitte den Programmierer benachrichtigen.");
        }
        finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }
    }
    @Override
    public UserDTO read(String email) throws DatabaseException {
        try {
            
            PreparedStatement ps = JDBCConnectionHandler.getInstance().getPreparedStatement("SELECT * FROM CarLookDB.Nutzer " + 
                                                                                            "WHERE CarLookDB.Nutzer.mail = ? ");
            ps.setString(1, email);
            ResultSet set = ps.executeQuery();
            
            UserDTO user = null;
            if (set.next()) {
                user = new UserDTO(set.getString(1), set.getString(2),set.getString(3));
            }
            else {
                // Return a null User if the profile has been damaged;
                return user;
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCConnectionHandler.getInstance().closeConnection();
        }    
        // -----
        return null;
    }
}

package com.fabianwelz.CarLook.services.db;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;

public class JDBCConnectionHandler {
	 private static JDBCConnectionHandler connection;
	 private String login = "fwelz2s";
	 private String password = "fwelz2s";
	 private String url = "jdbc:postgresql://dumbo.inf.h-brs.de:5432/fwelz2s";
	 private Connection conn;
	 
	 public static JDBCConnectionHandler getInstance() throws DatabaseException {
	        if (connection == null) {
	            connection = new JDBCConnectionHandler();
	        }
	        return connection;
	    }
	 
	  private JDBCConnectionHandler() throws DatabaseException {
	        this.initConnection();
	    }
	  
	  public void initConnection() throws DatabaseException {
	        try {
	            DriverManager.registerDriver(new org.postgresql.Driver());
	            openConnection();
	        } catch (SQLException ex) {
	            Logger.getLogger(JDBCConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	        
	    public void openConnection() throws DatabaseException {
	        try {
	            Properties props = new Properties();
	            props.setProperty("user", login);
	            props.setProperty("password", password);
	            
	            conn = DriverManager.getConnection(url, props);
	        } catch (SQLException ex) {
	            Logger.getLogger(JDBCConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
	            throw new DatabaseException("Fehler bei Zugriff auf die Datenbank. Ist eine sichere Verbindung vorhanden?");
	        }
	    }
	        
	    public Statement getStatement() throws DatabaseException {
	        try {    
	            if (conn.isClosed()) {
	                openConnection();
	            }
	            return conn.createStatement();
	        } catch (SQLException ex) {
	            Logger.getLogger(JDBCConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    }
	        
	    public void closeConnection() {
	        try {
	            conn.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(JDBCConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
	        try {    
	            if (conn.isClosed()) {
	                openConnection();
	            }
	            return conn.prepareStatement(sql);
	        } catch (SQLException ex) {
	            Logger.getLogger(JDBCConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
	            return null;
	        }
	    } 
	    public void commitChanges() {
	        try {
	            if(!conn.isClosed()) {
	                conn.commit();
	            }
	        }
	        catch (SQLException ex) {
	            Logger.getLogger(JDBCConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	
}

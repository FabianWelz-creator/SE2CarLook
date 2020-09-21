package com.fabianwelz.CarLook.process.control;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import com.fabianwelz.CarLook.process.control.exception.LoginException;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.model.object.dto.LoginDTO;
import com.fabianwelz.CarLook.model.object.dto.UserDTO;
import com.fabianwelz.CarLook.services.dao.UserDAO;
import com.fabianwelz.CarLook.services.util.*;

public class LoginControl {
	
public static void checkAuthentication(LoginDTO login) throws LoginException, DatabaseException {
        
        System.out.println("Login Control check Authentication");
        // Check if input is empty;
        if (login.getEmail().equals("") || login.getPassword().equals("")) {
            throw new LoginException("Bitte Email und Passwort eingeben!");
        }
        
        UserDTO user = UserDAO.getInstance().readLogin(login);
        if (user == null) { 
            throw new LoginException("Dieser Benutzer ist dem System nicht bekannt oder falsches Passwort!");
        }
        else {
            
            SessionControl.getInstance().setCurrentUser(user); 
            System.out.println(user.getEmail() + " ist erfolgreich eingeloggt");
        }
        
        // Navigate to main view;      
        UI.getCurrent().getNavigator().navigateTo(Views.SALESPAGE);
        
    }

    
    public static void logoutUser() {
        SessionControl.getInstance().closeSession();
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        Page.getCurrent().reload();
    }
	
}

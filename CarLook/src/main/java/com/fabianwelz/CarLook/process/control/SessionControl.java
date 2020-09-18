package com.fabianwelz.CarLook.process.control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

import com.fabianwelz.CarLook.model.object.dto.UserDTO;
import com.fabianwelz.CarLook.services.util.Roles;
import com.fabianwelz.CarLook.model.object.dto.SellerDTO;

public class SessionControl {
	
    private static SessionControl me;
    
    private VaadinSession currentSession; 
    
    public SessionControl() {
        currentSession = UI.getCurrent().getSession();
    }
    
    public static SessionControl getInstance() {
        if(me == null){
            me = new SessionControl();
        }
        return me;
    }
    
    public boolean hasLogin() {
        return (currentSession.getAttribute(Roles.CURRENT_USER.getValue()) != null);
    }
    
    public void setCurrentUser(UserDTO user) {
        currentSession.setAttribute(Roles.CURRENT_USER.getValue(), user);
    }
    
    public UserDTO getCurrentUser() {
        return (UserDTO)currentSession.getAttribute(Roles.CURRENT_USER.getValue());
    }
    
    public void closeSession() {
        currentSession.setAttribute(Roles.CURRENT_USER.getValue(), null);
        currentSession.close();
    }
    /*
    public boolean isSeller() {
        return getCurrentUser() instanceof SellerDTO;
    }
    */
}

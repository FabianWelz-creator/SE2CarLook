package com.fabianwelz.CarLook.gui.view;
import com.fabianwelz.CarLook.services.util.Views;
import com.fabianwelz.CarLook.gui.view.Loginpage;
import com.fabianwelz.CarLook.model.object.dto.LoginDTO;
import com.fabianwelz.CarLook.model.object.dto.UserDTO;
import com.fabianwelz.CarLook.process.control.LoginControl;
import com.fabianwelz.CarLook.process.control.SessionControl;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.process.control.exception.LoginException;
import com.fabianwelz.CarLook.services.util.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class Loginpageview extends Loginpage implements View{ 
	public Loginpageview() {
		loginregisterbutton.addClickListener((Button.ClickEvent e) -> {
			UI.getCurrent().getNavigator().navigateTo(Views.REGISTER);
	});
		//Einloggen
		loginbutton.addClickListener((Button.ClickEvent e) -> {
	            String login = loginemailtextfield.getValue();
	            String password = loginpasswordtextfield.getValue();
	            try {
	                LoginControl.checkAuthentication(new LoginDTO(login, password));
	            } catch (LoginException ex) {
	                Notification.show("Fehler", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
	                loginemailtextfield.setValue("");
	                loginpasswordtextfield.setValue("");
	            } catch (DatabaseException ex) {
	                Notification.show("DB-Fehler", ex.getMessage(), Notification.Type.ERROR_MESSAGE);
	                loginemailtextfield.setValue("");
	                loginpasswordtextfield.setValue("");
	            }
	        });
	    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        UserDTO user = SessionControl.getInstance().getCurrentUser();
        
        if (user != null) {
            UI.getCurrent().getNavigator().navigateTo(Views.SALESPAGE);
        }
        else {
        }
    }   

}
	


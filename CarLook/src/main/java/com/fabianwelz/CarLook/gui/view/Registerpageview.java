package com.fabianwelz.CarLook.gui.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.fabianwelz.CarLook.gui.ui.MyUI;
import com.fabianwelz.CarLook.model.object.dto.SellerDTO;
import com.fabianwelz.CarLook.process.control.RegistrationControl;
import com.fabianwelz.CarLook.process.control.exception.RegistrationException;
import com.fabianwelz.CarLook.services.util.Views;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;

public class Registerpageview extends Registerpage implements View{ 
	
	public Registerpageview () {
		// Lese Daten aus den Feldern aus 
		Binder<SellerDTO> binder = new Binder<>(SellerDTO.class);
		binder.forField(registeremailtextfield).withValidator(new EmailValidator("Das ist keine gültige E-Mail Adresse"))
        .bind(SellerDTO::getEmail, SellerDTO::setEmail);
		binder.forField(registernametextfield).asRequired("Namen eintragen!").bind(SellerDTO::getName, SellerDTO::setName);
		binder.forField(registerpasswordtextfield).asRequired("Passwort eintragen!").bind(SellerDTO::getPassword, SellerDTO::setPassword);
		SellerDTO s = new SellerDTO();
        binder.setBean(s);
        		// registriere Nutzer
            	registerdonebutton.addClickListener ((Button.ClickEvent e)-> {			
            				try {
								RegistrationControl.registerSeller(s);
								MyUI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
								
							} catch (DatabaseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (RegistrationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				
                      });  
            	//Abbruch
            	registerexit.addClickListener((Button.ClickEvent e)-> {	
            		MyUI.getCurrent().getNavigator().navigateTo(Views.LOGIN);	
            	}); 
	}

}

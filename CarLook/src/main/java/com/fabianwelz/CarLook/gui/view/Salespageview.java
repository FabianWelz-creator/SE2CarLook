package com.fabianwelz.CarLook.gui.view;

import com.fabianwelz.CarLook.services.util.Views;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.fabianwelz.CarLook.model.object.dto.SellerDTO;

import java.util.ArrayList;
import java.util.List;

import com.fabianwelz.CarLook.model.object.dto.CarDTO;
import com.fabianwelz.CarLook.process.control.SessionControl;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.process.control.CarControl;
import com.fabianwelz.CarLook.process.control.LoginControl;
import com.fabianwelz.CarLook.services.dao.CarDAO;

public class Salespageview extends Salespage implements View{  
	
	public Salespageview () {
		Grid<CarDTO>gridcars = new Grid<>();
		gridcars.setVisible(false);
		gridcars.setStyleName("gridcars");
		createcarform.setVisible(false);
		Binder<CarDTO> binder = new Binder<>(CarDTO.class);
		binder.forField(brandtextfield).bind(CarDTO::getBrand,CarDTO::setBrand);
		binder.forField(yeartextfield).bind(CarDTO::getYear,CarDTO::setYear);
		binder.forField(infotextfield).bind(CarDTO::getInfo,CarDTO::setInfo);
		CarDTO c = new CarDTO();	        		
		binder.setBean(c);
	
		
		//logout
		logoutbutton.addClickListener((Button.ClickEvent e) -> {
			LoginControl.logoutUser();
	});
		
		//Formular zum erstellen des Autos anzeigen
		addnewcarbutton.addClickListener((Button.ClickEvent e) -> {
		createcarform.setVisible(true);	
	});
		//Auto erstellen
		createcar.addClickListener((Button.ClickEvent e) -> {		
			try {
				c.setEmail(SessionControl.getInstance().getCurrentUser().getEmail());
				CarControl.registerCar(c);
				if(gridcars.isVisible())
				{
					try {
						List<CarDTO>mycars 	= new ArrayList();
						mycars = CarDAO.getInstance().getAllmycars(SessionControl.getInstance().getCurrentUser().getEmail());
						if (mycars.isEmpty()) {
							Notification.show(null, "Keine Autos gefunden", Notification.Type.WARNING_MESSAGE);
						}
						else {	
							fillgrid(gridcars,mycars);
						}
						} catch (DatabaseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			} catch (DatabaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			createcarform.setVisible(false);	
		});
		// Zeige die Autos in einem Grid an 
		showcarsbutton.addClickListener((Button.ClickEvent e) -> {		
			try {
				List<CarDTO>mycars 	= new ArrayList();
				mycars = CarDAO.getInstance().getAllmycars(SessionControl.getInstance().getCurrentUser().getEmail());
				if (mycars.isEmpty()) {
					Notification.show(null, "Keine Autos gefunden", Notification.Type.WARNING_MESSAGE);
				}
				else {	
					fillgrid(gridcars,mycars);
				}
				} catch (DatabaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		});
	}
	// Grid wird aus der Datenbank befuellt
	private void fillgrid(Grid<CarDTO>gridcars,List<CarDTO>mycars) {
		gridlayout.addComponent(gridcars);
		gridcars.setVisible(true);
		gridcars.removeAllColumns();
		gridcars.setItems(mycars);
		gridcars.addColumn(CarDTO::getBrand).setCaption("Marke");
        gridcars.addColumn(CarDTO::getYear).setCaption("Baujahr");
        gridcars.addColumn(CarDTO::getInfo).setCaption("Infotext");
        gridcars.setSizeFull();
        gridcars.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridcars.setHeightByRows(mycars.size());
	}
}

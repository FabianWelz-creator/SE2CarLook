package com.fabianwelz.CarLook.gui.view;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Salespage extends VerticalLayout {
	protected Button logoutbutton;
	protected Button addnewcarbutton;
	protected Button showcarsbutton;
	protected HorizontalLayout salehorizontal;
	protected FormLayout createcarform;
	protected TextField brandtextfield;
	protected TextField yeartextfield;
	protected TextField infotextfield;
	protected Button createcar;
	protected VerticalLayout gridlayout;

	public Salespage() {
		Design.read(this);
	}
}

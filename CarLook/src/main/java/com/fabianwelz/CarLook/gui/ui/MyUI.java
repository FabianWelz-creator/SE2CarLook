package com.fabianwelz.CarLook.gui.ui;

import javax.servlet.annotation.WebServlet;
import com.vaadin.*;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.navigator.Navigator;
import com.fabianwelz.CarLook.gui.view.*;
import com.fabianwelz.CarLook.selenium.test.*;
import com.fabianwelz.CarLook.services.util.Views;
/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	Navigator navi = new Navigator(this, this); // Views registrieren. Übergibt Teil-URL. Konstruktor übernimmt zwei Argumente: 1. UI, 2. Container, der den Navigator ersetzt

        // Views registrieren (Routen/Links):
        navi.addView(Views.LOGIN, Loginpageview.class);
        navi.addView(Views.REGISTER, Registerpageview.class);
        navi.addView(Views.SALESPAGE, Salespageview.class);
        // Default View:
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

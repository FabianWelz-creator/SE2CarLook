package com.fabianwelz.CarLook.services.util;
import com.fabianwelz.CarLook.model.object.dto.UserDTO;
import com.vaadin.ui.UI;
public enum Roles {

    CURRENT_USER("currentUser");
    
    
    private final String value;
    
    Roles(String val) {
        value = val;
    }
    
    public String getValue() {
        return value;
    }
}

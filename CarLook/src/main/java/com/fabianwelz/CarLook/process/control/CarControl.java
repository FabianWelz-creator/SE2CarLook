package com.fabianwelz.CarLook.process.control;

import com.fabianwelz.CarLook.model.object.dto.CarDTO;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.services.dao.CarDAO;


public class CarControl {
	public static void registerCar(CarDTO car) throws DatabaseException {
        try {
           CarDAO.getInstance().create(car); 
        }
        catch (DatabaseException ex) {
            if (ex.getVendorError().equals(DatabaseException.ErrorCode.UNIQUE_VIOLATION)) {
                throw new DatabaseException("Ein Problem ist aufgetreten bitte informieren Sie den Programmierer");
            }
            else {
                throw ex;
            }
        }
        
    }
}

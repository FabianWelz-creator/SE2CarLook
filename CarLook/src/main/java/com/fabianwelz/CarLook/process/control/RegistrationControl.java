package com.fabianwelz.CarLook.process.control;
import com.fabianwelz.CarLook.model.object.dto.SellerDTO;
import com.fabianwelz.CarLook.services.dao.UserDAO;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.process.control.exception.RegistrationException;


public class RegistrationControl {
	
    public static void registerSeller(SellerDTO seller) throws DatabaseException, RegistrationException {
        try {
           UserDAO.getInstance().create(seller); 
        }
        catch (DatabaseException ex) {
            if (ex.getVendorError().equals(DatabaseException.ErrorCode.UNIQUE_VIOLATION)) {
                throw new RegistrationException("Die Email ist bereits vergeben! Bitte wähle eine andere.");
            }
            else {
                throw ex;
            }
        }
        
    }

}

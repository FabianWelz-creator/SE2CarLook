package com.fabianwelz.CarLook.process.control.exception;

public class DatabaseException extends Exception {
	private ErrorCode vendorError;
    
    public DatabaseException(String msg) {
        super(msg);
    }
    
    public DatabaseException(String msg, String vendorErrorCode) {
        super(msg);
        vendorError = convertErrorCode(vendorErrorCode);
    }

    public ErrorCode getVendorError() {
        return vendorError;
    }
    
    private ErrorCode convertErrorCode(String vendorErrorCode) {
        switch (vendorErrorCode) {
            case "23503":
                return ErrorCode.FOREIGN_KEY_VIOLATION;
            case "23505":
                return ErrorCode.UNIQUE_VIOLATION;
            default:
                return ErrorCode.OTHER;
        }
    }
    
    public enum ErrorCode {
        FOREIGN_KEY_VIOLATION,  // 23503
        UNIQUE_VIOLATION,       // 23505
        OTHER;
    }
	
	
}

package com.fabianwelz.CarLook.model.object.dto;

public class UserDTO {
	@Override
    public String toString() {
        return "UserDTO{" + "   Email=" + email + ", Passwort=" + password + "Name="+name+'}';
    }
	 private String name;
	 private String email;
	 private String password; 
	 
	 public UserDTO() {
	        name="";
		 	email = "";
	        password = ""; 
	    }
	 
	 public UserDTO(String email) {
	        this.email = email;
	        password = ""; 
	    }
	 
	    public UserDTO( String email, String pw, String name) {
	        this.email = email;
	        this.password = pw;
	        this.name = name;
	    }
	    public UserDTO(UserDTO x) {
	        this.email = x.getEmail();
	        this.password = x.getPassword();
	        this.name = x.getName();
	    }
	    public String getName() {
	    	return name;
	    }
	    public void setName(String name) {
	    	this.name = name;
	    }
	    
	    
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    
	    public String getPassword() {
	        return password;
	    }
	    
	    public void setPassword(String pw) {
	        this.password = pw; 
	    }
	    

}

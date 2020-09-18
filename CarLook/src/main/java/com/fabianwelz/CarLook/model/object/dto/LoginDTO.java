package com.fabianwelz.CarLook.model.object.dto;

public class LoginDTO {
	
	private String email;
    private String password;
    
    public LoginDTO(String mail, String pw) {
        email = mail;
        password = pw;
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

    public void setPassword(String password) {
        this.password = password;
    }

}

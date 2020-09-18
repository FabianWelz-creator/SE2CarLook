package com.fabianwelz.CarLook.model.object.dto;

// Klasse fuer die Vertriebler, kann eventuell noch erweitert werden.
public class SellerDTO extends UserDTO {

	@Override
    public String toString() {
        String s = super.toString();
        return s + "\n SellerDTO{" + "Vorname=" + firstname + ", Nachname=" + lastname + '}';
    }
	private String firstname;
    private String lastname;
    
    public SellerDTO() {
        super();
        firstname = "";
        lastname = "";
    }
    public SellerDTO(String username) {
        super(username);
        firstname = "";
        lastname = "";
    }
    
    public SellerDTO(String username, String firstname) {
        super(username);
        this.firstname = firstname;
        lastname = "";
    }
    
    public SellerDTO(String username, String firstname, String lastname) {
        super(username);
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public SellerDTO(UserDTO template, String username, String firstname, String lastname) {
        super(template);
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

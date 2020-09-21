package com.fabianwelz.CarLook.selenium.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fabianwelz.CarLook.services.dao.UserDAO;
import com.fabianwelz.CarLook.model.object.dto.SellerDTO;
import com.fabianwelz.CarLook.process.control.exception.DatabaseException;
import com.fabianwelz.CarLook.process.control.exception.RegistrationException;
import com.fabianwelz.CarLook.process.control.RegistrationControl;


class junitseleniumlogintest {
	//Akzeptanztest fuer den Login
	@Test
	void test() throws DatabaseException, RegistrationException, InterruptedException {
		SellerDTO dummy = new SellerDTO();
		dummy.setEmail("dummy@dummy.de");
		dummy.setName("dummy");
		dummy.setPassword("dummy");
		RegistrationControl.registerSeller(dummy);
		String path = System.getProperty("user.dir");
	    System.out.println(path); 
	    System.setProperty("webdriver.chrome.driver",path+"\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,5);
	    driver.get("http://localhost:8084/CarLook/#!login"); 
	    driver.manage().window().maximize();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("loginemailtextfield")));
	    driver.findElement(By.className("loginemailtextfield")).sendKeys(dummy.getEmail());
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("loginpasswordtextfield")));
	    driver.findElement(By.className("loginpasswordtextfield")).sendKeys(dummy.getPassword());
	    Thread.sleep(100);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("loginbutton")));
	    driver.findElement(By.className("loginbutton")).click();
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logoutbutton")));
	    driver.findElement(By.className("logoutbutton")).click();
	    try {
			UserDAO.getInstance().delete(dummy.getEmail());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

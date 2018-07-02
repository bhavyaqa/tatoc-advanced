package com.qainfotech.automation.tatoc_advanced;

import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {
	App app = new App();
	WebDriver driver = app.driver;

	@BeforeClass
	public void launch_the_browser() {
		driver.get("http://10.0.1.86/tatoc/");
	}

	@Test
	void Test01_is_it_opening_advanced_course() {
		app.openAdvancedCourse();
		Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().equals("Hover Menu"));
	}

	@Test
	void Test02_clicking_go_next_of_hover_menu() {
		app.HoverMenu2();
	}
	@Test
	void Test03_adding_credentials_to_db_query() throws ClassNotFoundException, SQLException {
		app.QueryGate();
	}
	//@AfterClass
	void close_the_browser() {
		driver.quit();
	}
}
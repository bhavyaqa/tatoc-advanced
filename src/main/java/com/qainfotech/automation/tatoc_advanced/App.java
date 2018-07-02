package com.qainfotech.automation.tatoc_advanced;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class App {
	WebDriver driver;

	App() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\bhavyakhanna\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	void openAdvancedCourse() {
		driver.findElement(By.xpath("//a[2]")).click();
	}

	void HoverMenu2() {
		Actions action = new Actions(driver);
		WebElement menu2 = driver.findElement(By.cssSelector(".page div.menutop"));
		Action moveToMenu2 = action.moveToElement(menu2).build();
		moveToMenu2.perform();
		driver.findElement(By.cssSelector(".menutop  span:nth-child(5)")).click();

	}

	void QueryGate() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String dburl = "jdbc:mysql://10.0.1.86/tatoc";
		String username = "tatocuser";
		String password = "tatoc01";
		String displayedsymbol = driver.findElement(By.id("symboldisplay")).getText();
		Connection con = DriverManager.getConnection(dburl, username, password);
		Statement smt = con.createStatement();
		String query ="SELECT id from identity WHERE symbol = \""+displayedsymbol+"\";";
		ResultSet resultSet = smt.executeQuery(query);
		String id = null ;
		String name = null;
		String passkey = null;
		while (resultSet.next()) {
			 id = resultSet.getString(1);
		}
		String query2 = "SELECT name from credentials WHERE id = \""+id+"\"";
		ResultSet resultset2 = smt.executeQuery(query2);
		while (resultset2.next()) {
			name = resultset2.getString(1);
		}
		String query3 = "SELECT passkey from credentials WHERE name =\""+name+"\";";
		ResultSet resultset3 = smt.executeQuery(query3);
		while (resultset3.next()) {
			passkey = resultset3.getString(1);
		}
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("passkey")).sendKeys(passkey);
		driver.findElement(By.id("submit")).click();
	}

}

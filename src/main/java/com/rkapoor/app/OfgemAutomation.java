package com.rkapoor.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;


public class OfgemAutomation {
	public static void main(String[] args) throws Exception {
			System.setProperty("webdriver.chrome.driver", "C:\\Apps\\chromedriver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.gmail.com");
			driver.findElement(By.id("identifierId")).sendKeys("rohanofgem", Keys.ENTER);
			Thread.sleep(3000);
			driver.findElement(By.name("password")).sendKeys("Automation1001", Keys.ENTER);
			Thread.sleep(4000);

			List<WebElement> emails = driver.findElements(By.className("zE"));
			
			List<MailDetails> mds = new ArrayList<>();
			Map<MailDetails, WebElement> mdToWeMap = new HashMap<>();

			System.out.println("Mail count: " + emails.size());

			for(int i=0;i<emails.size();i++){
				
				WebElement we = emails.get(i);
				
				RemoteWebElement rwe = (RemoteWebElement) we;
				
				
				MailDetails md = MailDetails.from(we.getText());
				mds.add(md);
				
				mdToWeMap.put(md, we);
				
			}
			
			System.out.println("Mail details");
			System.out.println("============");
			mds.stream().forEach(md -> System.out.println(md));
			
			MailDetails mdLatest = MailDetails.latestEmail(mds);
			
			System.out.println("Latest Mail details");
			System.out.println("===================");
			System.out.println(mdLatest);
			
			Thread.sleep(4000);
			
			mdToWeMap.get(mdLatest).click();

			Thread.sleep(4000);

			// Find the delete button
			WebElement groupElement = driver.findElement(By.xpath("//div[@class='iH bzn']//div[@class='G-tF']//div[2][@class='G-Ni G-aE J-J5-Ji']"));
			Actions action = new Actions(driver);
			action.moveToElement(groupElement).build().perform();
			WebElement deleteButton = driver.findElement(By.xpath("//div[@aria-label='Delete']//div[@class='asa']"));
			
			//Click the delete button
			deleteButton.click();
			
			Thread.sleep(4000);
			
	}
}

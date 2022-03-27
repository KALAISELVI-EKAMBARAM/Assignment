package Basic;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewTest {
	@Test
	public void todoList() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");

		// Setting the driver to chrome driver
		WebDriver driver = new ChromeDriver();
		String url = "https://todomvc.com/examples/angularjs/#/";
		driver.get(url);
		// Capturing the title and validating if expected is equal to actual
		String expectedTitle = "AngularJS • TodoMVC";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Page title is : " + driver.getTitle());

		// Find element and assign in webelement
		WebElement ToDo = driver.findElement(By.xpath("//form[@ng-submit='addTodo()']/input"));

		/* ToDo.clear(); */
		ToDo.sendKeys("Drink Water");
		ToDo.submit();
		ToDo.sendKeys("Eat Food");
		ToDo.submit();
		ToDo.sendKeys("Selenium");
		ToDo.submit();
		ToDo.sendKeys("Python");
		ToDo.submit();
		ToDo.sendKeys("Code");
		ToDo.submit();
		System.out.println("To-do List");
		System.out.println("Add to-do is working");
		// validating text entered or not?
		// List<WebElement> ToDoList1=
		// driver.findElement(By.xpath("//ul[@class='todo-list']"));
		WebElement toDoList1 = driver.findElement(By.xpath("//ul[@class='todo-list']"));

		List<WebElement> list1 = toDoList1.findElements(By.tagName("li"));
		for (WebElement element : list1) {
			String attribute = element.getText();
			if (attribute.contains("Drink Water")) {
				System.out.println("Searched item is present in the list");
			}

//			else {
//				System.out.println("Exact Data not entered");
//			}
		}

		// Marking an item as complete works
		int i = 1;
		for (WebElement element1 : list1) {
			String attribute = element1.getText();
			if (attribute.contains("Drink Water")) {
				String count = driver.findElement(By.xpath("//span[@class='todo-count']/strong")).getText();
				int countexpect = Integer.parseInt(count);
				int countexpect1 = countexpect - 1;
				String expectedCount = Integer.toString(countexpect1);
				// System.out.println(i);
				// System.out.println(expectedCount);
				driver.findElement(By.xpath("(//ul[@class='todo-list']/li/div/input)[" + i + "]")).click();
				
				if (driver.findElement(By.xpath("(//ul[@class='todo-list']/li/div/label)[" + i + "]")).isEnabled()) {
					System.out.println("Completed item is enabled");
				} else {
					System.out.println("Completed item is disabled");
				}
				String count1 = driver.findElement(By.xpath("//span[@class='todo-count']/strong")).getText();
				Assert.assertEquals(count1, expectedCount);
				System.out.println("Marking an item as complete is working");
			}

//		  else
//		  {
//		  System.out.println("attribute");
//		  }
			i++;
		}
		// Delete a to-do
		WebElement toDoList2 = driver.findElement(By.xpath("//ul[@class='todo-list']"));

		List<WebElement> list2 = toDoList2.findElements(By.tagName("li"));
		int k = 1;
		for (WebElement element2 : list2) {
			String attribute1 = element2.getText();
			// System.out.println(element2.getText());
			if (attribute1.contains("Python")) {
				String count = driver.findElement(By.xpath("//span[@class='todo-count']/strong")).getText();
				int countexpect = Integer.parseInt(count);
				int countexpect1 = countexpect - 1;
				String expectedCount = Integer.toString(countexpect1);
				driver.findElement(By.xpath("(//ul[@class='todo-list']/li/div/input)[" + k + "]")).click();
				driver.findElement(By.xpath("(//button[@class='destroy'])[" + k + "]")).click();
				String count1 = driver.findElement(By.xpath("//span[@class='todo-count']/strong")).getText();
				Assert.assertEquals(count1, expectedCount);
				k--;
				System.out.println("Delete todo item is working");
				break;
				
			}

//		  else
//		  {
//		  System.out.println("attribute");
//		  }
			k++;
		}
		WebElement toDoCompleteList = driver.findElement(By.xpath("//ul[@class='todo-list']"));
		List<WebElement> Completelist = toDoCompleteList.findElements(By.tagName("li"));
		int Totalcountexpect = Completelist.size();
		
		WebElement Active = driver.findElement(By.xpath("(//ul[@class='filters']//a)[2]"));
		Active.click();
		WebElement toDoListActive = driver.findElement(By.xpath("//ul[@class='todo-list']"));

		List<WebElement> listActive = toDoListActive.findElements(By.tagName("li"));
		int ActiveNo=listActive.size();
		String count = driver.findElement(By.xpath("//span[@class='todo-count']/strong")).getText();
		int Activecountexpect = Integer.parseInt(count);
		Assert.assertEquals(ActiveNo, Activecountexpect);
		System.out.println("Active Filtering is working");
		int ExpectedCompletedCount = (Totalcountexpect) - (Activecountexpect);
		WebElement Completed = driver.findElement(By.xpath("(//ul[@class='filters']//a)[3]"));
		Completed.click();
		WebElement toDoListComplete = driver.findElement(By.xpath("//ul[@class='todo-list']"));
		List<WebElement> listComplete  = toDoListComplete.findElements(By.tagName("li"));
		int CompleteNo=listComplete.size();
		Assert.assertEquals(ExpectedCompletedCount, CompleteNo);
		System.out.println("Completed Filtering is working");
		driver.quit();

	}
}

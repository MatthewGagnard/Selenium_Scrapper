import javax.swing.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;



public class PriceCompare {
public static Scanner input = new Scanner(System.in);
	public static void main(String[] args) throws InterruptedException{
	   //Setup Selenium Driver
		System.setProperty("webdriver.chrome.driver", "My Driver"); //TODO Set users local path to Chromedriver in place of "My Driver".
	   ChromeOptions options=new ChromeOptions();
	   options.addArguments("--headless");
	   //Run in headless mode
	   WebDriver driver = new ChromeDriver(options);
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	   // Set UI for initial questions
	   JFrame firstFrame=new JFrame();
	   JTextField firstText = new JTextField("Select search options:");
	   JButton peramsButton = new JButton("Perameters!");
	   JButton cheapButton = new JButton("Find cheapest!");
	   JButton compareButton = new JButton("Comapre!");
	   peramsButton.setBounds(230,150,115,30);
	   cheapButton.setBounds(105,150,125,30);
	   compareButton.setBounds(0,150,115,30);
	   firstText.setBounds(85,100,150,50);
	   firstFrame.add(firstText);
	   firstFrame.add(peramsButton);
	   firstFrame.add(cheapButton);
	   firstFrame.add(compareButton);
	   firstFrame.setSize(350,350);
	   firstFrame.setLayout(null);
	   firstFrame.setVisible(true);
	   cheapButton.addActionListener(e->{
		   findCheapest(driver);
		   firstFrame.dispose();
		   });
	   compareButton.addActionListener(e->{
		   compareTo(driver);
		   firstFrame.dispose();
	   });
	   peramsButton.addActionListener(e->{
		   findWithPerams(driver);
		   firstFrame.dispose();
		   });
	 
	   firstFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   firstFrame.setVisible(true);

	}
	
	
	
	
	
	public static void compareTo(WebDriver driver)
	{
		   //Navigate to amazon to search prices
		   driver.get("https://amazon.com");
		   WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox")); //Find search Bar
		   //Prompt user input for searched item.	   
		   String keys = JOptionPane.showInputDialog(null, "What Item do you want to compare?");
		   searchBar.sendKeys(keys);
		   searchBar.submit();
		   String comparedPrice = JOptionPane.showInputDialog(null,"Please input price to be compared:");
			int comparedPriceint = Integer.parseInt(comparedPrice);
		   List<WebElement> price = driver.findElements(By.className("a-price-whole")); 
		  
		   // Iterate through the elements list to find the cheapest product.
		   WebElement temp3;
		   WebElement cheapestProduct = driver.findElement(By.className("a-price-whole"));
		   String temp;
		   int temp2=0;
		   int decider=comparedPriceint;
		   boolean hasChanged=false;
		   for(WebElement nextPrice : price)
		   {
			   temp3 = nextPrice;
			   temp = nextPrice.getText();
			   if(!temp.isEmpty())
			   {
				   				temp2 = Integer.parseInt(temp);
				   				if(decider!=0 && temp2<decider)
				   					{
				   					cheapestProduct=temp3;
				   					decider=temp2;
				   					hasChanged=true;
				   					}
				   				else if(decider==0 && temp2 ==0)
				   				{
				   				
				   				}
				   				else if(decider==0)
				   				{
				   					cheapestProduct=temp3;
				   					decider=temp2;
				   				}
				   				
			   }
		   }
		   if(hasChanged) {
		   WebElement link = cheapestProduct.findElement(By.xpath("../../..")); //Find link of cheapest product element
		  // Create and set dimensions for outcome UI
		   JFrame F=new JFrame();
		   JTextField jf = new JTextField("We found a cheaper price!");
		   String linkText = link.getAttribute("href").toString();
		   JButton button = new JButton("OK!");
		   JButton linkButton = new JButton("Go to Link");
		   linkButton.setBounds(175,150,95,30);
		   button.setBounds(80,150,95,30);
		   jf.setBounds(100,100,150,50);
		   F.add(button);
		   F.add(jf);
		   F.add(linkButton);
		   F.setSize(350,350);
		   F.setLayout(null);
		   F.setVisible(true);
		   WebDriver linkDriver = new ChromeDriver(); //Open driver outside of "Headless" mode, so user can see.
		 	// Set button actions
		   linkButton.addActionListener(e->{
			  
			   linkDriver.get(linkText);
			   });
		   button.addActionListener(e->{
			   F.dispose();
			   linkDriver.quit();
			   });
		   F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   F.setVisible(true);
		   }
		   else {
			   
		   JFrame F=new JFrame();
		   JTextField jf = new JTextField("You have the best price!");
		   JButton button = new JButton("OK!");
		   button.setBounds(125,150,95,30);
		   jf.setBounds(100,100,150,50);
		   F.add(button);
		   F.add(jf);
		   F.setSize(350,350);
		   button.addActionListener(e->{
			   F.dispose();
			   });
		   F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   F.setLayout(null);
		   F.setVisible(true);
		   }
		   driver.quit();
	}
	
	
	public static void findWithPerams(WebDriver driver)
	{
		   //Navigate to amazon to search prices
		   driver.get("https://amazon.com");
		   WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
		   //Prompt user input for searched item.	
		   String keys = JOptionPane.showInputDialog(null, "What item are you searching for?");
		   searchBar.sendKeys(keys);
		   searchBar.submit();
			//Get star rating perameters: If not availble will continue without 
		   try{
		   String starKey = JOptionPane.showInputDialog(null, "What is your minimum star rating? Select a number 1-4");
				   if(Integer.parseInt(starKey)==4)
				   {
					   WebElement fourStar = driver.findElement(By.xpath("//section[@aria-label='4 Stars & Up']/.."));
					   fourStar.click();
				   }
				   else if(Integer.parseInt(starKey)==3)
				   {
					   WebElement fourStar = driver.findElement(By.xpath("//section[@aria-label='3 Stars & Up']/.."));
					   fourStar.click();
				   }
				   else if(Integer.parseInt(starKey)==2)
				   {
					   WebElement fourStar = driver.findElement(By.xpath("//section[@aria-label='2 Stars & Up']/.."));
					   fourStar.click();
				   }
				   else if(Integer.parseInt(starKey)==1)
				   {
					   WebElement fourStar = driver.findElement(By.xpath("//section[@aria-label='1 Stars & Up']/.."));
					   fourStar.click();
				   }
				   else
				   {
					   System.out.println("Number entered did not match expected, continuing without min star requirement");
				   }
		   }
		   catch(NoSuchElementException e)
		   {
			   System.out.println("Rating peramter set could not be found. Continuing without.");
		   }
		   // Set perameters for minimum and maximum prices
		   String lowpriceKey = JOptionPane.showInputDialog(null, "What is your minimum price?");
		   String highpriceKey = JOptionPane.showInputDialog(null, "What is your Maximum price?");
		   try {
		   WebElement lowPrice = driver.findElement(By.xpath("//input[@id='low-price']"));
		   WebElement highPrice = driver.findElement(By.xpath("//input[@id='high-price']"));
		   lowPrice.sendKeys(lowpriceKey);
		   highPrice.sendKeys(highpriceKey);
		   highPrice.submit();
		   }
		   catch(NoSuchElementException e)
		   {System.out.println("High / Low price selection not availble for this product"); //Catch instances of unavilble price matching
		   }
		  // Find Cheapest with submitted PERAMS
		   List<WebElement> price = driver.findElements(By.className("a-price-whole")); 
		   // Iterate through the elements list to find the cheapest product.
		   WebElement temp3;
		   WebElement cheapestProduct = driver.findElement(By.className("a-price-whole"));
		   String temp;
		   int temp2=0;
		   int decider=0;
		   int height = Integer.parseInt(highpriceKey);
		   int depth = Integer.parseInt(lowpriceKey);
		   for(WebElement nextPrice : price)
		   {
			   temp3 = nextPrice;
			   temp = nextPrice.getText();
			   if(!temp.isEmpty())
			   {
				   				temp2 = Integer.parseInt(temp);
				   				if(decider!=0 && temp2<decider && temp2<=height && temp2>=depth)
				   					{
				   					cheapestProduct=temp3;
				   					decider=temp2;
				   					}
				   				else if(decider==0 && temp2 ==0)
				   				{
				   				
				   				}
				   				else if(decider==0 && temp2<=height && temp2>=depth)
				   				{
				   					cheapestProduct=temp3;
				   					decider=temp2;
				   				}
			   
			   }
		   }
		   WebElement link = cheapestProduct.findElement(By.xpath("../../..")); // Navigate and store link for found product
		   //Set UI for output
		   JFrame F=new JFrame();
		   JTextField jf = new JTextField("  We found the something for you!");
		   String linkText = link.getAttribute("href").toString();
		   JButton button = new JButton("OK!");
		   JButton linkButton = new JButton("Go to Link");
		   linkButton.setBounds(175,150,95,30);
		   button.setBounds(80,150,95,30);
		   jf.setBounds(75,100,200,50);
		   F.add(button);
		   F.add(jf);
		   F.add(linkButton);
		   F.setSize(350,350);
		   F.setLayout(null);
		   F.setVisible(true);
		   WebDriver linkDriver = new ChromeDriver();
		   linkButton.addActionListener(e->{
			  
			   linkDriver.get(linkText);
			   });
		   button.addActionListener(e->{
			   F.dispose();
			   linkDriver.quit();
			   });
		   F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   F.setVisible(true);
		   driver.quit();
	}
	
	
	  public static void findCheapest(WebDriver driver) 
	  {
	   //Navigate to amazon to search prices
	   driver.get("https://amazon.com");
	   WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
	   //Prompt user input for searched item.	   
	   String keys = JOptionPane.showInputDialog(null, "What item are you searching for?");
	   searchBar.sendKeys(keys);
	   searchBar.submit();
	   List<WebElement> price = driver.findElements(By.className("a-price-whole")); 
	  
	   // Iterate through the elements list to find the cheapest product.
	   WebElement temp3;
	   WebElement cheapestProduct = driver.findElement(By.className("a-price-whole"));
	   String temp;
	   int temp2=0;
	   int decider=0;
	   for(WebElement nextPrice : price)
	   {
		   temp3 = nextPrice;
		   temp = nextPrice.getText();
		   if(!temp.isEmpty())
		   {
			   				temp2 = Integer.parseInt(temp);
			   				if(decider!=0 && temp2<decider)
			   					{
			   					cheapestProduct=temp3;
			   					decider=temp2;
			   					}
			   				else if(decider==0 && temp2 ==0)
			   				{
			   				
			   				}
			   				else if(decider==0)
			   				{
			   					cheapestProduct=temp3;
			   					decider=temp2;
			   				}
		   
		   }
	   }
	   WebElement link = cheapestProduct.findElement(By.xpath("../../..")); //Navigate to link of cheapest product element
		//Set up UI for cheapest product	  
	   JFrame F=new JFrame();
	   JTextField jf = new JTextField("  We found the something for you!");
	   String linkText = link.getAttribute("href").toString();
	   JButton button = new JButton("OK!");
	   JButton linkButton = new JButton("Go to Link");
	   linkButton.setBounds(175,150,95,30);
	   button.setBounds(80,150,95,30);
	   jf.setBounds(75,100,200,50);
	   F.add(button);
	   F.add(jf);
	   F.add(linkButton);
	   F.setSize(350,350);
	   F.setLayout(null);
	   F.setVisible(true);
	   WebDriver linkDriver = new ChromeDriver();
	   linkButton.addActionListener(e->{
		  
		   linkDriver.get(linkText);
		   });
	   button.addActionListener(e->{
		   F.dispose();
		   linkDriver.quit();
		   });
	   F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   F.setVisible(true);
	   driver.quit();
	  }
	

}

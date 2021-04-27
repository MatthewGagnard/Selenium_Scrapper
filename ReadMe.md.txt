Matthew Gagnard
Selenium WebScraper

# Overview:
 - The main goal of the webscraper project is to familiarize myself with web based automation, HTML, and basic User Interface 
  
 - Selenium WebScraper, using Amazon.com as the target, takes user inputs to search through and find the cheapest options for a desired product, compare with perameters
	or compare a price.

 - Webscrapper will find and link you too the cheapest availble version of whatever product your looking for.

# How To Use
 	- Requirements: Java IDK, Most recent Selenium JAR, Selenium ChromeDriver for CHROME 90.0.4430.85 (Most recent Build)

	- Set users ChromeDriver local file location in line 21 in place of "My Driver"

	- On compilation user is prompted with three buttons
		
		- Select "Perameters" to search for a product with set minimum star ratings, minimum price and maximum price

		- Select "Find Cheapest" to find the cheapest available options of desired product

		- Select "Compare!" to compare your price for an object to prices found


	- Text boxes will prompt user for needed inputs.

	- After all inputs have been collected, a window will appear with what the scrapper found. 
		
		- The "OK!" button in all cases will close out the application

		- The "Go to link!" button will open up an instance of Google chrome and send users directly to the product that the scrapper found


# Bugs / Faults / Developments 

	#Bugs
	- No current known bugs
	- CAUTION: Rigorous edge case testing has not been completed 


	# Faults
	- Scrapper is not currently equipped to hand decimal places for numbers, all prices are rounded down.

		- Scrapper is dependent on Amazon built in search:

		- Misspelling may result in incorrect products

		- Vague searches may result in cheap products meant to add on to desired product.

	# Developments
	- Multi-site comparison
	- Full Price comparison: Compare based off of cents as well as Dollar amount
	- Continued GUI primping
		- More presentable User interfaces
	- Multi-Option product returns
		- Set multiple perameter sets and return products based off each set

	







  
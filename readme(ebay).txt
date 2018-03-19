To run these EbayTest you need to specify paths to the respective browser drivers in
EbaySuite.xml in parameters driverpath.

After that you can build project using command
	
	gradlew.bat testClasses

And then run tests using command
	
	java org.testng.TestNG EbaySuite.xml

Or, alternatively you can run:

	gradlew.bat test

To build and run tests simultaneously.	
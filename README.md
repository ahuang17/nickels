# Nickels Coding Challenge
## Description
My original goal with this challenge was to create an application in Spring Boot, and from there make a CLI that would make API calls to do tasks for the challenge. 

## Flow
The flow is that customers can add the money they have (addMoney method), while being able to check their money any time during the process (checkWallet method). They then have the option to either check the number of ways they can pay the CTA Ride Fare (checkFareWays method) or get the exact ways they can pay the fare (checkFare method). The user can also reset the wallet (emptyWallet method) to enter a different amount of money. The methods and API calls used can be found in complete/src/main/java/com/cta/CtaController.java. 

## Incomplete Elements.
Currently the checkFare method is not working, and will always return that there is no possible way to pay the fare. This is reflected in the unit tests I included, with the rest of the methods working but the test testing the checkFare method failing. Due to time, I was also unable to finish the CLI which would make the Spring Boot API calls. Ideally, the user would open up the application, and be prompted to enter the currency they have, and have options to check their wallet, and enter fare amounts to check what ways they can pay it. 

## Testing
Unit tests are included, and can be found in complete/src/test/java/com/cta/CtaControllerTest.java. All the API calls are tested, with all tests successful except for one (the checkFare I mentioned earlier). To run the tests, Go to the 'complete' directory in cmd line and run 'gradlew test'. 

## How to Run
 1. Go the 'complete' directory in cmd line and run 'gradlew bootRun' to start the application.
 2. Open a separate cmd line, from here you can run the api calls. 
 3. Api calls can be ran using the curl call 'curl localhost:8080' and then with the desired path added on. For example, to check wallet you would enter 'curl localhost:8080/checkWallet' in cmd line. 

 ## Example
 1. Run 'gradlew test' to test if code is working. 
 2. Start up application with 'gradlew bootRun'.
 3. Open separate cmd line.
 4. Run 'localhost:8080/addMoney/3,2,2,1' to add 3,2,2,1 currency to user wallet. 
 5. Run 'localhost:8080/checkWallet' to see that there is 3,2,2,1 in user wallet. 
 6. Run 'localhost:8080/runFareWays/4' to see there is 2 ways to pay the fare. 
 7. Run 'localhost:8080/runFare/4' to see that the fare can be paid with (3,1) or (2,2) (currently not working and will instead say there is no way to pay fare). 
# TechicalTest
Project's Title:
Crypto site UI and API validation

Project Description:
Compare market trend with and without filters
Convert coins to currency ,check coins purchased for certain amount

Project Setup:
Steps followed for creating this project

1) Create a new maven project
2) Add the necessary POM dependencies junit,selenium-java, selenium-jvm-deps,cucumber-java,cucumber-junit,httpcore,httpclient,json
3) Create test scenarios using feature file
4) Create Test Runner to run with cucumber by including path to features, step definitions,monochrome as true and reports plugin path
5) Setup step bindings file to match with the feature steps
6) Implement Page object Model using @FindBy annotations to identify webelements
7) Implement page factory to initialise page elements and work on its methods and use page object manager to control the action flow
8) Create selenium utilities(reusables), configuration file to store static values
9) Execute test as Junit test and get html reports which uses the extent-config xml template
10) UI scripts to capture screensots at the end of each step and close browser after quit are managed using annotation class

How to Use the Project:
Run the project to capture reports. Check through the html reports for pass/fail with screenshots, time taken for execution










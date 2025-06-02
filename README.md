# Introduction

This repository contains automated REST API tests for the Purgomalum profanity filtering service.

This project contains REST API tests written in Java using the JUnit framework, built with Maven, and leveraging the REST-assured library for HTTP requests.

# Running the Tests
- Follow these steps to execute the automation test suite from the command line:

# Clone this repository to your local machine
- git clone https://github.com/virenkpatel108/Chip-Test.git

# Navigate to the project directory:
- cd purgomalum-viren

# Execute the following Maven commands to run the tests:

*To run all tests:*
- mvn clean test

OR

- mvn test

*To run a specific test class:*
- mvn -Dtest=com.purgomalum.methods.ProfanityTest test

*To run a specific test method:*
- mvn -Dtest=com.purgomalum.methods.ProfanityTest#testMethodName test

# Expected Test Results
```
{
    "result": "This is *** sentence"
}
{
    "result": "***** keep away doctor"
}
{
    "result": "This is [BLOCKED] sentence"
}
{
    "result": "Ohh *********"
}

```

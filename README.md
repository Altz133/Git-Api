# Git-Api
### Description
The Git-Api is a Java Spring REST API program designed to interact with the official GitHub API [GitHub RestAPI].
Its main purpose is to retrieve information about repositories based on a provided GitHub username.
The API exposes a single endpoint that returns a list of repositories, including details such as the owner's username, all branch names, and the last commit SHA.
Also, it excludes any repositories that are identified as forks.


```
localhost:8080/api/{username}
```
 
 It also contains basic exception handlig:
 * preventing from providing GitHub username that is non-existent,
 * preventing from sending a "accept:application/xml" header with request;


 ### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- Git

GitHub OAuth access token (to generate one go to: github account settings -> developer settins -> personal access tokens ->Tokens(classic))
Token have to be placed inside "token.txt" file in root direcotry.

### Clone the Repository

Clone the repository to your local machine using the following command:
```
git clone https://github.com/Altz133/Git-Api.git
```
### Build the Project

Navigate to the project directory and build the application using Maven:
```
cd Git-api
mvn clean install
```

### Run the Application

To run the application, use the following command:
```
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### Access endpoint

Open your web browser and access the application at `http://localhost:8080/api/{GitHub_username}`.

[GitHub RestAPI]: http://slashdot.org](https://docs.github.com/en/rest?apiVersion=2022-11-28

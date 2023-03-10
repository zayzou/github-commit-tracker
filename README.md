# Commit Tracker App

## Overview
Commit Tracker App is a Java Spring Boot application that tracks the number of commits made by a user on their GitHub repository. It fetches the number of commits every day at 20:00 UTC, sends an email containing the count, and also posts a tweet containing the count on the user's Twitter account.

## Technical Details
The application uses the following technologies:

- Java 11
- Spring Boot 2.5.4
- OkHttp 4.9.1
- Jackson 2.13.0
- JavaMail 1.6.2
- Twitter4j 4.0.7

### Installation
To run the application locally, you need to have Java 11 and Maven installed on your machine.

1. Clone the repository to your local machine.
2. Open the project in your favorite IDE.
3. Set the environment variables `GITHUB_USERNAME`, `GITHUB_TOKEN`, `EMAIL_USERNAME`, `EMAIL_PASSWORD`, `TWITTER_CONSUMER_KEY`, `TWITTER_CONSUMER_SECRET`, `TWITTER_ACCESS_TOKEN`, and `TWITTER_ACCESS_SECRET`.
4. Build the project using `mvn clean install`.
5. Run the application using `mvn spring-boot:run`.

### Configuration
The application configuration is stored in the `application.properties` file. You can modify the values to customize the application behavior.

### Logging
The application logs can be found in the `logs/commit-tracker-app.log` file. The log file is rolled over daily, and a maximum of 30 days' worth of logs are kept.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)

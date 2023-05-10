# GitHub Commit Tracker


![Spring Logo](https://spring.io/img/spring-2.svg)

## Introduction

GitHub Commit Tracker is a Java Spring Boot application that tracks the number of commits made by a user on GitHub and sends the count via email and Telegram bot. It uses the GitHub API to fetch the number of commits made and the JavaMail API and Telegram bot API to send an email and message respectively.

## Technical Details
NOW I AM WORKING ON A GRAPHQL IMPLEMENTATION.
The application is built using Java Spring Boot and uses the following dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Test
- OkHttp
- Gson
- Jsoup

### Configuration

To use this application, you will need to provide the following configuration properties in `application.properties`:

- `github.username`: The GitHub username of the user to track commits for.
- `github.token`: The GitHub API token to authenticate with.
- `telegram.bot.token`: The Telegram bot API token.
- `telegram.chat.id`: The Telegram chat ID to send the message to.

### How to Use

To run the application, clone the repository and run the following command:


The application will run and fetch the number of commits made by the user at 20:00 UTC every day. It will then send an email and message containing the count.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

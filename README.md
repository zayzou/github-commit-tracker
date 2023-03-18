# GitHub Commit Tracker

![Spring Logo](https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg)

## Introduction

GitHub Commit Tracker is a Java Spring Boot application that tracks the number of commits made by a user on GitHub and sends the count via email and Telegram bot. It uses the GitHub API to fetch the number of commits made and the JavaMail API and Telegram bot API to send an email and message respectively.

## Technical Details

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
- `email.from`: The email address to send the email from.
- `email.to`: The email address to send the email to.
- `email.password`: The email account password.
- `telegram.bot.token`: The Telegram bot API token.
- `telegram.chat.id`: The Telegram chat ID to send the message to.

### How to Use

To run the application, clone the repository and run the following command:


The application will run and fetch the number of commits made by the user at 20:00 UTC every day. It will then send an email and message containing the count.

## Acknowledgments

This application was inspired by the GitHub Commit Tracker project by Soffi Zahir (https://github.com/Soffi-Zahir/github-commit-tracker).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

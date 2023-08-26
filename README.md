# spring-github-repo-tracker-app
 A Spring Boot application that allows you to list non-fork repositories of a given GitHub user. Retrieve information about their branches and the last commit SHA for each branch. Explore GitHub repositories effortlessly!
## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- Retrieve a list of non-fork repositories for a GitHub user.
- Display repository name, owner login, and branches with their last commit sha.
- Handle errors gracefully and return appropriate response codes and messages.

## Prerequisites

- Java JDK 17
- Spring Boot 3
- Maven

## Installation

1. Clone the repository:

```bash
git clone https://github.com/Damian1457/spring-github-repo-tracker-app.git
cd spring-github-repo-tracker-app
```

2. Build the application:

```bash
mvn clean install
```

## Usage
To run the application, open your terminal and execute the following command:

```bash
mvn spring-boot:run
```
Open your browser or use a tool like Postman to make requests to the API endpoints.

## API Endpoints

### GET /api/repositories/{username}

Retrieve a list of non-fork repositories for the specified GitHub username.

#### Example Request

```bash
GET /api/repositories/{username}
```

#### Example Response

```json
[
  {
    "name": "repo1",
    "ownerLogin": "user1",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "abcdef123456"
      }
    ]
  },
  // ... other repositories
]
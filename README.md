# Sales Campaigns Manager

This is a web application designed for managing sales campaigns for various products. The backend is built using Spring Boot with JWT for security and React on the frontend. The backend handles user authentication, campaign management, and product listing, with an SQL H2 database. The frontend uses Bootstrap, JS-Cookies, and DOM manipulation to manage sessions and display data.

## Table of Contents
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Technologies Used](#technologies-used)

## Getting Started

To get started with this project, clone the repository and follow the instructions below.

### Prerequisites

- Java 11 or higher
- Node.js and npm
- Maven

### Backend Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dminior8/SalesCampaingsManager.git
   cd your-repository/backendSCM
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
4. Access the backend of the application at http://localhost:8090 in your web browser.

### Frontend Setup

1. **Navigate to the frontend directory**:
   ```bash
   cd ../frontend
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Start the development server**:
   ```bash
   npm start
   ```
4. Access the frontend of the application at http://localhost:3030 in your web browser.

## Endpoints

Below is a table of the available API endpoints, their methods, and descriptions:

| Method | Endpoint                                   | Description                                                                           | Response                                         |
|--------|--------------------------------------------|---------------------------------------------------------------------------------------|--------------------------------------------------|
| POST   | `/api/auth/login`                          | Authenticates a user and returns a JWT token.                                          | JSON object with JWT token and user details.     |
| POST   | `/api/campaigns/add`                       | Adds a new campaign to the database. Requires JWT authentication.                      | JSON object with details of the added campaign.  |
| GET    | `/api/campaigns`                           | Retrieves a list of all campaigns. Requires JWT authentication.                        | JSON array of campaign objects.                  |
| GET    | `/api/campaigns/products`                  | Retrieves a list of all products associated with campaigns. Requires JWT authentication.| JSON array of product objects.                   |
| GET    | `/api/campaigns/products/{productId}`      | Retrieves details of a specific product by ID. Requires JWT authentication.            | JSON object with product details.                |
| GET    | `/api/user/current`                        | Retrieves details of the currently authenticated user. Requires JWT authentication.    | JSON object with user details.                   |
| PUT    | `/api/campaigns/edit`                      | Edits an existing campaign. Requires JWT authentication.                               | JSON object with updated campaign details.       |
| DELETE | `/api/campaigns/{campaignId}`              | Deletes a specific campaign by ID. Requires JWT authentication.                        | JSON object confirming deletion.                 |

## Technologies Used

- **Backend**:
  - Spring Boot
  - Spring Security (JWT Authentication)
  - H2 Database (SQL)
  - Maven

- **Frontend**:
  - React
  - Bootstrap
  - JS-Cookies
  - DOM Manipulation

## License
This project is licensed under the [MIT](https://github.com/dminior8/SalesCampaingsManager/blob/main/LICENSE) License. All modifications to the project must adhere to this license.

Author: Daniel Minior

Creation Date: 27 June 2024

Thank you for exploring Sales Campaigns Manager!

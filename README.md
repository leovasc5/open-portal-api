### Open Portal API

Open Portal API is an open-source project that provides a template for building a RESTful API to manage leads in a relational database. The API is built using Java and the Spring Boot Web framework.

---

#### Features

- **Lead Management**: Create, read, update, and delete leads in the relational database.
- **RESTful Endpoints**: Well-defined endpoints for interacting with the API.
- **Pagination**: Support for pagination to manage large sets of leads.
- **Validation**: Input data validation to ensure data integrity.
- **Security**: Basic authentication for API access.
- **Logging**: Logging of API requests and responses for monitoring.
- **Integration**:
  - **Email**: Send notifications via email for lead updates.
  - **Slack**: Notify users on Slack about new leads.
  - **WhatsApp**: Send WhatsApp messages to users for lead notifications.

---

#### Technologies Used

- **Java**: The primary programming language for the API.
- **Spring Boot Web**: A framework for building RESTful APIs with Java.
- **Spring Data JPA**: Simplifies data access using the Java Persistence API (JPA).
- **MySQL**: The relational database used for storing lead information.

---

#### Installation and Setup

1. **Clone the Repository**:
   ```shell
   git clone https://github.com/leovasc5/open-portal-api.git
   ```

2. **Navigate to Project Directory**:
   ```shell
   cd open-portal-api
   ```

3. **Build the Project**:
   ```shell
   ./mvnw clean install
   ```

4. **Run the Application**:
   ```shell
   ./mvnw spring-boot:run
   ```

5. **Access the API**:
   - Base URL: `http://localhost:8080/api/v1/resource`
   - Use your favorite REST client (e.g., Postman) to interact with the API.

---

#### API Endpoints

- **/api/v1/auth**: Sign-up and sign-in.
- **/api/v1/form**: CRUD.
- **/api/v1/user**: Read, Update and Delete.
- **/api/v1/log**: Create and Read.
- **/api/v1/image**: Create, Read and Delete.
- **/api/v1/city**: CRUD.
- **/api/v1/category**: CRUD.

Here you can see the endpoints documentation:

[Endpoints documentation](https://github.com/leovasc5/open-portal-api/blob/main/enpoints.md)

---

#### Configuration

- **Database**: Update `environment.properties` with your MySQL database configuration.
- **MySQL**: Run the files in `src/main/resources/database` and validate that everything is working.
- **JWT Token Expiration**: Update `environment.properties` with the secret and the expiration time (in milliseconds).
- **E-mail Sender**: Update `environment.properties` with the host, username and password of your e-mail sender.
- **Slack Sender**: Update `environment.properties` with the bearer token and channel code of the slack channel that receives notifications.
- **ImageBB API**: Update `environment.properties` with the API key of your image storage environment.
- **Front-end URL**: Update `environment.properties` if the front-end URL is needed.

---

#### Configuration

![diagram](https://github.com/leovasc5/open-portal-api/assets/70069239/6aa5e742-963e-4737-acb8-08cbceebc616)


---

#### Contributing

Contributions are welcome! Here's how you can contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature/new-feature`).
6. Create a new Pull Request to dev branch.

  Let's use the following flow:

![flux](https://github.com/leovasc5/open-portal-api/assets/70069239/0fe85cf8-5483-4918-8249-bc1ea471edea)

---

#### License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

#### Contact

For questions or feedback, please reach out to [leovasc5@hotmail.com](mailto:leovasc5@hotmail.com).

---

#### Acknowledgements

This project was inspired by the need for a simple, yet robust, RESTful API template for lead management.

Thank you for using Open Portal API! 🚀

---
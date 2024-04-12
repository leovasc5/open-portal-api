### API Documentation

#### Credentials
The endpoints can only be accessed with the bearer token in the authorization layer.

| Type        | Description                           |
| :---------- | :---------------------------------- |
| `Bearer token` | **Mandatory**. Can be achieved by accessing "api/v1/sign-in" |

### Auth

#### Sign Up

Creates a new user who can see the created leads.

```http
  POST /api/v1/auth/sign-up
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "name": "Leonardo Vasconcelos",
    "email": "leonardo.paulino@your.domain",
    "password": "123456",
    "phoneNumber": "5511912345678", // comes with the Brazilian format,
                                    // can be changed in the data model
    "isReceiver": true
  }
  ```

#### Sign In (User)

Authenticates a regular user.

```http
  POST /api/v1/auth/sign-in
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "email": "vitor.gsilva@your.domain",
    "password": "123456"
  }
  ```

#### Sign In (Admin)

Authenticates an admin user.

```http
  POST /api/v1/auth/sign-in
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "email": "leovasc5@your.domain",
    "password": "123456"
  }
  ```

---
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

The difference between the two requests is the response returned.

---

### Form

#### Create

Creates a new form.

```http
  POST /api/v1/form
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "authorName": "John Doe",
    "authorRole": "Sales Manager",
    "businessName": "His Company",
    "contactEmail": "john.doe@his.company",
    "description": "I need your company to do...",
    "cityOthers": {
        "name": "New York"
    },
    "categoryOthers": {
        "name": "Financial"
    }
    // other possibilities:
    "city": {
      "id": 1
    },
    "category": {
      "id": 1
    }    
  }
  ```

#### Read All

Returns all forms.

```http
  GET /api/v1/form
```

#### Read By ID

Returns a specific form by ID.

```http
  GET /api/v1/form/{id}
```

#### Delete

Deletes a form by ID.

```http
  DELETE /api/v1/form/{id}
```

#### Export to Excel

Export all forms for a XLSX file.

```http
  GET /api/v1/form/export-excel
```

---
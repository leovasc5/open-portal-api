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

### User

#### Read (Only Admin)

Returns all users.

```http
  GET /api/v1/user
```

#### Read By ID (Only Admin)

Returns a specific user by ID.

```http
  GET /api/v1/user/${id}
```

#### Update (Only Regular User)

Updates an user.

```http
  PUT /api/v1/user/{id}
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "name": "John Dow",
    "email": "john.doe@his.domain",
    "password": "123456",
    "phoneNumber": "5511912345678", // comes with the Brazilian format,
                                    // can be changed in the data model
    "isReceiver": true
  }
  ```

#### Delete By ID (Apenas Admin)

Deletes an user by ID.

```http
  DELETE /api/v1/user/{id}
```

---

### Log

#### Create

Creates a new log register.

```http
  POST /api/v1/log
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "dateTime": "2023-11-13T00:20:37"
  }
  ```

#### Read All

Returns all logs.

```http
  GET /api/v1/log
```

#### Read By ID

Returns a register of log specified by ID.

```http
  GET /api/v1/log/{id}
```

#### Delete

Deletes a register by ID.

```http
  DELETE /api/v1/log/${id}
```

---

### Image

#### Read By ID

Returns a specific image by ID.

```http
  GET /api/v1/image/2
```

#### Send Image

Send an image.

```http
  POST /api/v1/image
```

- **Headers**
  - `Content-Type: application/json`
  - `Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZW92YXNjNUBob3RtYWlsLmNvbSIsImlhdCI6MTcwMjI3MDMwOSwiZXhwIjoxNzAyMjcxNzQ5fQ.WkVq3MwczCv_8DT1PZppzsWsXU7VrXMBU9O6ZD_KQK4`

- **Body**
  ```json
  {
    "base64Image": "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
    "userId": 1
  }
  ```

#### Delete Image

Delete an image by ID.

```http
  DELETE /api/v1/image/2
```

---

### City

#### Read

Returns all cities.

```http
  GET /api/v1/city
```

#### Delete

Delete a city by ID.

```http
  DELETE /api/v1/city/39
```

#### Read All

Returns all cities.

```http
  GET /api/v1/city/list-all
```

#### Activate

Activates a city by ID.

```http
  PUT /api/v1/city/active/39
```

#### Other City

Creates a new city.

```http
  POST /api/v1/city/others
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "name": "Rio de Janeiro"
  }
  ```

---

### Category

#### Read

Returns all categories.

```http
  GET /api/v1/category
```

#### Delete

Deletes a category by ID.

```http
  DELETE /api/v1/category/39
```

#### Read All

Returns all categories.

```http
  GET /api/v1/category/list-all
```

#### Active

Activates a new category by ID.

```http
  PUT /api/v1/category/active/32
```

#### Other Category

Creates a new category.

```http
  POST /api/v1/category/others
```

- **Headers**
  - `Content-Type: application/json`

- **Body**
  ```json
  {
    "name": "Financial"
  }
  ```

---



---

```markdown
# 🛒 Simple E-commerce API – Spring Boot, JWT, MySQL

A complete **RESTful E-commerce API** built with **Spring Boot** and **MySQL**, featuring JWT authentication, role-based access (`ADMIN`, `CUSTOMER`), and support for products, cart, and orders.

---

## 🚀 Features

### 🔐 Authentication & Roles
- Register/Login using JWT
- Roles: `ADMIN` and `CUSTOMER`
- Role-based route protection using Spring Security

### 📦 Product Management
- Public product listing (pagination + search)
- Admin: Add, update, delete products

### 🛒 Cart Management
- Customers: Add, update, remove items
- View cart contents
- Cart is tied to authenticated user

### 🧾 Order System
- Place an order from the cart
- View order history
- Auto-clear cart on checkout

### 🌐 Basic Frontend (Optional)
- Simple HTML + JavaScript UI to interact with the API

---

## 🧑‍💻 Tech Stack

| Layer       | Technology           |
|-------------|----------------------|
| Language    | Java 17+             |
| Framework   | Spring Boot 3        |
| Security    | Spring Security + JWT |
| Database    | MySQL                |
| ORM         | Spring Data JPA      |
| Build Tool  | Maven                |
| Frontend    | HTML + JavaScript (optional) |

---

## 📁 Project Structure

ecommerce-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/ecommerce/
│   │   │   ├── controller/       # REST API controllers
│   │   │   ├── dto/              # Data Transfer Objects (Request/Response)
│   │   │   ├── entity/           # JPA Entities (User, Product, CartItem, Order, etc.)
│   │   │   ├── repository/       # Spring Data JPA Repositories
│   │   │   ├── service/          # Business logic
│   │   │   ├── security/         # JWT filters, auth, and configuration
│   │   │   └── config/           # CORS, default admin creation, etc.
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
├── frontend/
│   └── index.html                # Optional test frontend
│   └── script.js                 # Basic JavaScript for API calls
└── pom.xml

---

## ⚙️ Configuration

### 🔧 `application.properties`

```properties
# MySQL Config
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT Secret
jwt.secret=your_secret_key
jwt.expiration=3600000
````

---

## 📦 API Endpoints Summary

### 🧑 Auth

| Method | Endpoint             | Access | Description            |
| ------ | -------------------- | ------ | ---------------------- |
| POST   | `/api/auth/register` | Public | Register user          |
| POST   | `/api/auth/login`    | Public | Authenticate & get JWT |

---

### 📦 Products

| Method | Endpoint             | Role   | Description    |
| ------ | -------------------- | ------ | -------------- |
| GET    | `/api/products`      | Public | View products  |
| POST   | `/api/products`      | Admin  | Add product    |
| PUT    | `/api/products/{id}` | Admin  | Update product |
| DELETE | `/api/products/{id}` | Admin  | Delete product |

---

### 🛒 Cart

| Method | Endpoint                 | Role     | Description           |
| ------ | ------------------------ | -------- | --------------------- |
| GET    | `/api/cart`              | Customer | View user's cart      |
| POST   | `/api/cart`              | Customer | Add item to cart      |
| PUT    | `/api/cart/{cartItemId}` | Customer | Update quantity       |
| DELETE | `/api/cart/{cartItemId}` | Customer | Remove item from cart |

---

### 🧾 Orders

| Method | Endpoint      | Role     | Description      |
| ------ | ------------- | -------- | ---------------- |
| POST   | `/api/orders` | Customer | Place order      |
| GET    | `/api/orders` | Customer | View past orders |

---

## 👤 Default Admin (Auto-Created)

To manage products, use:

* **Email**: `admin@shop.com`
* **Password**: `admin123`

This is created on app startup if not present.

---

## 🧪 Testing Options

### Option 1: Postman or Swagger UI

* Send JWT token in `Authorization: Bearer <token>` header

### Option 2: Use Provided HTML Frontend

1. Go to `frontend/`
2. Open `index.html` in browser
3. Use buttons to register, login, view products, manage cart & orders

> 💡 Backend must run on `http://localhost:8080`

---

## 🧹 Completed Chapters

* ✅ Chapter 1: Project Setup & Dependencies
* ✅ Chapter 2: User Entity, Roles, JWT Auth
* ✅ Chapter 3: Product CRUD + Role-based Access
* ✅ Chapter 4: JWT Secured API Endpoints
* ✅ Chapter 5: Cart Management
* ✅ Chapter 6: Order Creation & History
* ✅ Chapter 7: Basic Frontend (HTML + JS)
* ✅ Chapter 8: Role-based Access & Backend Cleanup

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🙌 Support

If you found this helpful:

* ⭐ Star the repo
* 📨 Share it with friends
* 💬 Drop a comment or suggestion in Issues tab

---

```


```

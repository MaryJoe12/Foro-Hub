# Proyecto Java API REST - Gestión de Tópicos

Este proyecto es una API REST desarrollada en Java, que permite gestionar **tópicos** mediante operaciones CRUD, autenticación de usuarios, y documentación interactiva usando Swagger. La base de datos utilizada es **MySQL**.

---

## Funcionalidades

- Registro y autenticación de usuarios (con contraseñas hasheadas usando Bcrypt)
- CRUD de tópicos:
  - `GET /topicos`: obtener todos los tópicos con soporte para paginación y ordenamiento
  - `GET /topicos/{id}`: obtener un tópico por ID
  - `POST /topicos`: crear un nuevo tópico
  - `PUT /topicos/{id}`: actualizar un tópico existente
  - `DELETE /topicos/{id}`: eliminar un tópico
- Documentación Swagger disponible

---

## Tecnologías utilizadas

- **Java**
- **Spring Boot**
- **MySQL**
- **Spring Data JPA**
- **Spring Security**
- **Bcrypt**
- **Swagger (Springdoc OpenAPI)**

---

## 🧩 Estructura de la Base de Datos

### Tabla: `usuarios`

| Campo      | Tipo     | Restricciones       |
|------------|----------|---------------------|
| id         | INT      | PK, AUTO_INCREMENT  |
| nombre     | VARCHAR  | NOT NULL            |
| contraseña | VARCHAR  | NOT NULL (Bcrypt)   |

### Tabla: `topicos`

| Campo          | Tipo      | Restricciones      |
|----------------|-----------|--------------------|
| id             | INT       | PK, AUTO_INCREMENT |
| estado         | VARCHAR   | NOT NULL           |
| titulo         | VARCHAR   | NOT NULL           |
| mensaje        | VARCHAR   | NOT NULL           |
| autor          | VARCHAR   | NOT NULL           |
| curso          | VARCHAR   | NOT NULL           |
| fecha_creacion | DATETIME  | NOT NULL           |

---

## Seguridad y Autenticación

- Las contraseñas se almacenan hasheadas con **Bcrypt**.
- Los usuarios pueden iniciar sesión a través de un endpoint de autenticación (`/login` ).
- Acceso a los endpoints protegidos mediante token.
  
---

## Documentación Swagger

Una vez iniciada la aplicación, accede a la documentación interactiva en:
http://localhost:8080/swagger-ui.html

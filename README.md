# API - E-commerce

API REST para un sistema de e-commerce. Permite la gestión de usuarios, autenticación segura, productos y carrito de compras. Desarrollada con Java y Spring Boot, siguiendo una arquitectura en capas y un enfoque modular orientado a la escalabilidad y el mantenimiento.

Actualmente el proyecto está enfocado en el aprendizaje y la implementación de buenas prácticas backend, incluyendo seguridad, testing y organización del código.

## Tecnologías

- Java
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Lombok

## Seguridad y autenticación

- JWT (JSON Web Tokens)
- Filtros de seguridad con Spring Security
- Protección de rutas según autenticación
- Validación de datos (Spring Validation)

## Arquitectura

- Arquitectura en capas
- Separación de responsabilidades
- Uso de DTOs y mappers
- Manejo global de errores

## Estructura de carpetas

<pre>
src/main/java/com/ecommerce/
├── config/        # Configuración general (Security, etc.)
├── controllers/   # Controladores REST
├── dto/           # Objetos de transferencia de datos
├── mappers/       # Conversión entre entidades y DTOs
├── models/        # Entidades JPA
├── repositories/  # Acceso a base de datos
├── security/      # JWT, filtros y configuración de seguridad
├── services/      # Lógica de negocio
├── common/        # Manejo global de errores y utilidades
</pre>

## Funcionalidades

- Registro y autenticación de usuarios
- Generación y validación de JWT
- Protección de endpoints
- Gestión de productos
- Carrito de compras por usuario
- Manejo centralizado de errores
- Validación de datos en requests
- Testing de servicios

## Endpoints principales

### Autenticación

- POST /auth/register
- POST /auth/login

### Usuarios

- GET /user
- GET /user/{id}

### Productos

- GET /products
- POST /products
- GET /products/{id}

### Carrito

- POST /cart
- GET /cart

### Ejemplo de request

POST /auth/register

Body:

```json
{
  "username": "usuario",
  "email": "user@example.com",
  "password": "********"
}
````

## Variables de entorno

Configurar en `application.yml`:

```yml
datasource:  
  url: ${DB_URL}  
  username: ${DB_USER}  
  password: ${DB_PASSWORD}  
  driver-class-name: org.postgresql.Driver  
  
security:  
  jwt:  
    secret-key: ${JWT_SECRET}  
    expiration: ${TOKEN_EXPIRATION}
    refresh-token:  
      expiration: ${TOKEN_REFRESH}
```

## Instalación y ejecución del proyecto

Clonar repositorio:

- `git clone https://github.com/TU-USUARIO/TU-REPO.git`
- `cd TU-REPO`

Instalar dependencias y compilar:

- `mvn clean install`

Ejecución en desarrollo:

- `mvn spring-boot:run`

## Testing

Ejecutar tests:

- `mvn test`

Se implementaron tests unitarios en la capa de servicios utilizando:

- JUnit
- Mockito

## Decisiones técnicas

- Se utilizó JWT para mantener la API stateless y asegurar las comunicaciones.
- Spring Security fue configurado con filtros personalizados para manejar autenticación y autorización.
- Se implementó una arquitectura en capas para mejorar la mantenibilidad y escalabilidad del proyecto.
- Se separaron DTOs y entidades para evitar acoplamiento entre la API y la base de datos.
- Se incorporó un manejo global de excepciones para estandarizar las respuestas de error.
- Se utilizaron tests unitarios para validar la lógica de negocio de forma aislada.

## Estado del proyecto

Proyecto en desarrollo con foco en aprendizaje backend avanzado. Funcional y en constante mejora.
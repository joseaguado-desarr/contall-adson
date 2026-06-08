# Contall Inventario

Aplicación Spring Boot para registro de inventario de almacén de ventas.

## Características

- Registro y gestión de productos
- Control de stock y precio
- Autenticación segura con usuario administrador
- Base de datos en memoria H2 para pruebas rápidas

## Credenciales

- Usuario: `aguadojose20@gmail.com`
- Clave: `132828`

## Endpoints principales

- `POST /api/auth/login`
- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

> La aplicación ahora usa H2 en memoria para el desarrollo local.
> Si ejecutas la app en Spring Boot embebido, usa `http://localhost:8080/` o `http://localhost:8080/api/products`.
> Si la despliegas como `.war` en Tomcat con contexto `contall`, entonces usa `http://localhost:8080/contall/api/products`.

## Ejecutar

Desde la carpeta del proyecto:

```bash
mvn spring-boot:run
```

Después abre `http://localhost:8080/` en el navegador.

## Consola H2

- Abre `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:contalldb`
- Usuario: `sa`
- Clave: (dejar vacío)

## Frontend e interfaz

La aplicación ya incluye una interfaz web estática en `src/main/resources/static/index.html`.

- Navega a `http://localhost:8080/`
- Inicia sesión con las credenciales de administrador:
  - Usuario: `aguadojose20@gmail.com`
  - Clave: `132828`
- Administra productos desde la interfaz: listar, crear, editar y eliminar.

El frontend usa `/api/auth/login` para iniciar sesión y `/api/products` para gestionar el inventario.

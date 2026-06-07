# Contall Inventario

Aplicación Spring Boot para registro de inventario de almacén de ventas.

## Características

- Registro y gestión de productos
- Control de stock y precio
- Autenticación segura con usuario administrador
- Base de datos en memoria H2 para pruebas rápidas

## Credenciales

- Usuario: `aguadojose20@gmail.ocm`
- Clave: `132828`

## Endpoints principales

- `POST /api/auth/login`
- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

> Si ejecutas la app en Spring Boot embebido, usa `http://localhost:8080/` o `http://localhost:8080/api/products`.
> Si la despliegas como `.war` en Tomcat con contexto `contall`, entonces usa `http://localhost:8080/contall/api/products`.

## Ejecutar

Desde la carpeta del proyecto:

```bash
mvn spring-boot:run
```

Después abre `http://localhost:8080/` en el navegador.

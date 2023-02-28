# Punto de Venta API REST

Este proyecto es una API REST para un punto de venta sencillo. Permite gestionar clientes, órdenes, detalles de órdenes, categorías y productos.

## Tecnologías utilizadas
- `Java`
- `Spring Boot`
- `Spring Data JPA`
- `MySQL`

## Requerimientos
- `Java 11 o superior`
- `Maven`
- `MySQL`

## Instalación
1. Clona este repositorio.
2. **Crea una base de datos donde se crearan las tablas al arrancar la aplicacion.**
3. Abre el archivo `src/main/resources/application.properties` y configura las credenciales de tu base de datos MySQL.
4. Abre una terminal y navega hasta la carpeta del proyecto.
5. Ejecuta el comando `mvn spring-boot:run` para iniciar la aplicación.

## Uso
La API REST tiene los siguientes endpoints:

### Clientes
- `GET /clientes`: Obtiene la lista de todos los clientes.
- `GET /clientes/{id}`: Obtiene un cliente por su ID.
- `POST /clientes`: Crea un nuevo cliente.
- `PUT /clientes/{id}`: Actualiza un cliente existente.
- `DELETE /clientes/{id}`: Elimina un cliente por su ID.

### Órdenes
- `GET /ordenes`: Obtiene la lista de todas las órdenes.
- `GET /ordenes/{id}`: Obtiene una orden por su ID.
- `POST /ordenes`: Crea una nueva orden.
- `PUT /ordenes/{id}`: Actualiza una orden existente.
- `DELETE /ordenes/{id}`: Elimina una orden por su ID.

### Detalles de órdenes
- `GET /detalles-ordenes`: Obtiene la lista de todos los detalles de órdenes.
- `GET /detalles-ordenes/{id}`: Obtiene un detalle de orden por su ID.
- `POST /detalles-ordenes`: Crea un nuevo detalle de orden.
- `PUT /detalles-ordenes/{id}`: Actualiza un detalle de orden existente.
- `DELETE /detalles-ordenes/{id}`: Elimina un detalle de orden por su ID.

### Categorías
- `GET /categorias`: Obtiene la lista de todas las categorías.
- `GET /categorias/{id}`: Obtiene una categoría por su ID.
- `POST /categorias`: Crea una nueva categoría.
- `PUT /categorias/{id}`: Actualiza una categoría existente.
- `DELETE /categorias/{id}`: Elimina una categoría por su ID.

### Productos
- `GET /productos`: Obtiene la lista de todos los productos.
- `GET /productos/{id}`: Obtiene un producto por su ID.
- `POST /productos`: Crea un nuevo producto.
- `PUT /productos/{id}`: Actualiza un producto existente.
- `DELETE /productos/{id}`: Elimina un producto por su ID.

Puedes utilizar una herramienta como Postman o Insomnia para realizar las solicitudes HTTP a la API REST.

`ejemplo ` /productos
[![Captura-de-pantalla-2023-02-27-21-20-32.png](https://i.postimg.cc/pThQL7RF/Captura-de-pantalla-2023-02-27-21-20-32.png)](https://postimg.cc/dkFZW57s)

`ejemplo` /ordenes
[![Captura-de-pantalla-2023-02-27-21-27-46.png](https://i.postimg.cc/zGQVGfBx/Captura-de-pantalla-2023-02-27-21-27-46.png)](https://postimg.cc/qN8km42K)

## Contribuir
Si quieres contribuir a este proyecto, por favor sigue estas instrucciones:
1. Crea un fork del repositorio.
2. Crea una nueva rama en tu fork.
3. Realiza tus cambios y haz un commit.
4. Envía un pull request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para obtener más información.

# byma-fondos-up-back

## Descripción General
Esta es una API basada en Spring Boot que permite administrar gerentes. Puedes crear, obtener, actualizar y eliminar gerentes utilizando esta API. La API está documentada con Swagger y puedes interactuar con ella a través de la interfaz de usuario de Swagger.

## Tecnologías

- **Spring Boot** - Framework para el backend
- **Java** - Lenguaje de programación
- **Maven** - Gestión de dependencias
- **Lombok** - Para reducir código repetitivo
- **Jakarta Validation** - Validación de DTOs
- **Swagger/OpenAPI** - Documentación de la API
- **Base de Datos H2 (Opcional)** - Base de datos en memoria para desarrollo/pruebas
- **Postman** - Testing de la API
- **JUnit 5** - Framework de pruebas unitarias
- **Mockito** - Framework de mocking para pruebas unitarias
- **Docker (Opcional)** - Contenerización
- **Spring Data JPA** - Capa de acceso a datos

## Comenzando

### 1. Clonar el Repositorio

```bash
git clone https://github.com/teamcubation/byma-fondos-up-back.git
cd byma-fondos-up-back
```

### 2. Construir el Proyecto
Usa Maven para instalar las dependencias y construir el proyecto:
```bash
mvn clean install
```

### 3. Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

Por defecto, la aplicación se ejecuta en `http://localhost:8080`. Puedes acceder a la API a través de esta URL.

### 4. Acceder a Swagger UI
Una vez que la aplicación esté en ejecución, puedes ver la documentación de la API e interactuar con ella a través de Swagger UI:
```bash
http://localhost:8080/swagger-ui/index.html
```

### 5. Ejemplos de Solicitudes a la API
A continuación, algunos ejemplos para interactuar con la API utilizando Postman:

1. Crear un Gerente (POST /api/v1/gerentes)

**POST** `http://localhost:8080/api/v1/gerentes`
```json
{
  "idOrganizacionGerente": 12345,
  "denominacion": "Nombre de la organización",
  "liquidaEnByma": true,
  "habilitado": true,
  "tieneRelacion": false,
  "observaciones": "Algunas observaciones adicionales"
}
```

2. Obtener todos los gerentes (GET /api/v1/gerentes)

**GET** `http://localhost:8080/api/v1/gerentes`

3. Obtener gerente por ID (GET /api/v1/gerentes/{id})

**GET** `http://localhost:8080/api/v1/gerentes/{id}`

4. Actualizar un Gerente (PUT /api/v1/gerentes/{id})

**PUT** `http://localhost:8080/api/v1/gerentes/{id}`
```json
{
  "idOrganizacionGerente": 12345,
  "denominacion": "Nombre actualizado de la organización",
  "liquidaEnByma": true,
  "habilitado": false,
  "tieneRelacion": true,
  "observaciones": "Observaciones actualizadas"
}
```

5. Eliminar un Gerente (DELETE /api/v1/gerentes/{id})

**DELETE** `http://localhost:8080/api/v1/gerentes/{id}`
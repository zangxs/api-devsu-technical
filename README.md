# api-devsu-technical

Prueba técnica para la empresa Devsu — Arquitectura de microservicios.

Este repositorio contiene 2 microservicios independientes, comunicados de forma asíncrona vía RabbitMQ, siguiendo los principios de Clean Architecture:

- **app-microservice-cliente-app** — gestión de Cliente
- **app-microservice-cuenta-app** — gestión de Cuenta / Movimiento, incluye el endpoint de reportes

## Tabla de contenidos

- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Requisitos previos](#requisitos-previos)
- [Cómo levantar el proyecto](#cómo-levantar-el-proyecto)
- [Puertos y credenciales](#puertos-y-credenciales)
- [Documentación de la API](#documentación-de-la-api)
- [Endpoints principales](#endpoints-principales)
- [Comunicación asíncrona](#comunicación-asíncrona)
- [Pruebas](#pruebas)
- [Decisiones de diseño y aclaraciones](#decisiones-de-diseño-y-aclaraciones)

---

## Arquitectura

Cada microservicio sigue Clean Architecture, separado en tres capas con dependencias apuntando siempre hacia el dominio:

```
infrastructure  →  application  →  domain
```

- **domain**: modelos puros (`Cliente`, `Cuenta`, `Movimiento`), contratos de repositorio (interfaces) y excepciones de negocio. Sin dependencias de frameworks.
- **application**: DTOs, mappers y servicios que orquestan la lógica de negocio. Depende solo de `domain`.
- **infrastructure**: controladores REST, entidades JPA, adaptadores de persistencia, configuración de RabbitMQ y manejo global de excepciones. Depende de `domain` y `application`.

## Tecnologías

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA / Hibernate
- PostgreSQL 15
- RabbitMQ 3.13 (comunicación asíncrona entre microservicios)
- Lombok
- SpringDoc OpenAPI (Swagger UI)
- JUnit 5 + Mockito (pruebas unitarias)
- Karate DSL (pruebas de integración)
- Docker / Docker Compose

## Requisitos previos

- Docker y Docker Compose instalados
- Los siguientes puertos disponibles en tu máquina:

| Puerto | Servicio |
|---|---|
| 8080 | cliente-app |
| 8081 | cuenta-app |
| 5432 | PostgreSQL |
| 5672 | RabbitMQ (AMQP) |
| 15672 | RabbitMQ (Management UI) |

No es necesario ejecutar manualmente ningún script de creación de base de datos o tablas. Las bases de datos se crean automáticamente al iniciar el contenedor de PostgreSQL (ver `init-db.sql`), y las tablas se generan mediante Hibernate gracias a la propiedad configurada en ambos `application.yml`:

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: ${SPRING_JPA_HIBERNATE_DDL_AUTO:update}
```

El script `BaseDatos.sql` (incluido en la raíz de cada microservicio) se entrega como referencia del esquema generado, conforme lo solicitado en el enunciado.

## Cómo levantar el proyecto

Clonar el repositorio y, desde la raíz, ejecutar:

```bash
docker compose up --build
```

Esto construye ambas imágenes y levanta 4 contenedores: `postgres`, `rabbitmq`, `cliente-app` y `cuenta-app`.

Para bajar el entorno:

```bash
docker compose down
```

Para bajar el entorno y eliminar también los datos persistidos:

```bash
docker compose down -v
```

## Puertos y credenciales

### Bases de datos

| Base de datos | Usuario | Password |
|---|---|---|
| cliente_db | cliente_user | cliente_pass |
| cuenta_db | cuenta_user | cuenta_pass |

### RabbitMQ

| Usuario | Password | Management UI |
|---|---|---|
| rabbit_user | rabbit_pass | http://localhost:15672 |

## Documentación de la API

Cada microservicio expone su documentación interactiva vía Swagger UI:

- Cliente: http://localhost:8080/swagger-ui/index.html
- Cuenta: http://localhost:8081/swagger-ui/index.html

Especificación OpenAPI en formato JSON:

- http://localhost:8080/v3/api-docs
- http://localhost:8081/v3/api-docs

## Endpoints principales

### Cliente (puerto 8080)

| Verbo | Endpoint | Descripción |
|---|---|---|
| POST | `/api/clientes` | Crear cliente |
| GET | `/api/clientes` | Listar todos los clientes |
| GET | `/api/clientes/{id}` | Buscar cliente por id |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| PATCH | `/api/clientes/{id}` | Actualizar cliente parcialmente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente |

### Cuenta (puerto 8081)

| Verbo | Endpoint | Descripción |
|---|---|---|
| POST | `/api/cuentas` | Crear cuenta |
| GET | `/api/cuentas/{id}` | Buscar cuenta por id |
| PUT | `/api/cuentas/{id}` | Actualizar cuenta |
| POST | `/api/movimientos` | Registrar movimiento (actualiza el saldo de la cuenta) |
| GET | `/api/movimientos/{id}` | Buscar movimiento por id |
| GET | `/reportes?clienteId={id}&fechaInicio={yyyy-MM-dd}&fechaFin={yyyy-MM-dd}` | Estado de cuenta por cliente y rango de fechas |

## Comunicación asíncrona

`cliente-app` publica eventos a RabbitMQ cuando un cliente es creado o actualizado. `cuenta-app` consume esos eventos y mantiene una réplica local (`clientes_replica`) con los datos mínimos necesarios para operar sin depender de una llamada síncrona al otro microservicio.

```
cliente-app                          rabbitmq                          cuenta-app
    │  crea/actualiza cliente            │                                  │
    ├──── publica evento ───────────────►│                                  │
    │     (exchange: cliente-events)     │                                  │
    │                                    ├──── entrega a la cola ──────────►│
    │                                    │     (cuentas.cliente-events)     │
    │                                    │                                  ├─ guarda/actualiza
    │                                    │                                  │  clientes_replica
```

Esto permite que `cuenta-app` valide la existencia de un cliente al crear una cuenta, y arme el reporte de estado de cuenta, sin acoplamiento síncrono entre los servicios.

## Pruebas

### Pruebas unitarias (JUnit + Mockito)

```bash
cd app-microservice-cliente-app
mvn test

cd app-microservice-cuenta-app
mvn test
```

Cobertura incluida:
- `ClienteTest` — pruebas de la entidad de dominio Cliente
- `ClienteServiceTest` / `CuentaServiceTest` — lógica de negocio con dependencias mockeadas
- `ClienteControllerTest` — capa web con `MockMvc`, incluyendo manejo de excepciones

### Pruebas de integración (Karate)

> (requieren los microservicios corriendo, vía `docker compose up`).
```bash
cd karate-tests
mvn test

```

### Colección Postman

> Se incluye la collection y el enviroment necesario

## Decisiones de diseño y aclaraciones

- Aunque el enunciado menciona el campo **"contraseña"** para Cliente, se tomó la decisión de nombrarlo **`password`** en el modelo y la API, por convención técnica en inglés.
- El enunciado solicita un endpoint CRU (Crear, Leer, Actualizar) para Movimientos. No se implementó el **Update**, dado que un movimiento financiero ya registrado no debería ser editable sin romper la trazabilidad del saldo — modificarlo retroactivamente invalidaría los saldos de los movimientos posteriores. Se considera esto una decisión de integridad de datos más que una omisión.
- `Cliente` hereda de `Persona` a nivel de modelo de dominio (Java), pero ambas se persisten en una única tabla (`clientes`) en lugar de usar herencia JPA con tablas separadas (`JOINED`), evitando joins innecesarios para este caso de uso.
- `cuenta-app` no realiza llamadas HTTP síncronas a `cliente-app`. Mantiene una réplica de solo lectura (`clientes_replica`) alimentada por eventos de RabbitMQ, lo cual preserva el aislamiento entre microservicios.
- El manejo de errores está centralizado con `@RestControllerAdvice` (`GlobalExceptionHandler`) en cada microservicio, devolviendo consistente (`GenericResponse`) con `data`, `code` y `dateTime` en todas las respuestas, tanto de éxito como de error.

## Autor

Brayan — [github.com/brayanpv](https://github.com/zangxs)
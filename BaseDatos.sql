CREATE TABLE IF NOT EXISTS personas (
                                        id              BIGSERIAL PRIMARY KEY,
                                        nombre          VARCHAR(100) NOT NULL,
    genero          VARCHAR(20),
    edad            INTEGER,
    identificacion  VARCHAR(20) NOT NULL UNIQUE,
    direccion       VARCHAR(200),
    telefono        VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS clientes (
                                        cliente_id      BIGSERIAL PRIMARY KEY,
                                        password        VARCHAR(255) NOT NULL,
    estado          BOOLEAN NOT NULL DEFAULT TRUE,
    -- campos heredados de Persona
    nombre          VARCHAR(100) NOT NULL,
    genero          VARCHAR(20),
    edad            INTEGER,
    identificacion  VARCHAR(20) NOT NULL UNIQUE,
    direccion       VARCHAR(200),
    telefono        VARCHAR(20)
    );


CREATE INDEX idx_movimientos_cuenta_id ON movimientos(cuenta_id);

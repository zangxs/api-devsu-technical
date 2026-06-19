-- Este script corre UNA SOLA VEZ cuando el contenedor de postgres
-- se crea por primera vez (volumen vacío). Crea las dos bases de
-- datos y sus respectivos usuarios para cada microservicio.

CREATE USER cliente_user WITH PASSWORD 'cliente_pass';
CREATE DATABASE cliente_db OWNER cliente_user;
GRANT ALL PRIVILEGES ON DATABASE cliente_db TO cliente_user;

CREATE USER cuenta_user WITH PASSWORD 'cuenta_pass';
CREATE DATABASE cuenta_db OWNER cuenta_user;
GRANT ALL PRIVILEGES ON DATABASE cuenta_db TO cuenta_user;
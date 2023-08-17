
-- Crear la tabla de usuarios (modificada para incluir el nombre)
-- Reemplazar PIN con password
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    alias VARCHAR(100),
    passw VARCHAR(25) NOT NULL, 
    saldo DOUBLE NOT NULL
);

ALTER TABLE usuarios MODIFY COLUMN alias VARCHAR(100) NOT NULL;

ALTER TABLE usuarios ADD CONSTRAINT unique_alias_constraint UNIQUE (alias);


-- Crear la tabla de historico
CREATE TABLE IF NOT EXISTS historico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    tipo_operacion VARCHAR(50) NOT NULL,
    cantidad DOUBLE,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Insertar datos de ejemplo
-- Insertar datos de ejemplo en usuarios
INSERT INTO usuarios (nombre, alias, passw, saldo) VALUES 
('Juan Perez',      'jperez','1234', 1000.0),
('Ana Ramirez',     'aramirez','1234', 2500.0),
('Carlos Gomez',    'cgomez','1234', 500.0),
('Marta Torres',    'mtorres','1234', 750.0),
('Luisa Fernandez', 'lfernandez','1234', 3000.0);

-- Insertar datos de ejemplo en historico (asumiendo que los IDs de los usuarios coinciden con los valores insertados anteriormente)
-- Juan Perez hizo un depósito de 200.0
INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (1, 'deposito', 1000.0);
INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (2, 'deposito', 2500.0);
INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (3, 'deposito', 500.0);
INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (4, 'deposito', 750.0);
INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (5, 'deposito', 3000.0);



-- Crear la tabla de usuarios 
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    pin INT NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    alias VARCHAR(100) NOT NULL UNIQUE
);

-- Crear la tabla de historicos
CREATE TABLE IF NOT EXISTS historicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    tipo_operacion VARCHAR(50) NOT NULL,
    cantidad DECIMAL(10, 2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Insertar datos de ejemplo en usuarios
INSERT INTO usuarios (nombre, pin, saldo, alias) VALUES 
('Juan Perez', 1234, 1000.0, 'jperez'),
('Ana Ramirez', 5678, 2500.0, 'aramirez'),
('Carlos Gomez', 9012, 500.0, 'cgomez'),
('Marta Torres', 3456, 750.0, 'mtorrez'),
('Luisa Fernandez', 7890, 3000.0, 'lfernandez');
-- Insertar datos de ejemplo en historico (asumiendo que los IDs de los usuarios coinciden con los valores insertados anteriormente)
-- Juan Perez hizo un depósito de 200.0
INSERT INTO historicos (usuario_id, tipo_operacion, cantidad) VALUES (1, 'Depósito', 1000.0);
INSERT INTO historicos (usuario_id, tipo_operacion, cantidad) VALUES (2, 'Depósito', 2500.0);
INSERT INTO historicos (usuario_id, tipo_operacion, cantidad) VALUES (3, 'Depósito', 500.0);
INSERT INTO historicos (usuario_id, tipo_operacion, cantidad) VALUES (4, 'Depósito', 750.0);
INSERT INTO historicos (usuario_id, tipo_operacion, cantidad) VALUES (5, 'Depósito', 3000.0);



create database bdd_atm;
use bdd_atm;
drop database bdd_atm;

CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY,
    pin VARCHAR(15)
);

CREATE TABLE OperacionesUsuarios (
    id_operacion INT PRIMARY KEY,
    id_usuario INT,
    saldo DECIMAL(10, 2),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

-- Inserciones en la tabla Usuarios
INSERT INTO Usuarios (id_usuario, pin) VALUES
(1, '1234'),
(2, '5678'),
(3, '9012'),
(4, '3456'),
(5, '7890');

-- Inserciones en la tabla OperacionesUsuarios
INSERT INTO OperacionesUsuarios (id_operacion, id_usuario, saldo) VALUES
(1, 1, 1000.00),
(2, 2, 500.00),
(3, 3, 200.00),
(4, 4, 1500.00),
(5, 5, 750.00);

-- Verificar datos
select * from Usuarios;
select * from OperacionesUsuarios;

INSERT INTO Tienda (nombre) VALUES ("PROWAD");

INSERT INTO Estado (nombre) VALUES ("Pendiente");
INSERT INTO Estado (nombre) VALUES ("Pagado");

INSERT INTO Rol (nombre) VALUES ("Administrador");
INSERT INTO Rol (nombre) VALUES ("Almacenista");
INSERT INTO Rol (nombre) VALUES ("Cliente");

INSERT INTO TipoDato (nombre) VALUES ("Cadena");
INSERT INTO TipoDato (nombre) VALUES ("Entero");
INSERT INTO TipoDato (nombre) VALUES ("Flotante");

INSERT INTO Usuario (curp, nombre, primerApellido, correoElectronico, contrasena, Tiendaid, Rolid) 
		VALUES ("000000000000000000", "Natalia", "Hernández", "hdeznatali@gmail.com", "contrasena", "1", "1");



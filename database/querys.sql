INSERT INTO Atributo (nombre, TipoDatoid) VALUES ("Fecha de caducidad", "4");
INSERT INTO Atributo (nombre, TipoDatoid) VALUES ("Color", "1");
INSERT INTO Atributo (nombre, TipoDatoid) VALUES ("Marca", "1");
INSERT INTO Atributo (nombre, TipoDatoid) VALUES ("Tamaño en cm", "3");
INSERT INTO Atributo (nombre, TipoDatoid) VALUES ("Cantidad", "2");

INSERT INTO Categoria (nombre) VALUES ("Electrodomésticos");
INSERT INTO Categoria (nombre) VALUES ("Alimentos");
INSERT INTO Categoria (nombre) VALUES ("Panadería");
INSERT INTO Categoria (nombre) VALUES ("Cosméticos");

INSERT INTO Categoria_Atributo (Categoriaid, Atributoid) VALUES ("2", "1");
INSERT INTO Categoria_Atributo (Categoriaid, Atributoid) VALUES ("2", "5");
INSERT INTO Categoria_Atributo (Categoriaid, Atributoid) VALUES ("3", "1");
INSERT INTO Categoria_Atributo (Categoriaid, Atributoid) VALUES ("3", "2");

INSERT INTO Usuario (curp, nombre, primerApellido, segundoApellido, correoElectronico, contrasena, Tiendaid, Rolid) 
	VALUES ("HESN930515MDFRNT03", "Natalia", "Hernández", "Sánchez", "hdeznatali@gmail.com", "123456", "1", "2");
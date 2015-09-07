INSERT INTO Atributo (nombre, TipoDatoid) VALUES ("Fecha de caducidad", "1");
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
	VALUES ("HESN930515MDFRNT03", "Giselle", "Hernández", "Sánchez", "giselle@gmail.com", "123456", "1", "2");
INSERT INTO Usuario (curp, nombre, primerApellido, segundoApellido, correoElectronico, contrasena, Tiendaid, Rolid) 
		VALUES ("PERA900909MDFRNT09", "Araceli", "Pérez", "Ramírez", "ara@gmail.com", "contrasena", "1", "3");

INSERT INTO Producto (nombre, Tiendaid, cantidad, precio) VALUES ("Donas de chocolate", "1", "10", "20.0");
INSERT INTO Producto (nombre, Tiendaid, cantidad, precio) VALUES ("Cereal", "1", "20", "40");
INSERT INTO Producto (nombre, Tiendaid, cantidad, precio) VALUES ("Refrigerador", "1", "100", "10000");

INSERT INTO Producto_Categoria (Productoid, Categoriaid) VALUES ("1", "3");
INSERT INTO Producto_Categoria (Productoid, Categoriaid) VALUES ("1", "2");

INSERT INTO Producto_Atributo (Productoid, Atributoid, valorEntero) VALUES ("1", "5", "10");
INSERT INTO Producto_Atributo (Productoid, Atributoid, valorCadena) VALUES ("1", "2", "Café");

INSERT INTO Carrito (fechaCompra, Usuariocurp, Estadoid) VALUES ("2000/10/30", "PERA900909MDFRNT09", "2");
INSERT INTO Carrito (fechaCompra, Usuariocurp, Estadoid) VALUES ("2010/11/14", "PERA900909MDFRNT09", "2");

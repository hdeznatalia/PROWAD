function Atributo(nombre, tipoDato, id) {
    this.nombre = nombre;
    this.tipoDato = new TipoDato(tipoDato);
}

function TipoDato(nombre) {
    this.nombre = nombre;
}

function Categoria(nombre) {
    this.nombre = nombre;
}

function ProductoAtributo (atributo, valorCadena, valorEntero, valorFlotante, valorFecha) {
	this.id = new ProductoAtributoId(atributo);
	this.valorCadena = valorCadena;
	this.valorEntero = valorEntero;
	this.valorFlotante = valorFlotante;
	this.valorFecha = valorFecha;
}

function ProductoAtributoId(atributo) {
	this.atributo = new Atributo(atributo);
}

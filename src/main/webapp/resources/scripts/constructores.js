function Atributo(nombre, tipoDato, id) {
    this.nombre = nombre;
    this.tipoDato = new TipoDato(tipoDato);
}

function TipoDato(nombre) {
    this.nombre = nombre;
}

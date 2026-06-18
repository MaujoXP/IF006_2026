const form = document.getElementById("formNA");
const resultado = document.getElementById("resultado");
let numero = generarAleatorio(1, 50);
const lista = document.getElementById("lista");
form.addEventListener("submit", function(e){
	e.preventDefault();
	const intento = parseInt(document.getElementById("txtNumero").value);
	const pista = document.getElementById("resultado");
	let msg = '';
	if(intento === numero) {
		msg = 'Acertaste!';
	} else if(intento < numero) {
		msg = 'Es mayor'
	} else {
		msg = 'Es menor'
	}
	const elemento = document.createElement("li");
	elemento.innerHTML = `${msg} que: ${intento}`;
	lista.appendChild(elemento);
	resultado.innerHTML = msg;
});
function generarAleatorio(min, max) {
	if(min < max) {
		return Math.floor(Math.random() * (max - min + 1)) + min;
	} else {
		alert("El rango del final no puede ser menor que el inicio");
	}
}
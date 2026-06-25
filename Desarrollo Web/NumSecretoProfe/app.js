let numeroSecreto = generarNumero();
function generarNumero() {
	return Math.floor(Math.random() * 100) + 1;
}

const txtNumero = document.getElementById("numero");
const mensaje = document.getElementById("mensaje");

document.getElementById("btnIntentar").addEventListener("click", intentar);

document.getElementById("btnReiniciar").addEventListener("click", reiniciar);

function intentar() {
	const valor = Number(txtNumero.value);
	if(!valor) {
		mostrarMensaje("Ingrese un número", "");
		return;
	} if(valor > numeroSecreto) {
		mostrarMensaje("Muy alto", "alto");
		return;
	} else if(valor < numeroSecreto) {
		mostrarMensaje("Muy bajo", "bajo");
		return;
	} else {
		mostrarMensaje("Felicidades", "correcto");
	}
}

function mostrarMensaje(texto, clase) {
	mensaje.textContent = texto;
	mensaje.className = clase;
}

function reiniciar() {
	numeroSecreto = generarNumero();
	txtNumero.value = "";
	mensaje.textContent = "";
	mensaje.className = "mensaje";
}
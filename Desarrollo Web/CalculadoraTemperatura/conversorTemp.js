const temperatura = document.getElementById("temperatura");
const unidadOriginal = document.getElementById("unidadOriginal");
const unidadDestino = document.getElementById("unidadDestino");
const res = document.getElementById("resultado");
const btnLimpiar = document.getElementById("btnLimpiar").addEventListener("click", function(){
	temperatura.value = "";
});
const btnConvertir = document.getElementById("btnConvertir");
btnConvertir.addEventListener("click", convertir);
function convertir() {
	let resultado = 0;
	let msg = "";
	if(unidadOriginal.value === unidadDestino.value) {
		msg = "Conversión ambigua";
	}
	if(unidadOriginal.value === "fahrenheit" && unidadDestino.value === "celcius") {
		resultado = (temperatura.value - 32) * 5/9
		msg = resultado + " °C"
	} else if(unidadOriginal.value === "fahrenheit" && unidadDestino.value === "kelvin") {
		resultado = (temperatura.value - 32) * 5/9 + 273.15
		msg = resultado + " K"
	} else if(unidadOriginal.value === "celcius" && unidadDestino.value === "fahrenheit") {
		resultado = temperatura.value * 9/5 + 32
		msg = resultado + " °F"
	} else if(unidadOriginal.value === "celcius" && unidadDestino.value === "kelvin") {
		resultado = temperatura.value + 273.15;
		msg = resultado + " K"
	} else if(unidadOriginal.value === "kelvin" && unidadDestino.value === "fahrenheit") {
		resultado = (temperatura.value - 273.15) * 9/5 + 32
		msg = resultado + " °F"
	} else if(unidadOriginal.value === "kelvin" && unidadDestino.value === "celcius") {
		resultado = temperatura.value - 273.15
		msg = resultado + " °C"
	}
	res.innerHTML = `<p>${msg}</p>`
}
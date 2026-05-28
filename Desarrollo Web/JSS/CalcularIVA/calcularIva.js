const form = document.getElementById("formIVA");
const resultado = document.getElementById("resultado");

form.addEventListener("submit", function(e){
	e.preventDefault();	
	const precio = parseFloat(
		document.getElementById("txtPrecio").value	);
	const iva = parseFloat(
		document.getElementById("txtIva").value);
	const montoIva = precio * (iva/100);
	const total = precio + montoIva;

	resultado.innerHTML = `<p><strong>Precio:</strong>₡${precio.toFixed(2)}</p>
						<p><strong>IVA:</strong>₡${montoIva.toFixed(2)}</p>
					<p><strong>Precio:</strong>₡${total.toFixed(2)}</p>`;
});
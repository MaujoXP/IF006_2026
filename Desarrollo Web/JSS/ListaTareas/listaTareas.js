const boton = document.getElementById("btnAgregar");
const tarea = document.getElementById("tarea");
const lista = document.getElementById("lista");


boton.addEventListener("click", function() {
	if(tarea.value === "") {
		alert("Escriba una tarea");
		return;
	}
	const li = document.createElement("li");
	/*Agrega el texto de la tarea y un boton eliminar*/
	li.innerHTML = `
		${tarea.value}
		<button class="eliminar">X</button>
	`;
	/*Agrega a la lista*/
	lista.appendChild(li);

	tarea.value = "";

	const btnEliminar = li.querySelector(".eliminar");
	btnEliminar.addEventListener("click", function(){
		if(confirm("Ta seguro?")) {
			li.remove();	
		} else {
			return;
		}
		
	})
});
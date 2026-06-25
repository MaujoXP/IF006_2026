const curso = {};
const estudiantes = [];

const formCurso = document.getElementById("formCurso");
const formEstudiante = document.getElementById("formEstudiante");

const pantallaCurso = document.getElementById("pantallaCurso");
const pantallaEstudiante = document.getElementById("pantallaEstudiante");
const pantallaResultado = document.getElementById("pantallaResultado");

const tablaEstudiante = document.getElementById("tablaEstudiantes");

//Curso
formCurso.addEventListener("submit", function(e) {
	e.preventDefault();
	curso.nombre = document.getElementById("nombreCurso").value;
	curso.codigo = document.getElementById("codigoCurso").value;
	curso.profesor = document.getElementById("profesor").value;

	pantallaCurso.classList.add("oculto");
	pantallaEstudiante.classList.remove("oculto");
});

//Estudiantes
formEstudiante.addEventListener("submit", function(e) {
	e.preventDefault();

	const nombre = document.getElementById("nombreEstudiante").value;
	const nota = Number(document.getElementById("nota").value);

	estudiantes.push({nombre, nota});

	mostrarTabla();

	formEstudiante.reset();

});

function mostrarTabla() {
	tablaEstudiante.innerHTML = "";
	let estado = "";
	estudiantes.forEach(estudiante => {
		if(estudiante.nota >= 67) {
			estado = "Aprobado";
		} else if(estudiante.nota >= 60) {
			estado = "Ampliacion"
		} else {
			estado = "Reprobado"
		}
		tablaEstudiante.innerHTML += `
			<tr>
			    <td>${estudiante.nombre}</td>
				<td>${estudiante.nota}</td>
				<td>${estado}
			</tr>
		`;
	});
}

//Calcular el promedio
document.getElementById("btnCalcular").addEventListener("click", function() {
	if(estudiantes.length === 0) {
		alert("Debe registrar estudiantes");
		return;
	}

	let suma = 0;
	let mayor = estudiantes[0].nota;
	let menor = estudiantes[0].nota;

	estudiantes.forEach(estudiante=>{
		suma+=estudiante.nota;
		if(estudiante.nota > mayor) {
			mayor = estudiante.nota;
		}
		if(estudiante.nota < menor) {
			menor = estudiante.nota;
		}
	});

	const promedio = suma / estudiantes.length;

	pantallaEstudiante.classList.add("oculto");
	pantallaResultado.classList.remove("oculto");

	document.getElementById("infoCurso").textContent = `Curso: ${curso.nombre} | Código: ${curso.codigo} | Profesor: ${curso.profesor}`;
	document.getElementById("cantidadEstudiantes").textContent = `Cantidad estudiantes: ${estudiantes.length}`;
	document.getElementById("promedioGeneral").textContent = `Promedio general: ${promedio.toFixed(2)}`;
	document.getElementById("notaMayor").textContent = `Nota más alta: ${mayor}`;
	document.getElementById("notaMenor").textContent = `Nota más baja: ${menor}`;
});
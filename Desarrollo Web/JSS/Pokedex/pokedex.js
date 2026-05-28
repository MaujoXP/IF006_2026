const boton = document.getElementById("buscar");
const resultado = document.getElementById("resultado");
boton.addEventListener("click", function(){
	const nombre = document.getElementById("pokemon")
	.value.toLowerCase();
	fetch(`https://pokeapi.co/api/v2/pokemon/${nombre}`)
	.then(function(response){
		return response.json();
	})
	.then(function(data){
		resultado.innerHTML = `
			<h2>${data.name}</h2>
			<img src="${data.sprites.front_default}">
			<p>Tipo: ${data.types[0].type.name}</p>
			<p>Altura: ${data.height}</p>
			<p>Peso: ${data.weight}</p>
		`;
	})
	.catch(function(){
		resultado.innerHTML = `<p>Pokemon no encontrado</p>`
	});
});
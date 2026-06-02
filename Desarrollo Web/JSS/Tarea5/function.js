const boton = document.getElementById("buscar");
const peliculas = document.getElementById("peliculas");
const apiKey = "aa5801b9";

function crearCard(datos) {
  return `
    <div class="card">
      <div class="card-inner">
        <div class="card-front">
          <img src="${datos.Poster}" alt="${datos.Title}">
          <h3>${datos.Title}</h3>
          <p><strong>Fecha:</strong> ${datos.Released}</p>
          <p><strong>Género:</strong> ${datos.Genre}</p>
          <p><strong>Puntaje IMDb:</strong> ${datos.imdbRating}</p>
        </div>
        <div class="card-back">
          <h3>${datos.Title}</h3>
          <p>${datos.Plot}</p>
        </div>
      </div>
    </div>
  `;
}

boton.addEventListener("click", function(){
  const titulo = document.getElementById("titulo").value;
  const url = `https://www.omdbapi.com/?apikey=${apiKey}&s=${encodeURIComponent(titulo)}`;

  fetch(url)
    .then(function(res){ 
      return res.json();
    })
    .then(function(datos){
      if (datos.Response === "True") {
        peliculas.innerHTML = "";
        datos.Search.forEach(function(peli){
          const detalleUrl = `https://www.omdbapi.com/?apikey=${apiKey}&i=${peli.imdbID}`;
          fetch(detalleUrl)
            .then(function(res){
              return res.json();
            })
            .then(function(detalle){
              peliculas.innerHTML += crearCard(detalle);
            });
        });
      } else {
        peliculas.innerHTML = `<p>No se encontraron películas</p>`;
      }
    })
    .catch(function(){
      peliculas.innerHTML = `<p>Error al consultar las películas</p>`;
    });
});

window.onload = function(){
  const url = `https://www.omdbapi.com/?apikey=${apiKey}&s=cats`;

  fetch(url)
    .then(function(res) {
      return res.json();
      })
    .then(function(datos){
      if (datos.Response === "True") {
        peliculas.innerHTML = "";
        datos.Search.forEach(function(peli){
          const detalleUrl = `https://www.omdbapi.com/?apikey=${apiKey}&i=${peli.imdbID}`;
          fetch(detalleUrl)
            .then(function(res){
              return res.json();
            })
            .then(function(detalle){
              peliculas.innerHTML += crearCard(detalle);
            });
        });
      } else {
        peliculas.innerHTML = `<p>No se encontraron películas</p>`;
      }
    })
    .catch(function(){
      peliculas.innerHTML = `<p>Error al cargar películas por defecto </p>`;
    });
};

let segundos = 0;
let intervalo;

const tiempo = document.getElementById("tiempo");
const btnIniciar = document.getElementById("btnIniciar");
const btnPausar = document.getElementById("btnPausar");
const btnReiniciar = document.getElementById("btnReiniciar");

function actualizarTiempo(){

    let minutos = Math.floor(segundos / 60);
    let seg = segundos % 60;

    minutos = String(minutos).padStart(2, "0");
    seg = String(seg).padStart(2, "0");

    tiempo.textContent = ${minutos}:${seg};
}

btnIniciar.addEventListener("click", function(){

    clearInterval(intervalo);

    intervalo = setInterval(function(){

        segundos++;
        actualizarTiempo();

    }, 1000);

});

btnPausar.addEventListener("click", function(){

    clearInterval(intervalo);

});

btnReiniciar.addEventListener("click", function(){

    clearInterval(intervalo);

    segundos = 0;

    actualizarTiempo();

});
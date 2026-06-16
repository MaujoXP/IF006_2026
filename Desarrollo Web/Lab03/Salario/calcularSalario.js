const form = document.getElementById("formCSD");
const resultado = document.getElementById("resultado");
form.addEventListener("submit", function(e){
    e.preventDefault();
    const horas = parseInt(document.getElementById("txtHoras").value);
    let salario = parseFloat(document.getElementById("txtSalario").value);
    const trabajador = document.getElementById("txtTrabajador").value;
    let total = 0;
    let msg = '';
    if (horas <= 8) {
        total = horas * salario;
        msg = `${horas} horas Pagadas de manera Ordinaria`;
    } else if (horas <= 12) {
        total = (salario * 8) + ((horas - 8) * salario * 1.5);
        msg = `Horas extras: ${horas-8}(max 4) pagadas con un +50%, resto pagadas ordinarias`;
    } else {
        total = (salario * 8) + (4 * salario * 1.5) + ((horas - 12) * salario);
        msg = `Horas extras: 4 pagadas con un +50%, resto pagadas ordinarias`;
    }

    resultado.innerHTML = `<p>Estimado ${trabajador} su desgloce de pago diario es</p>
    <p>Su desgloce de pago diario</p>
    <p>Horas:${horas}</p>
	<p>Pago por hora:${salario}</p>
	<p>Total:${total}</p>
    <p>Desgloce: ${msg}</p>`;
});


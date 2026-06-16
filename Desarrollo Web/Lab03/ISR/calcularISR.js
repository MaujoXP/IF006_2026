const form = document.getElementById("formISR");
const resultado = document.getElementById("resultado");
form.addEventListener("submit", function(e){
    e.preventDefault();
    const salario = parseFloat(document.getElementById("txtSalario").value);
    let impuesto = 0;
    let msg = '';
    if (salario > 918000) {
        let base;
        if (salario > 1347000) {
            base = 1347000 - 918000;
        } else {
            base = salario - 918000;
        }
        let impuestoAux = base * 0.10;
        impuesto += impuestoAux;
        msg += "Impuesto 10%: " + impuestoAux + "\n";
    }

    if (salario > 1347000) {
        let base;
        if (salario > 2364000) {
            base = 2364000 - 1347000;
        } else {
            base = salario - 1347000;
        }
        let impuestoAux = base * 0.15;
        impuesto += impuestoAux;
        msg += "Impuesto 15%: " + impuestoAux + "\n";
    }

    if (salario > 2364000) {
        let base;
        if (salario > 4727000) {
            base = 4727000 - 2364000;
        } else {
            base = salario - 2364000;
        }
        let impuestoAux = base * 0.20;
        impuesto += impuestoAux;
        msg += "Impuesto 20%: " + impuestoAux + "\n";
    }

    if (salario > 4727000) {
        let base = salario - 4727000;
        let impuestoAux = base * 0.25;
        impuesto += impuestoAux;
        msg += "Impuesto 25%: " + impuestoAux + "\n";
    }
    resultado.innerHTML = `<p>Detalle:${msg}</p>
    <p>Impuestos totales:${impuesto}</p>
	<p>Salario neto:${salario - impuesto}</p>
	<p>Salario bruto:${salario}</p>`;
    //console.log(msg);
    //console.log("Impuesto total:", impuesto);
    //console.log("Salario neto:", salario - impuesto);
});


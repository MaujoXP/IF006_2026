const express = require('express');
const config = require('./config');
const app = express();

// Importacion de rutas
const rutasCategoria        = require('./routes/categoria-routes');
const rutasProveedor        = require('./routes/proveedor-routes');
const rutasProducto         = require('./routes/producto-routes');
const rutasOrdenCompra      = require('./routes/ordencompra-routes');
const rutasOrdenCompraDetalle = require('./routes/ordencompradetalle-routes');
const rutasVenta            = require('./routes/venta-routes');
const rutasVentaDetalle     = require('./routes/ventadetalle-routes');

// Permite leer datos json que se envian en el cuerpo de las peticiones
app.use(express.json());
// Permite leer los datos enviados desde un formulario html
app.use(express.urlencoded({ extended: true }));

// Rutas
app.use('/categoria',             rutasCategoria);
app.use('/proveedor',             rutasProveedor);
app.use('/producto',              rutasProducto);
app.use('/ordencompra',           rutasOrdenCompra);
app.use('/ordencompradetalle',    rutasOrdenCompraDetalle);
app.use('/venta',                 rutasVenta);
app.use('/ventadetalle',          rutasVentaDetalle);

app.set('port', config.app.port);
module.exports = app;

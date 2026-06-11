const express = require('express');

const config = require('./config');

const app = express();

//importacion de rutas
const rutasCategoria = require('./routes/categoria-routes');

//Permite leer datos json que se envian en el cuerpo de las peticiones
app.use(express.json());

//Permite leer los datos enviados desde un formulario html
app.use(express.urlencoded({extend:true}));

app.use('/categoria', rutasCategoria);

app.set('port', config.app.port);

module.exports = app;
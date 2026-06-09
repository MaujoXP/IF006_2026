const express = require('express');

const config = require('./config');

const app = express();

//importacion de rutas
const rutasCategoria = require('./routes/categoria-routes');

//uso de las rutas
app.use('/categoria', rutasCategoria);

app.get('/', function(req, res) {
	res.send('Dih');
});

app.get('/saludar', function(req, res) {
	res.send('Hola');
});

var persona = {
	idPersona : 25,
	nombre : 'Mauricio',
	edad : 21
};

app.get('/persona/:cedula', function(req, res) {
	const cedula = req.params.cedula;
	res.json({
		mensaje: 'Persona solicitada',
		cedula: cedula
	});
});

app.get('/personas/listar', function(req, res) {
	var personas = [{
		idPersona: 25,
		nombre: 'Michelle',
		edad: 23
	}, {
		idPersona: 25,
		nombre: 'Mauricio',
		edad: 21
	}, {
		idPersona: 95,
		nombre: 'Gabriel',
		edad: 15
	}];
	res.json(personas);
});

app.set('port', config.app.port);

module.exports = app;
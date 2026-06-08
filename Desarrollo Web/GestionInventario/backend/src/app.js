const express = require('express');

const config = require('./config');

const app = express();

app.get('/', (req, res) => {
	res.send('Hola mundo!');
});

app.set('port', config.app.port);

module.exports = app;
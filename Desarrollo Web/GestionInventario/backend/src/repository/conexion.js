const mysql = require('mysql2');

const config = require('../config.js');

const conexion = mysql.createConnection({
	host: config.mysql.host,
	user: config.mysql.user,
	password: config.mysql.password,
	database: config.mysql.database
});

conexion.connect((error) => {
	if(error) {
		console.error('Error de conexión');
		return;
	}
	console.log('Conectado a mysql');
})

module.exports = conexion;
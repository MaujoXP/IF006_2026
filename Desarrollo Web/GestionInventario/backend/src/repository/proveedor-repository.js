const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject) => {
		conexion.query(
			'SELECT IdProveedor, Nombre, Telefono FROM Proveedor',
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

function obtenerPorId(id) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'SELECT IdProveedor, Nombre, Telefono FROM Proveedor WHERE IdProveedor = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado[0] || null);
			}
		);
	});
}

function agregar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'INSERT INTO Proveedor(Nombre, Telefono) VALUES(?, ?)',
			[datos.Nombre, datos.Telefono],
			(error, resultado) => {
				if (error) return reject(error);
				resolve({ IdProveedor: resultado.insertId, ...datos });
			}
		);
	});
}

function editar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'UPDATE Proveedor SET Nombre = ?, Telefono = ? WHERE IdProveedor = ?',
			[datos.Nombre, datos.Telefono, datos.IdProveedor],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

function eliminar(id) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'DELETE FROM Proveedor WHERE IdProveedor = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

module.exports = { listar, obtenerPorId, agregar, editar, eliminar };

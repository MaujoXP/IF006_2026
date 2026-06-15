const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject) => {
		conexion.query(
			'SELECT IdVenta, FechaVenta FROM Venta',
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
			'SELECT IdVenta, FechaVenta FROM Venta WHERE IdVenta = ?',
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
			'INSERT INTO Venta(FechaVenta) VALUES(?)',
			[datos.FechaVenta || new Date()],
			(error, resultado) => {
				if (error) return reject(error);
				resolve({ IdVenta: resultado.insertId, ...datos });
			}
		);
	});
}

function editar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'UPDATE Venta SET FechaVenta = ? WHERE IdVenta = ?',
			[datos.FechaVenta, datos.IdVenta],
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
			'DELETE FROM Venta WHERE IdVenta = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

module.exports = { listar, obtenerPorId, agregar, editar, eliminar };

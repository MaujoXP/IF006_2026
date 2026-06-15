const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject) => {
		conexion.query(
			`SELECT vd.IdVenta_Detalle, vd.IdVenta, vd.IdProducto,
			        p.Nombre AS NombreProducto, vd.cantidad, vd.PrecioVenta
			 FROM Venta_Detalle vd
			 LEFT JOIN Producto p ON vd.IdProducto = p.IdProducto`,
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

function listarPorVenta(idVenta) {
	return new Promise((resolve, reject) => {
		conexion.query(
			`SELECT vd.IdVenta_Detalle, vd.IdVenta, vd.IdProducto,
			        p.Nombre AS NombreProducto, vd.cantidad, vd.PrecioVenta
			 FROM Venta_Detalle vd
			 LEFT JOIN Producto p ON vd.IdProducto = p.IdProducto
			 WHERE vd.IdVenta = ?`,
			[idVenta],
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
			`SELECT vd.IdVenta_Detalle, vd.IdVenta, vd.IdProducto,
			        p.Nombre AS NombreProducto, vd.cantidad, vd.PrecioVenta
			 FROM Venta_Detalle vd
			 LEFT JOIN Producto p ON vd.IdProducto = p.IdProducto
			 WHERE vd.IdVenta_Detalle = ?`,
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
			'INSERT INTO Venta_Detalle(IdVenta, IdProducto, cantidad, PrecioVenta) VALUES(?, ?, ?, ?)',
			[datos.IdVenta, datos.IdProducto, datos.cantidad, datos.PrecioVenta],
			(error, resultado) => {
				if (error) return reject(error);
				resolve({ IdVenta_Detalle: resultado.insertId, ...datos });
			}
		);
	});
}

function editar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'UPDATE Venta_Detalle SET IdVenta = ?, IdProducto = ?, cantidad = ?, PrecioVenta = ? WHERE IdVenta_Detalle = ?',
			[datos.IdVenta, datos.IdProducto, datos.cantidad, datos.PrecioVenta, datos.IdVenta_Detalle],
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
			'DELETE FROM Venta_Detalle WHERE IdVenta_Detalle = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

module.exports = { listar, listarPorVenta, obtenerPorId, agregar, editar, eliminar };

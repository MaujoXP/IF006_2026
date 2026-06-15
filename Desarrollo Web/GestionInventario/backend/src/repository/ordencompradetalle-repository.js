const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject) => {
		conexion.query(
			`SELECT od.IdOrdenCompra_Detalle, od.IdOrdenCompra, od.IdProducto,
			        pr.Nombre AS NombreProducto, od.Cantidad, od.PrecioCompra
			 FROM OrdenCompra_Detalle od
			 LEFT JOIN Producto pr ON od.IdProducto = pr.IdProducto`,
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

function listarPorOrden(idOrdenCompra) {
	return new Promise((resolve, reject) => {
		conexion.query(
			`SELECT od.IdOrdenCompra_Detalle, od.IdOrdenCompra, od.IdProducto,
			        pr.Nombre AS NombreProducto, od.Cantidad, od.PrecioCompra
			 FROM OrdenCompra_Detalle od
			 LEFT JOIN Producto pr ON od.IdProducto = pr.IdProducto
			 WHERE od.IdOrdenCompra = ?`,
			[idOrdenCompra],
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
			`SELECT od.IdOrdenCompra_Detalle, od.IdOrdenCompra, od.IdProducto,
			        pr.Nombre AS NombreProducto, od.Cantidad, od.PrecioCompra
			 FROM OrdenCompra_Detalle od
			 LEFT JOIN Producto pr ON od.IdProducto = pr.IdProducto
			 WHERE od.IdOrdenCompra_Detalle = ?`,
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
			'INSERT INTO OrdenCompra_Detalle(IdOrdenCompra, IdProducto, Cantidad, PrecioCompra) VALUES(?, ?, ?, ?)',
			[datos.IdOrdenCompra, datos.IdProducto, datos.Cantidad, datos.PrecioCompra],
			(error, resultado) => {
				if (error) return reject(error);
				resolve({ IdOrdenCompra_Detalle: resultado.insertId, ...datos });
			}
		);
	});
}

function editar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'UPDATE OrdenCompra_Detalle SET IdOrdenCompra = ?, IdProducto = ?, Cantidad = ?, PrecioCompra = ? WHERE IdOrdenCompra_Detalle = ?',
			[datos.IdOrdenCompra, datos.IdProducto, datos.Cantidad, datos.PrecioCompra, datos.IdOrdenCompra_Detalle],
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
			'DELETE FROM OrdenCompra_Detalle WHERE IdOrdenCompra_Detalle = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

module.exports = { listar, listarPorOrden, obtenerPorId, agregar, editar, eliminar };

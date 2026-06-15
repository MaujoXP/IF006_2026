const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject) => {
		conexion.query(
			`SELECT oc.IdOrdenCompra, oc.IdProveedorrr, p.Nombre AS NombreProveedor, 
			        oc.FechaCompra, oc.estado 
			 FROM OrdenCompra oc
			 LEFT JOIN Proveedor p ON oc.IdProveedorrr = p.IdProveedor`,
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
			`SELECT oc.IdOrdenCompra, oc.IdProveedorrr, p.Nombre AS NombreProveedor, 
			        oc.FechaCompra, oc.estado 
			 FROM OrdenCompra oc
			 LEFT JOIN Proveedor p ON oc.IdProveedorrr = p.IdProveedor
			 WHERE oc.IdOrdenCompra = ?`,
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
			'INSERT INTO OrdenCompra(IdProveedorrr, FechaCompra, estado) VALUES(?, ?, ?)',
			[datos.IdProveedorrr, datos.FechaCompra, datos.estado],
			(error, resultado) => {
				if (error) return reject(error);
				resolve({ IdOrdenCompra: resultado.insertId, ...datos });
			}
		);
	});
}

function editar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			'UPDATE OrdenCompra SET IdProveedorrr = ?, FechaCompra = ?, estado = ? WHERE IdOrdenCompra = ?',
			[datos.IdProveedorrr, datos.FechaCompra, datos.estado, datos.IdOrdenCompra],
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
			'DELETE FROM OrdenCompra WHERE IdOrdenCompra = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

module.exports = { listar, obtenerPorId, agregar, editar, eliminar };

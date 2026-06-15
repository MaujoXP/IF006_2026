const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject) => {
		conexion.query(
			`SELECT p.IdProducto, p.IdCategoria, c.Nombre AS NombreCategoria,
			        p.Nombre, p.Descripcion, p.PrecioActual,
			        p.InventarioActual, p.InventarioMinimo, p.FechaRegistro
			 FROM Producto p
			 LEFT JOIN Categoria c ON p.IdCategoria = c.IdCategoria`,
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
			`SELECT p.IdProducto, p.IdCategoria, c.Nombre AS NombreCategoria,
			        p.Nombre, p.Descripcion, p.PrecioActual,
			        p.InventarioActual, p.InventarioMinimo, p.FechaRegistro
			 FROM Producto p
			 LEFT JOIN Categoria c ON p.IdCategoria = c.IdCategoria
			 WHERE p.IdProducto = ?`,
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
			`INSERT INTO Producto(IdCategoria, Nombre, Descripcion, PrecioActual, InventarioActual, InventarioMinimo, FechaRegistro)
			 VALUES(?, ?, ?, ?, ?, ?, ?)`,
			[datos.IdCategoria, datos.Nombre, datos.Descripcion, datos.PrecioActual,
			 datos.InventarioActual, datos.InventarioMinimo, datos.FechaRegistro || new Date()],
			(error, resultado) => {
				if (error) return reject(error);
				resolve({ IdProducto: resultado.insertId, ...datos });
			}
		);
	});
}

function editar(datos) {
	return new Promise((resolve, reject) => {
		conexion.query(
			`UPDATE Producto SET IdCategoria = ?, Nombre = ?, Descripcion = ?,
			 PrecioActual = ?, InventarioActual = ?, InventarioMinimo = ?
			 WHERE IdProducto = ?`,
			[datos.IdCategoria, datos.Nombre, datos.Descripcion,
			 datos.PrecioActual, datos.InventarioActual, datos.InventarioMinimo, datos.IdProducto],
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
			'DELETE FROM Producto WHERE IdProducto = ?',
			[id],
			(error, resultado) => {
				if (error) return reject(error);
				resolve(resultado);
			}
		);
	});
}

module.exports = { listar, obtenerPorId, agregar, editar, eliminar };

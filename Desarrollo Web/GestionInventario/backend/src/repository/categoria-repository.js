const conexion = require('./conexion');

function listar() {
	return new Promise((resolve, reject)=>{
		conexion.query('SELECT idCategoria, Nombre, Descripcion FROM Categoria',
			(error, resultado)=>{
				if(error) return reject(error);

				resolve(resultado);
			});
	});
}

function obtenerPorId(id) {
	return new Promise((resolve, reject)=>{
		conexion.query('SELECT IdCategoria, Nombre, Descripcion FROM Categoria WHERE IdCategoria = ?',
			[id], (error, resultado)=>{
				if(error) return reject(error);

				resolve(resultado[0] || null);
			});
	})
}

function agregar(datos) {
	return new Promise((resolve, reject)=>{
		conexion.query('INSERT INTO Categoria(Nombre, Descripcion) VALUES(?, ?)', [datos.Nombre, datos.Descripcion],
			(error, resultado)=>{
				if(error) return reject(error);

				resolve({IdCategoria: resultado.insertId, ...datos});
			});
	})
}

function editar(datos) {
	return new Promise((resolve, reject)=>{
		conexion.query('UPDATE Categoria SET Nombre = ?, Descripcion = ? WHERE IdCategoria = ?', [datos.Nombre, datos.Descripcion, datos.IdCategoria],
			(error, resultado)=>{
				if(error) return reject(error);

				resolve(resultado);
			});
	})
}

function eliminar(id) {
	return new Promise((resolve, reject)=>{
		conexion.query('DELETE FROM Categoria WHERE IdCategoria = ?', [id],
			(error, resultado)=>{
				if(error) return reject(error);

				resolve(resultado);
			});
	})
}

module.exports = {
	listar,
	obtenerPorId,
	agregar,
	editar,
	eliminar
};
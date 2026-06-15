const repositorio = require('../repository/producto-repository');
const respuesta = require('../util/respuesta');

async function listarProductos(req, res) {
	try {
		const productos = await repositorio.listar();
		respuesta.success(req, res, productos, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function obtenerProductoPorId(req, res) {
	try {
		const id = req.params.id;
		const producto = await repositorio.obtenerPorId(id);
		if (!producto) {
			return respuesta.error(req, res, `Producto con ID ${id} no encontrado`, 404);
		}
		respuesta.success(req, res, producto, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarProducto(req, res) {
	try {
		const { IdCategoria, Nombre, Descripcion, PrecioActual, InventarioActual, InventarioMinimo } = req.body;
		if (!Nombre) {
			return respuesta.error(req, res, 'El nombre del producto es requerido', 400);
		}
		if (!IdCategoria) {
			return respuesta.error(req, res, 'La categoría es requerida', 400);
		}
		if (!PrecioActual || PrecioActual <= 0) {
			return respuesta.error(req, res, 'El precio debe ser mayor a 0', 400);
		}
		const nuevoProducto = await repositorio.agregar({
			IdCategoria,
			Nombre,
			Descripcion: Descripcion || '',
			PrecioActual,
			InventarioActual: InventarioActual || 0,
			InventarioMinimo: InventarioMinimo || 0,
			FechaRegistro: new Date()
		});
		respuesta.success(req, res, nuevoProducto, 201);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarProducto(req, res) {
	try {
		const { IdProducto, IdCategoria, Nombre, Descripcion, PrecioActual, InventarioActual, InventarioMinimo } = req.body;
		if (!Nombre) {
			return respuesta.error(req, res, 'El nombre del producto es requerido', 400);
		}
		if (!IdCategoria) {
			return respuesta.error(req, res, 'La categoría es requerida', 400);
		}
		const existe = await repositorio.obtenerPorId(IdProducto);
		if (!existe) {
			return respuesta.error(req, res, `Producto con ID ${IdProducto} no encontrado`, 404);
		}
		await repositorio.editar({
			IdProducto,
			IdCategoria,
			Nombre,
			Descripcion: Descripcion || '',
			PrecioActual,
			InventarioActual: InventarioActual || 0,
			InventarioMinimo: InventarioMinimo || 0
		});
		respuesta.success(req, res, 'Producto actualizado con éxito', 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarProducto(req, res) {
	try {
		const id = req.params.id;
		const existe = await repositorio.obtenerPorId(id);
		if (!existe) {
			return respuesta.error(req, res, `Producto con ID ${id} no encontrado`, 404);
		}
		await repositorio.eliminar(id);
		respuesta.success(req, res, `Producto con ID ${id} eliminado correctamente`, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarProductos,
	obtenerProductoPorId,
	agregarProducto,
	editarProducto,
	eliminarProducto
};

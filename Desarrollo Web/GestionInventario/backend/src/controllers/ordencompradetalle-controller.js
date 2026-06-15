const repositorio = require('../repository/ordencompradetalle-repository');
const respuesta = require('../util/respuesta');

async function listarDetalles(req, res) {
	try {
		const detalles = await repositorio.listar();
		respuesta.success(req, res, detalles, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function listarDetallesPorOrden(req, res) {
	try {
		const idOrdenCompra = req.params.idOrdenCompra;
		const detalles = await repositorio.listarPorOrden(idOrdenCompra);
		respuesta.success(req, res, detalles, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function obtenerDetallePorId(req, res) {
	try {
		const id = req.params.id;
		const detalle = await repositorio.obtenerPorId(id);
		if (!detalle) {
			return respuesta.error(req, res, `Detalle con ID ${id} no encontrado`, 404);
		}
		respuesta.success(req, res, detalle, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarDetalle(req, res) {
	try {
		const { IdOrdenCompra, IdProducto, Cantidad, PrecioCompra } = req.body;
		if (!IdOrdenCompra || !IdProducto) {
			return respuesta.error(req, res, 'IdOrdenCompra e IdProducto son requeridos', 400);
		}
		if (!Cantidad || Cantidad <= 0) {
			return respuesta.error(req, res, 'La cantidad debe ser mayor a 0', 400);
		}
		if (!PrecioCompra || PrecioCompra <= 0) {
			return respuesta.error(req, res, 'El precio de compra debe ser mayor a 0', 400);
		}
		const nuevoDetalle = await repositorio.agregar({ IdOrdenCompra, IdProducto, Cantidad, PrecioCompra });
		respuesta.success(req, res, nuevoDetalle, 201);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarDetalle(req, res) {
	try {
		const { IdOrdenCompra_Detalle, IdOrdenCompra, IdProducto, Cantidad, PrecioCompra } = req.body;
		if (!IdOrdenCompra || !IdProducto) {
			return respuesta.error(req, res, 'IdOrdenCompra e IdProducto son requeridos', 400);
		}
		const existe = await repositorio.obtenerPorId(IdOrdenCompra_Detalle);
		if (!existe) {
			return respuesta.error(req, res, `Detalle con ID ${IdOrdenCompra_Detalle} no encontrado`, 404);
		}
		await repositorio.editar({ IdOrdenCompra_Detalle, IdOrdenCompra, IdProducto, Cantidad, PrecioCompra });
		respuesta.success(req, res, 'Detalle actualizado con éxito', 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarDetalle(req, res) {
	try {
		const id = req.params.id;
		const existe = await repositorio.obtenerPorId(id);
		if (!existe) {
			return respuesta.error(req, res, `Detalle con ID ${id} no encontrado`, 404);
		}
		await repositorio.eliminar(id);
		respuesta.success(req, res, `Detalle con ID ${id} eliminado correctamente`, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarDetalles,
	listarDetallesPorOrden,
	obtenerDetallePorId,
	agregarDetalle,
	editarDetalle,
	eliminarDetalle
};

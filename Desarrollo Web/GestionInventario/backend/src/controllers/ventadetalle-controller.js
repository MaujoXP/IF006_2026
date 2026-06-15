const repositorio = require('../repository/ventadetalle-repository');
const respuesta = require('../util/respuesta');

async function listarDetalles(req, res) {
	try {
		const detalles = await repositorio.listar();
		respuesta.success(req, res, detalles, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function listarDetallesPorVenta(req, res) {
	try {
		const idVenta = req.params.idVenta;
		const detalles = await repositorio.listarPorVenta(idVenta);
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
			return respuesta.error(req, res, `Detalle de venta con ID ${id} no encontrado`, 404);
		}
		respuesta.success(req, res, detalle, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarDetalle(req, res) {
	try {
		const { IdVenta, IdProducto, cantidad, PrecioVenta } = req.body;
		if (!IdVenta || !IdProducto) {
			return respuesta.error(req, res, 'IdVenta e IdProducto son requeridos', 400);
		}
		if (!cantidad || cantidad <= 0) {
			return respuesta.error(req, res, 'La cantidad debe ser mayor a 0', 400);
		}
		if (!PrecioVenta || PrecioVenta <= 0) {
			return respuesta.error(req, res, 'El precio de venta debe ser mayor a 0', 400);
		}
		const nuevoDetalle = await repositorio.agregar({ IdVenta, IdProducto, cantidad, PrecioVenta });
		respuesta.success(req, res, nuevoDetalle, 201);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarDetalle(req, res) {
	try {
		const { IdVenta_Detalle, IdVenta, IdProducto, cantidad, PrecioVenta } = req.body;
		if (!IdVenta || !IdProducto) {
			return respuesta.error(req, res, 'IdVenta e IdProducto son requeridos', 400);
		}
		const existe = await repositorio.obtenerPorId(IdVenta_Detalle);
		if (!existe) {
			return respuesta.error(req, res, `Detalle de venta con ID ${IdVenta_Detalle} no encontrado`, 404);
		}
		await repositorio.editar({ IdVenta_Detalle, IdVenta, IdProducto, cantidad, PrecioVenta });
		respuesta.success(req, res, 'Detalle de venta actualizado con éxito', 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarDetalle(req, res) {
	try {
		const id = req.params.id;
		const existe = await repositorio.obtenerPorId(id);
		if (!existe) {
			return respuesta.error(req, res, `Detalle de venta con ID ${id} no encontrado`, 404);
		}
		await repositorio.eliminar(id);
		respuesta.success(req, res, `Detalle de venta con ID ${id} eliminado correctamente`, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarDetalles,
	listarDetallesPorVenta,
	obtenerDetallePorId,
	agregarDetalle,
	editarDetalle,
	eliminarDetalle
};

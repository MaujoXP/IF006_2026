const repositorio = require('../repository/venta-repository');
const respuesta = require('../util/respuesta');

async function listarVentas(req, res) {
	try {
		const ventas = await repositorio.listar();
		respuesta.success(req, res, ventas, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function obtenerVentaPorId(req, res) {
	try {
		const id = req.params.id;
		const venta = await repositorio.obtenerPorId(id);
		if (!venta) {
			return respuesta.error(req, res, `Venta con ID ${id} no encontrada`, 404);
		}
		respuesta.success(req, res, venta, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarVenta(req, res) {
	try {
		const { FechaVenta } = req.body;
		const nuevaVenta = await repositorio.agregar({
			FechaVenta: FechaVenta || new Date()
		});
		respuesta.success(req, res, nuevaVenta, 201);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarVenta(req, res) {
	try {
		const { IdVenta, FechaVenta } = req.body;
		const existe = await repositorio.obtenerPorId(IdVenta);
		if (!existe) {
			return respuesta.error(req, res, `Venta con ID ${IdVenta} no encontrada`, 404);
		}
		await repositorio.editar({ IdVenta, FechaVenta });
		respuesta.success(req, res, 'Venta actualizada con éxito', 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarVenta(req, res) {
	try {
		const id = req.params.id;
		const existe = await repositorio.obtenerPorId(id);
		if (!existe) {
			return respuesta.error(req, res, `Venta con ID ${id} no encontrada`, 404);
		}
		await repositorio.eliminar(id);
		respuesta.success(req, res, `Venta con ID ${id} eliminada correctamente`, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarVentas,
	obtenerVentaPorId,
	agregarVenta,
	editarVenta,
	eliminarVenta
};

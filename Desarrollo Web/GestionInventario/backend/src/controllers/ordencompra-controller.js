const repositorio = require('../repository/ordencompra-repository');
const respuesta = require('../util/respuesta');

async function listarOrdenesCompra(req, res) {
	try {
		const ordenes = await repositorio.listar();
		respuesta.success(req, res, ordenes, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function obtenerOrdenCompraPorId(req, res) {
	try {
		const id = req.params.id;
		const orden = await repositorio.obtenerPorId(id);
		if (!orden) {
			return respuesta.error(req, res, `Orden de compra con ID ${id} no encontrada`, 404);
		}
		respuesta.success(req, res, orden, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarOrdenCompra(req, res) {
	try {
		const { IdProveedorrr, FechaCompra, estado } = req.body;
		if (!IdProveedorrr) {
			return respuesta.error(req, res, 'El proveedor es requerido', 400);
		}
		if (!estado) {
			return respuesta.error(req, res, 'El estado es requerido', 400);
		}
		const nuevaOrden = await repositorio.agregar({
			IdProveedorrr,
			FechaCompra: FechaCompra || new Date(),
			estado
		});
		respuesta.success(req, res, nuevaOrden, 201);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarOrdenCompra(req, res) {
	try {
		const { IdOrdenCompra, IdProveedorrr, FechaCompra, estado } = req.body;
		if (!IdProveedorrr) {
			return respuesta.error(req, res, 'El proveedor es requerido', 400);
		}
		if (!estado) {
			return respuesta.error(req, res, 'El estado es requerido', 400);
		}
		const existe = await repositorio.obtenerPorId(IdOrdenCompra);
		if (!existe) {
			return respuesta.error(req, res, `Orden de compra con ID ${IdOrdenCompra} no encontrada`, 404);
		}
		await repositorio.editar({ IdOrdenCompra, IdProveedorrr, FechaCompra, estado });
		respuesta.success(req, res, 'Orden de compra actualizada con éxito', 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarOrdenCompra(req, res) {
	try {
		const id = req.params.id;
		const existe = await repositorio.obtenerPorId(id);
		if (!existe) {
			return respuesta.error(req, res, `Orden de compra con ID ${id} no encontrada`, 404);
		}
		await repositorio.eliminar(id);
		respuesta.success(req, res, `Orden de compra con ID ${id} eliminada correctamente`, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarOrdenesCompra,
	obtenerOrdenCompraPorId,
	agregarOrdenCompra,
	editarOrdenCompra,
	eliminarOrdenCompra
};

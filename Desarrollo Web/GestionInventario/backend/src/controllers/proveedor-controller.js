const repositorio = require('../repository/proveedor-repository');
const respuesta = require('../util/respuesta');

async function listarProveedores(req, res) {
	try {
		const proveedores = await repositorio.listar();
		respuesta.success(req, res, proveedores, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function obtenerProveedorPorId(req, res) {
	try {
		const id = req.params.id;
		const proveedor = await repositorio.obtenerPorId(id);
		if (!proveedor) {
			return respuesta.error(req, res, `Proveedor con ID ${id} no encontrado`, 404);
		}
		respuesta.success(req, res, proveedor, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarProveedor(req, res) {
	try {
		const { Nombre, Telefono } = req.body;
		if (!Nombre) {
			return respuesta.error(req, res, 'El nombre del proveedor es requerido', 400);
		}
		const nuevoProveedor = await repositorio.agregar({
			Nombre,
			Telefono: Telefono || ''
		});
		respuesta.success(req, res, nuevoProveedor, 201);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarProveedor(req, res) {
	try {
		const { IdProveedor, Nombre, Telefono } = req.body;
		if (!Nombre) {
			return respuesta.error(req, res, 'El nombre del proveedor es requerido', 400);
		}
		const existe = await repositorio.obtenerPorId(IdProveedor);
		if (!existe) {
			return respuesta.error(req, res, `Proveedor con ID ${IdProveedor} no encontrado`, 404);
		}
		await repositorio.editar({ IdProveedor, Nombre, Telefono: Telefono || '' });
		respuesta.success(req, res, 'Proveedor actualizado con éxito', 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarProveedor(req, res) {
	try {
		const id = req.params.id;
		const existe = await repositorio.obtenerPorId(id);
		if (!existe) {
			return respuesta.error(req, res, `Proveedor con ID ${id} no encontrado`, 404);
		}
		await repositorio.eliminar(id);
		respuesta.success(req, res, `Proveedor con ID ${id} eliminado correctamente`, 200);
	} catch (error) {
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarProveedores,
	obtenerProveedorPorId,
	agregarProveedor,
	editarProveedor,
	eliminarProveedor
};

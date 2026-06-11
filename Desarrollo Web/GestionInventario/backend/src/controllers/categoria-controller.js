const repositorio = require('../repository/categoria-repository');
const respuesta = require('../util/respuesta');

async function listarCategorias(req, res){
	try{
		const categorias = await repositorio.listar();
		respuesta.success(req, res, categorias, 200);
	} catch(error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function obtenerCategoriaPorId(req, res) {
	try{
		const id = req.params.id;
		const categoria = await repositorio.obtenerPorId(id);
		if(!categoria){
			respuesta.error(req, res, `Categoria con ID ${id} no encontrado`, 404);
			
		}
		respuesta.success(req, res, categoria, 200);
	} catch(error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function agregarCategoria(req, res) {
	try{
		const {Nombre, Descripcion} = req.body;
		if(!Nombre){
			return respuesta.error(req, res, 'El nombre de la categoria es requerido', 404);
		}

		const nuevaCategoria = await repositorio.agregar({
			Nombre,
			Descripcion: Descripcion || ''
		});

		respuesta.success(req, res, nuevaCategoria, 201);
	} catch(error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function editarCategoria(req, res) {
	try{
		const {IdCategoria, Nombre, Descripcion} = req.body;
		if(!Nombre){
			return respuesta.error(req, res, 'El nombre de la categoria es requerido', 404);
		}

		const existe = await repositorio.obtenerPorId(IdCategoria);
		if(!existe) {
			return respuesta.error(req, res, `Categoria con ID ${IdCategoria} no encontrada`, 404);
		}

		await repositorio.editar({
		 	IdCategoria,
			Nombre,
			Descripcion: Descripcion || ''
		});

		respuesta.success(req, res, 'Los datos se actualizaron con éxito', 200);
	} catch(error) {
		respuesta.error(req, res, error.message, 500);
	}
}

async function eliminarCategoria(req, res) {
	try {
		const id = req.params.id;

		const existe = await repositorio.obtenerPorId(id);
		if(!existe) {
			return respuesta.error(req, res, `Categoria con ID ${IdCategoria} no encontrada`, 404);
		}

		await repositorio.eliminar(id);

		respuesta.success(req, res, `Categoria con ID ${id} eliminada correctamente`);

	} catch(error){
		respuesta.error(req, res, error.message, 500);
	}
}

module.exports = {
	listarCategorias,
	obtenerCategoriaPorId,
	agregarCategoria,
	editarCategoria,
	eliminarCategoria
};
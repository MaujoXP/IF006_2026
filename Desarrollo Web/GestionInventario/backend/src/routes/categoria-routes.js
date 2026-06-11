const router = require('express').Router();

const controlador = require('../controllers/categoria-controller');

router.get('/', controlador.listarCategorias);
router.get('/:id', controlador.obtenerCategoriaPorId);
router.post('/agregar', controlador.agregarCategoria);
router.put('/editar', controlador.editarCategoria);
router.delete('/eliminar/:id', controlador.eliminarCategoria);

module.exports = router;
const router = require('express').Router();
const controlador = require('../controllers/producto-controller');

router.get('/', controlador.listarProductos);
router.get('/:id', controlador.obtenerProductoPorId);
router.post('/agregar', controlador.agregarProducto);
router.put('/editar', controlador.editarProducto);
router.delete('/eliminar/:id', controlador.eliminarProducto);

module.exports = router;

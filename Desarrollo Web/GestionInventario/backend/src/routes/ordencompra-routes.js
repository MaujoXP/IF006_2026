const router = require('express').Router();
const controlador = require('../controllers/ordencompra-controller');

router.get('/', controlador.listarOrdenesCompra);
router.get('/:id', controlador.obtenerOrdenCompraPorId);
router.post('/agregar', controlador.agregarOrdenCompra);
router.put('/editar', controlador.editarOrdenCompra);
router.delete('/eliminar/:id', controlador.eliminarOrdenCompra);

module.exports = router;

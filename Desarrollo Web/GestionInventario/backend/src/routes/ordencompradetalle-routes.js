const router = require('express').Router();
const controlador = require('../controllers/ordencompradetalle-controller');

router.get('/', controlador.listarDetalles);
router.get('/orden/:idOrdenCompra', controlador.listarDetallesPorOrden);
router.get('/:id', controlador.obtenerDetallePorId);
router.post('/agregar', controlador.agregarDetalle);
router.put('/editar', controlador.editarDetalle);
router.delete('/eliminar/:id', controlador.eliminarDetalle);

module.exports = router;

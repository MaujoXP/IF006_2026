const router = require('express').Router();
const controlador = require('../controllers/venta-controller');

router.get('/', controlador.listarVentas);
router.get('/:id', controlador.obtenerVentaPorId);
router.post('/agregar', controlador.agregarVenta);
router.put('/editar', controlador.editarVenta);
router.delete('/eliminar/:id', controlador.eliminarVenta);

module.exports = router;

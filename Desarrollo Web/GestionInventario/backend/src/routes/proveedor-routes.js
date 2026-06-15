const router = require('express').Router();
const controlador = require('../controllers/proveedor-controller');

router.get('/', controlador.listarProveedores);
router.get('/:id', controlador.obtenerProveedorPorId);
router.post('/agregar', controlador.agregarProveedor);
router.put('/editar', controlador.editarProveedor);
router.delete('/eliminar/:id', controlador.eliminarProveedor);

module.exports = router;

const express = require('express');
const respuesta = require('../../red/respuestas');
const controlador = require('./index');

const router = express.Router();

router.get('/', todos);
router.get('/:id', uno);
router.post('/', agregar);
router.put('/', eliminar);

async function todos(req, res, next) {
    try {
        const items = await controlador.todos();
        respuesta.success(req, res, items, 200);
    } catch (err) {
        next(err);
    }
};

async function uno(req, res, next) {
    try {
        const item = await controlador.uno(req.params.id);
        respuesta.success(req, res, item, 200);
    } catch (err) {
        next(err);
    }
};

async function agregar(req, res, next) {
    try {
        const item = await controlador.agregar(req.body);
        if(req.body.error == 0) {
            mensaje = 'Item guardado con éxito';
        } else {
            mensaje = 'Item actualizado con éxito';
        }
        respuesta.success(req, res, mensaje, 201);
    } catch (err) {
        next(err);
    }
};

async function eliminar(req, res, next) {
    try {
        const item = await controlador.eliminar(req.body);
        if(req.body.error == 0) {
            mensaje = 'Item eliminado con éxito';
        } else {
            mensaje = 'Error al eliminar el item';
        }
        respuesta.success(req, res, { mensaje }, 200);
    } catch (err) {
        next(err);
    }
};

module.exports = router;
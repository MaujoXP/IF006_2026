const router = require('express').Router();

const categorias = [
	{id:1, nombre:'Verduras', descripcion: ''},
	{id:2, nombre:'Frutas', descripcion: ''},
	{id:3, nombre:'Enlatados', descripcion: ''},
	{id:4, nombre:'Carnes', descripcion: 'Res, pollo, cerdo'}
];

router.get('/', function(req, res) {
	res.send('Estamos en categorias');
});

router.get('/listar', function(req, res) {
	res.send(categorias);
});

module.exports = router;
exports.success = function(req, res, mensaje, status) {
	res.status(status || 200).send({
		error: false,
		status: status,
		body: mensaje || 'Éxito'
	});
}

exports.error = function(req, res, mensaje, status) {
	res.status(status || 500).send({
		error: true,
		status: status,
		body: mensaje || 'Ocurrión un error'
	});
}
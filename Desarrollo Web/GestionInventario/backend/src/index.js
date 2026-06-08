const app = require('./app');

const port = app.get('port');

app.listen(port, ()=>{
	console.log(`Èl servidor esta corriendo en el puerto ${port}`);
});
const mysql = require('mysql');
const config = require('../config');

const dbconfig = {
    host: config.mysql.host,
    user: config.mysql.user,
    password: config.mysql.password,
    database: config.mysql.database
}

let conexion;

function conMysql() {
    conexion = mysql.createConnection(dbconfig);

    conexion.connect(function(err){
        if(err){
            console.error('[db err]',  err);
            setTimeout(conMsql, 200);
        } else {
            console.log('DB conectada!!!');
        }
    });

    conexion.on('error', function(err){
        console.error('[db err]',  err);
        if(err.code === 'PROTOCOL_CONNECTION_LOST'){
            conMysql();
        } else {
            throw err;
        }
    });
}

conMysql();

function todos(tabla) {
    return new Promise((resolve, reject) => {
        conexion.query(`SELECT * FROM ${tabla}`, (error, filas) => {
            if(error) return reject(error);
            resolve(filas)
        })
    });
}

function uno(tabla, id) {

}

function agregar(tabla, datos) {

}

function eliminar(tabla, id) {

}

module.exports = {
    todos,
    uno,
    agregar,
    eliminar
}
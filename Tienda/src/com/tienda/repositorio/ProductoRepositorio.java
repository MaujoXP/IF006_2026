/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.repositorio;

import com.tienda.modelo.Producto;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Gaby
 */
public class ProductoRepositorio {
    ConexionDB cnx; 
    
    public ProductoRepositorio() {
        cnx = new ConexionDB();
    }
    
    public int agregar(Producto producto) {
        String sql = "INSERT INTO producto(nombre, categoria, precio, activo) "
                + "VALUES (?,?,?,?)";
        try (
            Connection cn = cnx.obtenerConexion();
            PreparedStatement ps =   
                    cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, producto.getNombre());
                    ps.setString(2, producto.getCategoria());
                    ps.setDouble(3, producto.getPrecio());
                    ps.setBoolean(4, true);
                    
                    int filas = ps.executeUpdate();
                    
                    //Recuperar el ID generado
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if(rs.next()) {
                            int idGenerado = rs.getInt(1); //Columna 1 = id generado
                            return idGenerado;
                        }
                    }
                    
                    return -1;
        } catch(Exception e) {
            System.out.println("Error al insertar el producto" + e.getMessage());
            return -1;
        }
    }
    
    public int eliminar(Producto producto) {
        String sql = "DELETE FROM producto "
                + "WHERE " 
                + "Nombre = ? AND " 
                + producto.getCategoria() + "= ? AND " 
                + String.valueOf(producto.getPrecio()) + "= ?";
        try (
            Connection cn = cnx.obtenerConexion();
            PreparedStatement ps =   
                    cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, producto.getNombre());
                    ps.setString(2, producto.getCategoria());
                    ps.setDouble(3, producto.getPrecio());
                    ps.executeUpdate();
                    return 1;
        } catch(Exception e) {
            System.out.println("Error al insertar el producto" + e.getMessage());
            return -1;
        }
    }
    
    public int actualizar(Producto producto, Producto producto2) {
        String sql = "UPDATE FROM producto "
                + "SET " 
                + "Nombre = " + producto.getNombre()
                + "Categoria = " + producto.getCategoria()
                + String.valueOf(producto.getPrecio()) + "= ?";
        try (
            Connection cn = cnx.obtenerConexion();
            PreparedStatement ps =   
                    cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, producto.getNombre());
                    ps.setString(2, producto.getCategoria());
                    ps.setDouble(3, producto.getPrecio());
                    ps.executeUpdate();
                    return 1;
        } catch(Exception e) {
            System.out.println("Error al insertar el producto" + e.getMessage());
            return -1;
        }
    }
}

package Buses;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
/*Clase Asiento*/
public class Asiento {
    //atributos de la clase

    private int numero;
    private int tipo;
    private int ubicacion;//parte trasera del pasillo
    private int estado;//bueno malo reservado normal

    //metodos constructores
    public Asiento() {
        this.numero = 0;
        this.tipo = 1;
        this.ubicacion = 1;
        this.estado = 1;
    }

    public Asiento(int numero) {
        this.numero = numero;
        this.tipo = 1;
        this.ubicacion = 1;
        this.estado = 1;
    }

    public Asiento(int numero, int tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ubicacion = 1;
        this.estado = 1;
    }

    public Asiento(int numero, int tipo, int ubicacion) {
        this.numero = numero;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.estado = 1;
    }

    public Asiento(int numero, int tipo, int ubicacion, int estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    //metodos
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return this.numero;
    }

    public int getTipo() {
        return this.tipo;
    }

    public int getUbicacion() {
        return this.ubicacion;
    }

    public int getEstado() {
        return this.estado;
    }

    public String getTipoTx() {
        String tipos[] = {"Indefinido", "Normal", "Preferencial", "VIP"};
        return tipos[this.tipo];
    }

    public String getUbicacionTx() {
        String ubicado[] = {"Indefinido", "Pasillo", "Ventana", "Atras"};
        return ubicado[this.ubicacion];
    }

    public String getEstadoTx() {
        String estados[] = {"Indefinido", "Bueno", "Dañado", "Reservado", "Vendido", "Disponible"};
        return estados[this.estado];
    }

    @Override
    public String toString() {
        String sal = "";
        sal += "Asiento Numero: " + this.numero + "\nTipo Asiento: " + getTipoTx();
        sal += "\nUbicacion: " + getUbicacionTx() + "\nEstado del Asiento: " + getEstadoTx();
        return sal;
    }

    public String detalle() {
        String sal = "";
        sal += " " + this.numero + " " + getTipoTx() + "         " + getUbicacionTx() + "  " + getEstadoTx() + "\n";
        return sal;
    }
}

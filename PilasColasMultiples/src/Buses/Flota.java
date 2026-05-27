package Buses;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
/*Clase Flota*/
public class Flota {
    
    //Lista vectorial
    private Bus buses[];
    private int nBuses;
    private String nombre;
    private int apBus;

    //flota que tiene muchos buses
    public Flota() {
        apBus = 1;
        this.nBuses = 10;
        this.nombre = "Chesky";
        this.buses = new Bus[nBuses];
        for (int i = 1; i < nBuses; i++) {
            buses[i] = new Bus();
        }
    }

    public Flota(String nombre, int cantidad) {
        apBus = 1;
        this.nBuses = cantidad;
        this.nombre = nombre;
        this.buses = new Bus[nBuses];
        for (int i = 1; i < nBuses; i++) {
            buses[i] = new Bus();
        }
    }

    public void agregarBus(Bus nuevo) {
        this.buses[apBus++] = nuevo;
    }

    public void agregarBus(int capacidad, String placa) {
        Bus nuevo = new Bus(placa, capacidad);
        this.buses[apBus++] = nuevo;
    }

    public void cambiarCapacidadDeBus(int pos, int capacidad) {
        this.buses[pos].setCapacidad(capacidad);//invoca un metodo de la clase atraves del metodo de bus
    }

    public void cambioTipoDeAsiento(int pos, int numeroAsiento, int tipo) {
        this.buses[pos].setAsiento(numeroAsiento, tipo);
    }

    @Override
    public String toString() {
        String sal = "";
        sal += "\nFlota" + this.nombre
                + "\nLista de Buses: \n";
        for (int i = 1; i < this.nBuses; i++) {
            sal += buses[i].toString();
        }
        return sal;
    }

}

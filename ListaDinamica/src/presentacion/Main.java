/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import generica.ListaGenerica;
import java.time.LocalDate;
import java.util.ArrayList;
import persona.ListaPersonas;
import persona.Persona;
import vehiculo.Vehiculo;
import utilidades.ManejoFechas;
import vehiculo.ListaVehiculos;

/**
 *
 * @author Gaby
 */
public class Main {

    public static void main(String[] args) {
        ListaGenerica listaPersonas2 = new ListaGenerica();
        ListaGenerica listaVehiculos2 = new ListaGenerica();
        ManejoFechas manejoFechas = new ManejoFechas();
        
        ListaPersonas listaPersonas = new ListaPersonas();
        Persona persona = new Persona(
                listaPersonas2.obtenerConsecutivo(),
                "702050316",
                "Roberto Gomez Bolaños",
                LocalDate.of(2000, 12, 25)
        );
        listaPersonas2.agregar(persona);
        listaPersonas.agregar(persona);

        Persona persona2 = new Persona(
                listaPersonas2.obtenerConsecutivo(),
                "305010214",
                "María Fernanda López",
                LocalDate.of(1998, 5, 14)
        );
        listaPersonas2.agregar(persona2);
        listaPersonas.agregar(persona2);

        Persona persona3 = new Persona(
                listaPersonas2.obtenerConsecutivo(),
                "109080512",
                "Carlos Eduardo Ramírez",
                LocalDate.of(1995, 8, 20)
        );
        listaPersonas2.agregar(persona3);
        listaPersonas.agregar(persona3);

        Persona persona4 = new Persona(
                listaPersonas2.obtenerConsecutivo(),
                "506070809",
                "Ana Sofía Hernández",
                LocalDate.of(2002, 3, 9)
        );
        listaPersonas2.agregar(persona4);
        listaPersonas.agregar(persona4);

        Persona persona5 = new Persona(
                listaPersonas2.obtenerConsecutivo(),
                "808182838",
                "Jorge Luis Castillo",
                LocalDate.of(1999, 11, 30)
        );
        listaPersonas2.agregar(persona5);
        listaPersonas.agregar(persona5);

        Persona persona6 = new Persona(persona);
        listaPersonas2.agregar(persona6);

        Persona personaEncontrada = (Persona) listaPersonas2.buscarPorId(4);
        if (personaEncontrada == null) {
            System.out.println("Persona no encontrada");
        } else {
            System.out.println("Persona Encontrada: " + personaEncontrada);
        }
       
        
        
        //--------------------------------------------------------------------------------------------
        ListaVehiculos listaVehiculos = new ListaVehiculos();
        Vehiculo vehiculo1 = new Vehiculo(
                listaVehiculos2.obtenerConsecutivo(),
                "123456",
                "Carro",
                2015
        );
        listaVehiculos2.agregar(vehiculo1);
        listaVehiculos.agregar(vehiculo1);

        Vehiculo vehiculo2 = new Vehiculo(
                listaVehiculos2.obtenerConsecutivo(),
                "123456",
                "Carro",
                2015
        );
        listaVehiculos2.agregar(vehiculo2);
        listaVehiculos.agregar(vehiculo2);
        

        Vehiculo vehiculo3 = new Vehiculo(
                listaVehiculos2.obtenerConsecutivo(),
                "654321",
                "Moto",
                2018
        );
        listaVehiculos2.agregar(vehiculo3);
        listaVehiculos.agregar(vehiculo3);

        Vehiculo vehiculo4 = new Vehiculo(
                listaVehiculos2.obtenerConsecutivo(),
                "987654",
                "Camioneta",
                2020
        );
        listaVehiculos2.agregar(vehiculo4);
        listaVehiculos.agregar(vehiculo4);

        Vehiculo vehiculo5 = new Vehiculo(
                listaVehiculos2.obtenerConsecutivo(),
                "456789",
                "Bus",
                2012
        );
        listaVehiculos2.agregar(vehiculo5);
        listaVehiculos.agregar(vehiculo5);
        System.out.println("__________________________________________");
        ArrayList<Persona> personasOrdenadas = listaPersonas.mayoresEdad();
    
        for(Persona p : personasOrdenadas) {
            System.out.println(p);
        }

        //System.out.println(listaVehiculos2.toString());
        //System.out.println(listaPersonas2);
    }
}

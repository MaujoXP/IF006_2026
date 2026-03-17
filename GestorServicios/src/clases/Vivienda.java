/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;

public class Vivienda {
    private int id;
    private String propietario;
    private String numCasa;
    private String provincia;
    private String direccion;
    private ArrayList<Servicio> servicios;
    private final double IVA = 0.13; 

    public Vivienda(int id, String propietario, String numCasa, String provincia, String direccion) {
        this.id = id;
        this.propietario = propietario;
        this.numCasa = numCasa;
        this.provincia = provincia;
        this.direccion = direccion;
        this.servicios = new ArrayList<>();
    }

    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
    }

    public void mostrarResumen() {
        System.out.println("RESUMEN DE FACTURACIÓN - Vivienda: " + numCasa);
        System.out.println("Propietario: " + propietario);
        System.out.println("-------------------------------------------------");
        
        double totalGeneral = 0;

        for (Servicio s : servicios) {
            double base = s.calcularMonto();
            double montoIva = base * IVA;
            double totalServicio = base + montoIva;
            
            System.out.println("Servicio: " + s.getNombre() + " (" + s.getCodigo() + ")");
            System.out.println(" > Costo Base: ₡" + base);
            System.out.println(" > IVA (13%): ₡" + montoIva);
            System.out.println(" > Costo Final: ₡" + totalServicio);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
            
            totalGeneral += totalServicio;
        }
        
        System.out.println("TOTAL GENERAL A PAGAR: ₡" + totalGeneral);
    }
}

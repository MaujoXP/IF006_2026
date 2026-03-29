/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persona;

import generica.ListaGenerica;
import java.time.LocalDate;
import java.util.ArrayList;
import utilidades.ManejoFechas;

/**
 * Contiene en memoria una lista de Persona
 *
 * @version 1.0.0
 * @author Mauricio
 */
public class ListaPersonas extends ListaGenerica<Persona> {
    protected ManejoFechas manejoFechas = new ManejoFechas();

    public int obtenerConsecutivoId() {
        return lista.size() + 1;
    }

    public ArrayList mayoresEdad() {
        ArrayList<Persona> temp = new ArrayList<>();

        for (Persona persona : lista) {
            if (manejoFechas.calcularEdad(persona.getFechaNacimiento()) >= 18) {
                temp.add(persona);
            }
        }

        return temp;
    }

    public ArrayList nacidasAntesDe(int annio) {
        ArrayList<Persona> temp = new ArrayList();

        for (Persona persona : lista) {
            if (manejoFechas.compararFechas(persona.getFechaNacimiento(), LocalDate.of(annio, 1, 1)).equals("La primera fecha es anterior")) {
                temp.add(persona);
            }
        }

        return temp;
    }

    @Override
    public String toString() {
        String salida = "";
        for (Persona persona : lista) {
            salida += persona + "\n\n";
        }
        return salida;
    }
}

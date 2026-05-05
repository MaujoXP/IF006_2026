package Contacto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class PruebaContacto {

    public static void main(String[] args) {
        ListaContactos lista1 = new ListaContactos(100);
        ListaContactos lista2 = new ListaContactos(100);
        ListaContactos lista3 = new ListaContactos(100);
        lista1.cargarListaChesky();
        lista2.cargarListaChesky2();
        lista3.cargarListaJengibre();
        //System.out.println(lJengibre.unirLista(lChesky, lJengibre.unirLista(lJengibre, lChesky2)).filtroXEstado(3).toReporteTelefonos());
    }
}

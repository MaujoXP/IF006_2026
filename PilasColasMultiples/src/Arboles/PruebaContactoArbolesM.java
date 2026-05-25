/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arboles;

import Contacto.ListaContactos;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Gaby
 */
public class PruebaContactoArbolesM {
    public static void main(String[] args) {
          ArbolesM bosque = new ArbolesM(60, 5); 
//        bosque.buscarUbicacion(500, 0);
//        bosque.buscarUbicacion(300, 0);
//        bosque.buscarUbicacion(700, 0);
//        bosque.buscarUbicacion(600, 0);
//        bosque.buscarUbicacion(1200, 0);
//        bosque.buscarUbicacion(1225, 0);
//        bosque.buscarUbicacion(1400, 0);
//        //System.out.println(bosque.toString()); 
//        
//        bosque.buscarUbicacion(599, 1);
//        bosque.buscarUbicacion(400, 1);
//        bosque.buscarUbicacion(720, 1);
//        bosque.buscarUbicacion(640, 1);
//        bosque.buscarUbicacion(1250, 1);
//        bosque.buscarUbicacion(1665, 1);
//        bosque.buscarUbicacion(1440, 1);
//        System.out.println(bosque.toString()); 
//        
//        bosque.buscarUbicacion(123, 2);
//        bosque.buscarUbicacion(456, 2);
//        bosque.buscarUbicacion(789, 2);
//        bosque.buscarUbicacion(678, 2);
//        bosque.buscarUbicacion(1789, 2);
//        bosque.buscarUbicacion(145, 2);
//        bosque.buscarUbicacion(150, 2);
//        System.out.println(bosque.toString());
//        
//        System.out.println(bosque.buscarElemento(100, 2));
        
        ListaContactos lista1 = new ListaContactos(100);
        ListaContactos lista2 = new ListaContactos(100);
        ListaContactos lista3 = new ListaContactos(100);
        ListaContactos lista4 = new ListaContactos(100);
        ListaContactos lista5 = new ListaContactos(100);
        lista1.cargarListaAveParaiso();
        lista2.cargarListaChesky();
        lista3.cargarListaChesky2();
        lista4.cargarListaJengibre();
        lista5.cargarListaJengibre_A();
        
        for(int i = 0; i < 10; i++) {
            bosque.buscarUbicacion(Integer.parseInt(lista1.getContacto(i).getId()), 0);
            bosque.buscarUbicacion(Integer.parseInt(lista5.getContacto(i).getId()), 4);
            bosque.buscarUbicacion(Integer.parseInt(lista4.getContacto(i).getId()), 3);
            bosque.buscarUbicacion(Integer.parseInt(lista3.getContacto(i).getId()), 2);
            bosque.buscarUbicacion(Integer.parseInt(lista2.getContacto(i).getId()), 1);
        }
        System.out.println(bosque);
    }
}

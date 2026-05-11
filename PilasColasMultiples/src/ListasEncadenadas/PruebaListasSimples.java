/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListasEncadenadas;

import Contacto.ListaContactos;

/**
 *
 * @author Gaby
 */
public class PruebaListasSimples{
  public static void main(String arg[]){
 //    ListaSimpleC lsc= new ListaSimpleC(7);
//       ListaSimpleF lsc= new ListaSimpleF(7);
////    System.out.println(lsc.toStringI());
////    
//    lsc.agregarElemento(104);
//    lsc.agregarElemento(45);
//    lsc.agregarElemento(47);
//    lsc.agregarElemento(48);
//    lsc.agregarElemento(83);
//    System.out.println(lsc.toString());
////    System.out.println("ELIMINADO: "+lsc.eliminarElemento());
//    System.out.println("Localizado en: "+lsc.localizarElemento(47));
    ListaSimpleCM listaCM = new ListaSimpleCM(40, 3);
    ListaContactos lista1 = new ListaContactos(100);
        ListaContactos lista2 = new ListaContactos(100);
        ListaContactos lista3 = new ListaContactos(100);
        lista1.cargarListaChesky();
        lista2.cargarListaChesky2();
        lista3.cargarListaJengibre();
        for(int i = 0; i < 8; i++) {
             listaCM.agregarElemento(Integer.parseInt(lista1.getContacto(i).getId()), 0);
        }
        System.out.println(listaCM.toString());
        for(int i = 0; i < 8; i++) {
             listaCM.agregarElemento(Integer.parseInt(lista2.getContacto(i).getId()), 1);
        }
        for(int i = 0; i < 8; i++) {
             listaCM.agregarElemento(Integer.parseInt(lista3.getContacto(i).getId()), 2);
        }
        System.out.println(listaCM.toString());
        
   
  }
}

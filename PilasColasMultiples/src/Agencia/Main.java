/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agencia;

import Arboles.ArbolesM;
import Cola.Cola;
import Cola.ColasM;
import ListasEncadenadas.ListaDobleC;
import ListasEncadenadas.ListaDobleCM;
import ListasEncadenadas.ListaDobleF;
import ListasEncadenadas.ListaSimpleC;
import ListasEncadenadas.ListaSimpleCM;
import ListasEncadenadas.ListaSimpleF;

/**
 *
 * @author Mauricio León B C5G444
 */
public class Main {

    public static void main(String[] args) {  
        ListaTramites lista1 = new ListaTramites();
        ListaTramites lista2 = new ListaTramites();
        ListaTramites lista3 = new ListaTramites();
        ListaTramites lista4 = new ListaTramites();
        ListaTramites lista5 = new ListaTramites();
        ListaTramites lista6 = new ListaTramites();
        lista1.cargarListaLimon();
        lista2.cargarListaCaldera();
        lista3.cargarListaSanJose();
        lista4.cargarListaGolfito();
        lista5.cargarListaMoin();
        lista6.cargarListaMatina();
        // Colas
        Cola cola1 = new Cola();
        cola1.agregarElemento(lista1.getTramite(0).getnTramite());
        cola1.agregarElemento(lista1.getTramite(1).getnTramite());
        cola1.agregarElemento(lista1.getTramite(2).getnTramite());
        
        cola1.eliminarElemento();
        
        cola1.agregarElemento(lista2.getTramite(0).getnTramite());
        cola1.agregarElemento(lista2.getTramite(1).getnTramite());
        cola1.agregarElemento(lista2.getTramite(2).getnTramite());
        cola1.agregarElemento(lista2.getTramite(3).getnTramite());
        
        cola1.eliminarElemento();
        cola1.eliminarElemento();
        
        System.out.println(cola1.toString());
        
        // ColasM
        ColasM colaM = new ColasM(6); 
        colaM.inicializarColas(50);
        for(int i = 0; i < lista1.getATramite(); i++){
            colaM.agregarColaM(0, lista1.getTramite(i).getnTramite());
        }
        for(int i = 0; i < lista2.getATramite(); i++){
            colaM.agregarColaM(1, lista2.getTramite(i).getnTramite());
        }
        for(int i = 0; i < lista3.getATramite(); i++){
            colaM.agregarColaM(2, lista3.getTramite(i).getnTramite());
        }
        for(int i = 0; i < lista4.getATramite(); i++){
            colaM.agregarColaM(3, lista4.getTramite(i).getnTramite());
        }
        
        System.out.println(colaM.toString());

        // LSF
        ListaSimpleF lsf = new ListaSimpleF(20);

        lsf.agregarElemento(lista1.getTramite(0).getnTramite());
        lsf.agregarElemento(lista1.getTramite(1).getnTramite());
        lsf.agregarElemento(lista1.getTramite(2).getnTramite());
        lsf.agregarElemento(lista1.getTramite(3).getnTramite());

        lsf.eliminarElemento();

        lsf.agregarElemento(lista2.getTramite(0).getnTramite());
        lsf.agregarElemento(lista2.getTramite(1).getnTramite());
        lsf.agregarElemento(lista2.getTramite(2).getnTramite());
        lsf.agregarElemento(lista2.getTramite(3).getnTramite());
        lsf.agregarElemento(lista2.getTramite(4).getnTramite());

        lsf.eliminarElemento();
        lsf.eliminarElemento();
        lsf.eliminarElemento();
        lsf.eliminarElemento();

        System.out.println(lsf.toString());

        // LSC
        ListaSimpleC lsc = new ListaSimpleC(50);

        lsc.agregarElemento(lista3.getTramite(0).getnTramite());
        lsc.agregarElemento(lista3.getTramite(1).getnTramite());
        lsc.agregarElemento(lista3.getTramite(2).getnTramite());

        lsc.eliminarElemento();

        lsc.agregarElemento(lista4.getTramite(0).getnTramite());
        lsc.agregarElemento(lista4.getTramite(1).getnTramite());
        lsc.agregarElemento(lista4.getTramite(2).getnTramite());

        for (int i = 0; i < 4; i++) {
            lsc.eliminarElemento();
        }

        System.out.println(lsc.toString());
        
        // LDF
        ListaDobleF ldf = new ListaDobleF(50);
        
        for (int i = 0; i < 4 ; i++) {
            ldf.agregarElemento(lista5.getTramite(i).getnTramite());
        }
        
        ldf.eliminarElemento();
        
        for (int i = 0; i < 5; i++) {
            ldf.agregarElemento(lista1.getTramite(i).getnTramite());
        }
        
        for (int i = 0; i < 4; i++) {
            ldf.eliminarElemento();
        }
        
        System.out.println(ldf.toString());
        // LDC
        ListaDobleC ldc = new ListaDobleC(60);
        
        for (int i = 0; i < 5; i++) {
            ldc.agregarElemento(lista3.getTramite(i).getnTramite());
        }
        
        ldc.eliminarElemento();
        
        for (int i = 0; i < 6; i++) {
            ldc.agregarElemento(lista4.getTramite(i).getnTramite());
        }
        
        for (int i = 0; i < 4; i++) {
            ldc.eliminarElemento();
        }
        
        System.out.println(ldc.toString());

        // LSCM
        ListaSimpleCM lscm = new ListaSimpleCM(200, 5);
        
        for (int i = 0; i < lista1.getATramite(); i++) {
            lscm.agregarElemento(lista1.getTramite(i).getnTramite(), 0);
        }
        
        for (int i = 0; i < lista2.getATramite(); i++) {
            lscm.agregarElemento(lista2.getTramite(i).getnTramite(), 1);
        }
        
        for (int i = 0; i < lista3.getATramite(); i++) {
            lscm.agregarElemento(lista3.getTramite(i).getnTramite(), 2);
        }
        
        for (int i = 0; i < lista4.getATramite(); i++) {
            lscm.agregarElemento(lista4.getTramite(i).getnTramite(), 3);
        }
        
        System.out.println(lscm.toString());
        
        // LDCM
        ListaDobleCM ldcm = new ListaDobleCM(300, 7); 
        
        for (int i = 0; i < lista1.getATramite(); i++) {
            ldcm.agregarElemento(lista1.getTramite(i).getnTramite(), 0);
        }
        
        for (int i = 0; i < lista2.getATramite(); i++) {
            ldcm.agregarElemento(lista2.getTramite(i).getnTramite(), 1);
        }
        
        for (int i = 0; i < lista3.getATramite(); i++) {
            ldcm.agregarElemento(lista3.getTramite(i).getnTramite(), 2);
        }
        
        for (int i = 0; i < lista4.getATramite(); i++) {
            ldcm.agregarElemento(lista4.getTramite(i).getnTramite(), 3);
        }
        
        System.out.println(ldcm.toString());

        // ArbolIRD
        ArbolesM abird = new ArbolesM(25, 1);
        
        for (int i = 0; i < 5; i++) {
            abird.buscarUbicacion(lista3.getTramite(i).getnTramite(), 0);
        }
        
        for (int i = 0; i < 6; i++) {
            abird.buscarUbicacion(lista4.getTramite(i).getnTramite(), 0);
        }
        
        System.out.println(abird.toString());
        
        // ArbolesMIRD
        
        ArbolesM abirdm = new ArbolesM(50, 7);
        
        for (int i = 0; i < lista6.getATramite(); i++) {
            abirdm.buscarUbicacion(lista6.getTramite(i).getnTramite(), 0);
        }

        for (int i = 0; i < lista2.getATramite(); i++) {
            abirdm.buscarUbicacion(lista2.getTramite(i).getnTramite(), 1);
        }

        for (int i = 0; i < lista5.getATramite(); i++) {
            abirdm.buscarUbicacion(lista5.getTramite(i).getnTramite(), 2);
        }

        for (int i = 0; i < lista4.getATramite(); i++) {
            abirdm.buscarUbicacion(lista4.getTramite(i).getnTramite(), 3);
        }

        System.out.println(abirdm.toString());
    }
}

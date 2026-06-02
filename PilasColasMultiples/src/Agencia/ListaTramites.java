/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agencia;


public class ListaTramites {

    private Tramite[] listaTramites;
    private int aTramite;
    private int tamMaximo;

    public ListaTramites() {
        tamMaximo = 20;
        listaTramites = new Tramite[tamMaximo];
        aTramite = 0;
    }

    public Tramite getTramite(int tramite) {
        return listaTramites[tramite];
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public ListaTramites(int tamannio) {
        tamMaximo = tamannio;
        listaTramites = new Tramite[tamMaximo];
        aTramite = 0;
    }

    public void agregarTramite(Tramite nuevo) {
        listaTramites[aTramite++] = nuevo;
    }

    public String    toString() {
        String sal = "LISTA DE CONTACTOS GENERAL";
        for (int i = 0; i < aTramite; i++) {
            sal += listaTramites[i].toString();
        }
        return sal;
    }

//    public String toReporte() {
//        String sal = "LISTA DE CONTACTOS GENERAL";
//        sal += "\n";
//        for (int i = 0; i < aTramite; i++) {
//            sal += listaTramites[i].toDetalle();
//        }
//        return sal;
//    }

//    public String toReporteTelefonos() {
//        String sal = "LISTA DE CONTACTOS GENERAL";
//        sal += "\n";
//        for (int i = 0; i < aTramite; i++) {
//            sal += listaTramites[i].toDetalleListaTelefonos();
//        }
//        return sal;
//    }

//    public ListaTramites filtroXEstado(int estadoNuevo) {
//        ListaTramites temporal = new ListaTramites(tamMaximo);
//        for (int i = 0; i < aTramite; i++) {
//            if (listaTramites[i].getEstado() == estadoNuevo) {
//                temporal.agregarTramite(listaTramites[i]);
//            }
//        }
//        return temporal;
//    }

    public ListaTramites unirLista(ListaTramites listaA,
            ListaTramites listaB) {

        ListaTramites temporal = new ListaTramites(
                listaA.getATramite()
                + listaB.getATramite());
        for (int i = 0; i < listaA.getATramite(); i++) {
            temporal.agregarTramite(listaA.listaTramites[i]);
        }
        for (int i = 0; i < listaB.getATramite(); i++) {
            temporal.agregarTramite(listaB.listaTramites[i]);
        }
        return temporal;
    }

    public int getATramite() {
        return aTramite;
    }

    public void cargarListaLimon() {
        Tramite t1 = new Tramite(25, "19/6/2010", 81014, 20010030, 10000, 2000, 1);
        Tramite t2 = new Tramite(25, "19/6/2010", 81018, 20010033, 20000, 8000, 2);
        Tramite t3 = new Tramite(25, "21/6/2010", 81023, 20010030, 4000, 5000, 3);
        Tramite t4 = new Tramite(25, "21/6/2010", 81025, 20010030, 10000, 2000, 1);
        Tramite t5 = new Tramite(25, "21/6/2010", 81028, 20010030, 12000, 1000, 2);
        Tramite t6 = new Tramite(25, "22/6/2010", 81030, 20010033, 3000, 300, 3);
        Tramite t7 = new Tramite(25, "23/6/2010", 81050, 20010040, 7000, 750, 1);
        Tramite t8 = new Tramite(25, "19/6/2013", 8001014, 20010030, 2000000, 200000, 1);
        Tramite t9 = new Tramite(25, "19/6/2013", 8001018, 20010033, 4800000, 48000, 2);
        Tramite t10 = new Tramite(25, "21/6/2013", 8001023, 20010030, 4000000, 75000, 3);
        Tramite t11 = new Tramite(25, "19/6/2013", 8001060, 20010030, 10000000, 200000, 1);
        Tramite t12 = new Tramite(25, "19/6/2013", 8001057, 20010033, 2000000, 48000, 2);
        Tramite t13 = new Tramite(25, "21/6/2013", 8001016, 20010030, 4000000, 75000, 3);
        Tramite t14 = new Tramite(25, "19/6/2013", 8001055, 20010030, 10000000, 200000, 1);
        Tramite t15 = new Tramite(25, "19/6/2013", 8001004, 20010033, 2000000, 48000, 2);
        Tramite t16 = new Tramite(25, "21/6/2013", 8001003, 20010030, 4000000, 75000, 3);

        agregarTramite(t1);
        agregarTramite(t2);
        agregarTramite(t3);
        agregarTramite(t4);
        agregarTramite(t5);
        agregarTramite(t6);
        agregarTramite(t7);
        agregarTramite(t8);
        agregarTramite(t9);
        agregarTramite(t10);
        agregarTramite(t11);
        agregarTramite(t12);
        agregarTramite(t13);
        agregarTramite(t14);
        agregarTramite(t15);
        agregarTramite(t16);
    }

    public void cargarListaCaldera() {
        Tramite t1 = new Tramite(30, "19/6/2010", 91014, 20010030, 14000, 1400, 2);
        Tramite t2 = new Tramite(30, "19/6/2010", 91018, 20010033, 2800, 280, 3);
        Tramite t3 = new Tramite(30, "21/6/2010", 91023, 20010030, 17000, 1750, 1);
        Tramite t4 = new Tramite(30, "21/6/2010", 91025, 20010040, 1000, 100, 2);
        Tramite t5 = new Tramite(30, "21/6/2013", 8001028, 20010030, 6000000, 120000, 2);
        Tramite t6 = new Tramite(30, "21/6/2013", 8001025, 20010040, 1000000, 102000, 1);
        Tramite t7 = new Tramite(30, "21/6/2013", 8001029, 20010040, 12000000, 120000, 2);
        Tramite t8 = new Tramite(30, "22/6/2013", 8001031, 20010033, 3000000, 350000, 3);
        Tramite t9 = new Tramite(30, "23/6/2013", 8001050, 20010040, 7000000, 275000, 1);

        agregarTramite(t1);
        agregarTramite(t2);
        agregarTramite(t3);
        agregarTramite(t4);
        agregarTramite(t5);
        agregarTramite(t6);
        agregarTramite(t7);
        agregarTramite(t8);
        agregarTramite(t9);
    }

    public void cargarListaSanJose() {
        Tramite t1 = new Tramite(28, "19/6/2010", 71012, 20010045, 10000, 200, 1);
        Tramite t2 = new Tramite(28, "19/6/2010", 71017, 20010033, 2000, 480, 2);
        Tramite t3 = new Tramite(28, "21/6/2010", 71025, 20010030, 4000, 750, 3);
        Tramite t4 = new Tramite(28, "21/6/2010", 71020, 20010030, 1000, 102, 1);
        Tramite t5 = new Tramite(28, "21/6/2010", 71030, 20010045, 12000, 120, 2);
        Tramite t6 = new Tramite(28, "22/6/2010", 71035, 20010033, 3000, 350, 3);
        Tramite t7 = new Tramite(28, "19/6/2010", 8001014, 20010030, 10000000, 200000, 1);
        Tramite t8 = new Tramite(28, "19/6/2010", 8001018, 20010033, 2000000, 48000, 2);
        Tramite t9 = new Tramite(28, "21/6/2010", 8001023, 20010030, 4000000, 75000, 3);
        Tramite t10 = new Tramite(28, "21/6/2010", 8001025, 20010030, 1000000, 102000, 1);
        Tramite t11 = new Tramite(28, "21/6/2010", 8001028, 20010030, 12000000, 120000, 2);
        Tramite t12 = new Tramite(28, "22/6/2010", 8001030, 20010033, 3000000, 350000, 3);
        Tramite t13 = new Tramite(28, "23/6/2010", 8001050, 20010040, 7000000, 275000, 1);
        Tramite t14 = new Tramite(28, "21/6/2013", 8001005, 20010040, 1000000, 102000, 1);
        Tramite t15 = new Tramite(28, "21/6/2013", 8001008, 20010040, 12000000, 120000, 2);
        Tramite t16 = new Tramite(28, "22/6/2013", 8001000, 20010033, 3000000, 350000, 3);

        agregarTramite(t1);
        agregarTramite(t2);
        agregarTramite(t3);
        agregarTramite(t4);
        agregarTramite(t5);
        agregarTramite(t6);
        agregarTramite(t7);
        agregarTramite(t8);
        agregarTramite(t9);
        agregarTramite(t10);
        agregarTramite(t11);
        agregarTramite(t12);
        agregarTramite(t13);
        agregarTramite(t14);
        agregarTramite(t15);
        agregarTramite(t16);
    }

    public void cargarListaGolfito() {
        Tramite t1 = new Tramite(35, "19/6/2010", 21012, 20010045, 11000, 255, 1);
        Tramite t2 = new Tramite(35, "19/6/2010", 21019, 20010033, 22000, 408, 2);
        Tramite t3 = new Tramite(35, "22/6/2013", 8001030, 20010033, 3000000, 350000, 3);
        Tramite t4 = new Tramite(35, "23/6/2013", 8001038, 20010040, 5000000, 275000, 1);
        Tramite t5 = new Tramite(35, "22/6/2013", 8001039, 20010033, 3000000, 325000, 4);
        Tramite t6 = new Tramite(35, "22/6/2013", 8001039, 20010033, 3000000, 325000, 4);

        agregarTramite(t1);
        agregarTramite(t2);
        agregarTramite(t3);
        agregarTramite(t4);
        agregarTramite(t5);
        agregarTramite(t6);
    }

    public void cargarListaMatina() {
        Tramite t1 = new Tramite(32, "19/6/2010", 51102, 20010030, 10890, 190, 1);
        Tramite t2 = new Tramite(32, "19/6/2010", 51107, 20010033, 2000, 415, 2);
        Tramite t3 = new Tramite(32, "21/6/2010", 51205, 20010030, 4000, 705, 3);
        Tramite t4 = new Tramite(32, "21/6/2010", 71020, 20010030, 1000, 102, 1);
        Tramite t5 = new Tramite(32, "21/6/2010", 71030, 20010045, 12000, 120, 2);
        Tramite t6 = new Tramite(32, "22/6/2010", 71035, 20010033, 3000, 350, 3);
        Tramite t7 = new Tramite(32, "19/6/2010", 8001014, 20010030, 10000000, 200000, 1);
        Tramite t8 = new Tramite(32, "19/6/2010", 8001018, 20010033, 2000000, 48000, 2);

        agregarTramite(t1);
        agregarTramite(t2);
        agregarTramite(t3);
        agregarTramite(t4);
        agregarTramite(t5);
        agregarTramite(t6);
        agregarTramite(t7);
        agregarTramite(t8);
    }
    
    public void cargarListaMoin() {
        Tramite t1 = new Tramite(40, "19/6/2010", 51102, 20010030, 10890, 190, 1);
        Tramite t2 = new Tramite(40, "19/6/2010", 51107, 20010033, 2000, 415, 2);
        Tramite t3 = new Tramite(40, "21/6/2010", 51205, 20010030, 4000, 705, 3);
        Tramite t4 = new Tramite(40, "21/6/2010", 71020, 20010030, 1000, 102, 1);
        Tramite t5 = new Tramite(40, "21/6/2010", 71030, 20010045, 12000, 120, 2);
        Tramite t6 = new Tramite(40, "22/6/2010", 71035, 20010033, 3000, 350, 3);
        Tramite t7 = new Tramite(40, "19/6/2010", 8001014, 20010030, 10000000, 200000, 1);
        Tramite t8 = new Tramite(40, "19/6/2010", 8001018, 20010033, 2000000, 48000, 2);

        agregarTramite(t1);
        agregarTramite(t2);
        agregarTramite(t3);
        agregarTramite(t4);
        agregarTramite(t5);
        agregarTramite(t6);
        agregarTramite(t7);
        agregarTramite(t8);
    }
}

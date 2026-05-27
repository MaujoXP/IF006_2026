package Buses;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Pila.PilasM;

/**
 *
 * @author Hp
 */
public class PruebaBuses {

    public static void main(String[] args) {

        Asiento primero = new Asiento();
        System.out.println(primero.toString());
        primero.setTipo(2);
        primero.setNumero(2);
        primero.setEstado(4);
        System.out.println("\n" + primero.toString());

        Bus bus1 = new Bus("A101", 38, true, true, false, true, "2021", "Volvo");
        Bus bus2 = new Bus("A102", 42, true, false, true, false, "2020", "MAN");
        Bus bus3 = new Bus("A103", 50, true, true, true, true, "2022", "Chevrolet");
        Bus bus4 = new Bus("A104", 36, false, true, false, false, "2019", "Hyundai");
        Bus bus5 = new Bus("A105", 44, true, true, false, true, "2023", "Mercedes-Benz");

        Bus bus6 = new Bus("B106", 48, true, false, true, true, "2021", "Chevrolet");
        Bus bus7 = new Bus("B107", 52, true, true, true, true, "2024", "MAN");
        Bus bus8 = new Bus("B108", 35, false, false, false, false, "2018", "Nissan");
        Bus bus9 = new Bus("B109", 40, true, true, false, true, "2022", "Solaris");
        Bus bus10 = new Bus("B110", 46, true, false, true, true, "2020", "Mercedes-Benz");

        Bus bus11 = new Bus("C111", 54, true, true, true, false, "2023", "MAN");
        Bus bus12 = new Bus("C112", 37, false, true, false, true, "2019", "Hyundai");
        Bus bus13 = new Bus("C113", 41, true, false, false, true, "2021", "Toyota");
        Bus bus14 = new Bus("C114", 49, true, true, true, true, "2024", "Scania");
        Bus bus15 = new Bus("C115", 53, true, false, true, true, "2022", "Chevrolet");

        Flota flotaGuapiles = new Flota("Guapiles", 6);
        Flota flotaSiquirres = new Flota("Siquirres", 6);
        Flota flotaSanJose = new Flota("San Jose", 6);

        flotaGuapiles.agregarBus(bus1);
        flotaGuapiles.agregarBus(bus2);
        flotaGuapiles.agregarBus(bus3);
        flotaGuapiles.agregarBus(bus4);
        flotaGuapiles.agregarBus(bus5);

        flotaSiquirres.agregarBus(bus6);
        flotaSiquirres.agregarBus(bus7);
        flotaSiquirres.agregarBus(bus8);
        flotaSiquirres.agregarBus(bus9);
        flotaSiquirres.agregarBus(bus10);

        flotaSanJose.agregarBus(bus11);
        flotaSanJose.agregarBus(bus12);
        flotaSanJose.agregarBus(bus13);
        flotaSanJose.agregarBus(bus14);
        flotaSanJose.agregarBus(bus15);

        System.out.println(flotaGuapiles.toString());
        System.out.println(flotaSiquirres);
        System.out.println(flotaSanJose);

        Chofer porras = new Chofer("Juan", "Gonzalez", "Valencia", "B95412354", "24/12/89", "Pueblo", 2, "78961456", "Juancho");
        System.out.println(porras.toString());

        ListaChoferes lista = new ListaChoferes();

        lista.agregarLista(new Chofer("Geovanny", "Cruz", "Rodriguez", "85235689", "04/12/84", "Cocos", 3, "62458763",  "Geo"));
        lista.agregarLista(new Chofer("Luis", "Torres", "Zuñiga", "23568741", "05/05/70", "Colina", 2, "84569875",  "Luisito"));
        lista.agregarLista(new Chofer("Sebastian", "Solis", "Herreras", "12568974", "05/10/89", "Villa hermosa", 4, "65847521",  "Sebitas"));
        lista.agregarLista(new Chofer("Wendy", "Cruz", "Rodriguez", "23568912", "01/03/90", "Marchena", 3, "88452169",  "Wensita"));
        lista.agregarLista(new Chofer("Yeni", "Rodriguez", "Contreras", "568745691", "05/03/84", "Villa del mar", 2, "84563827", "Cholita"));
        lista.agregarLista(new Chofer("Mariana", "Contreras", "Gomez", "23512047", "06/07/75", "Envaco", 4, "78452169", "Doña Mari"));
        lista.agregarLista(new Chofer("Reyli", "Fonseca", "Aguilar", "25463987", "06/08/86", "Pueblo Nuevo", 5, "65482136", "Rey"));
        lista.agregarLista(new Chofer("Keylin", "Sanchez", "Rodriguez", "23568714", "06/08/95", "Rio Frio", 2, "86987541", "Key"));
        lista.agregarLista(new Chofer("Shendaly", "Gutierrez", "Rogers", "31456910", "31/05/92", "Santa Rosa", 4, "73695021", "Shenshen"));
        lista.agregarLista(new Chofer("Joshua", "Madrigal", "Velazquez", "89753214", "10/11/90", "Beverly", 3, "63254100", "Josh"));
        lista.agregarLista(new Chofer("Yiriani", "Barahona", "Loria", "32897400", "01/05/96", "Limoncito", 2, "87596321", "Yir"));
        lista.agregarLista(new Chofer("Marcos", "Jimenez", "Solano", "12345678", "15/03/85", "Limon", 5, "88887777", "Marco"));
        lista.agregarLista(new Chofer("Andrea", "Vargas", "Mora", "98765432", "22/07/95", "San José", 4, "88776655", "Andy"));
        lista.agregarLista(new Chofer("Luis", "Fernandez", "Castro", "45678912", "09/11/82", "Cartago", 6, "89994422", "Lucho"));
        lista.agregarLista(new Chofer("Karla", "Soto", "Ramirez", "85296374", "30/01/88", "Heredia", 3, "86543219", "Kary"));
        lista.agregarLista(new Chofer("Jose", "Martinez", "Gomez", "74125896", "05/05/79", "Alajuela", 7, "87451236", "Chepe"));
        lista.agregarLista(new Chofer("Patricia", "Rojas", "Campos", "36985214", "18/09/92", "Puntarenas", 2, "89632145", "Patri"));
        lista.agregarLista(new Chofer("Daniel", "Cordero", "Araya", "15975348", "12/12/87", "Guanacaste", 4, "81234567", "Dani"));
        lista.agregarLista(new Chofer("Sofia", "Hernandez", "Quesada", "24681357", "21/06/86", "San Carlos", 5, "89765432", "Sofi"));
        lista.agregarLista(new Chofer("Ricardo", "Alfaro", "Perez", "13579246", "10/02/80", "Turrialba", 6, "87654321", "Ricky"));
        
        System.out.println(lista);

        System.out.println(lista.filtroLista1(8).toString2());
        System.out.println(lista.filtroLista1(3, 5).toString2());

        ListaChoferes listachof1 = new ListaChoferes();
        listachof1 = lista.filtroLista1(0, 7);

        ListaChoferes listachof2 = new ListaChoferes();
        listachof2 = lista.filtroLista1(8, 12);

        ListaChoferes listachof3 = new ListaChoferes();
        listachof3 = lista.filtroLista1(12, 19);

        System.out.println("Lista 1");
        System.out.println(listachof1.toString2());
        System.out.println("Lista 2");
        System.out.println(listachof2.toString2());
        System.out.println("Lista 3");
        System.out.println(listachof3.toString2());

        PilasM pila1 = new PilasM(3);

        pila1.crearPila(0, 20);
        for (int i = 0; i < listachof1.getApuntLista(); i++) {
            pila1.agregarPilaM(0, listachof1.getLicencia(i));
        }
        System.out.println(pila1.toString(0));

        pila1.crearPila(1, 20);
        for (int i = 0; i < listachof2.getApuntLista(); i++) {
            pila1.agregarPilaM(1, listachof2.getLicencia(i));
        }
        System.out.println(pila1.toString(1));

        pila1.crearPila(2, 20);
        for (int i = 0; i < listachof3.getApuntLista(); i++) {
            pila1.agregarPilaM(2, listachof3.getLicencia(i));
        }
        System.out.println(pila1.toString(2));
    }
}

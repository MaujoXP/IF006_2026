package Contacto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class ListaContactos {

    private Contacto[] listaContactos;
    private int aContacto;
    private int tamMaximo;

    public ListaContactos() {
        tamMaximo = 20;
        listaContactos = new Contacto[tamMaximo];
        aContacto = 0;
    }
    
    public Contacto getContacto(int contacto) {
        return listaContactos[contacto];
    }
    
    public int getTamMaximo() {
        return tamMaximo;
    }

    public ListaContactos(int tamannio) {
        tamMaximo = tamannio;
        listaContactos = new Contacto[tamMaximo];
        aContacto = 0;
    }

    public void agregarContacto(Contacto nuevo) {
        listaContactos[aContacto++] = nuevo;
    }

    public String toString() {
        String sal = "LISTA DE CONTACTOS GENERAL";
        for (int i = 0; i < aContacto; i++) {
            sal += listaContactos[i].toString();
        }
        return sal;
    }

    public String toReporte() {
        String sal = "LISTA DE CONTACTOS GENERAL";
        sal += "\n";
        for (int i = 0; i < aContacto; i++) {
            sal += listaContactos[i].toDetalle();
        }
        return sal;
    }
    
    public String toReporteTelefonos() {
        String sal = "LISTA DE CONTACTOS GENERAL";
        sal += "\n";
        for (int i = 0; i < aContacto; i++) {
            sal += listaContactos[i].toDetalleListaTelefonos();
        }
        return sal;
    }
    
    public ListaContactos filtroXEstado(int estadoNuevo ){
       ListaContactos temporal = new ListaContactos(tamMaximo);
        for(int i = 0 ;i<aContacto ;i++){
          if(listaContactos[i].getEstado() == estadoNuevo){
              temporal.agregarContacto(listaContactos[i]);}
       }
         return temporal;
    }
    
     public ListaContactos unirLista(ListaContactos listaA,
                                 ListaContactos listaB){

   ListaContactos temporal = new ListaContactos(
                                    listaA.getAContacto()+
                                    listaB.getAContacto());
          for(int i = 0 ;i<listaA.getAContacto() ;i++){
          temporal.agregarContacto(listaA.listaContactos[i]);
          }
       for(int i = 0 ;i<listaB.getAContacto() ;i++){
          temporal.agregarContacto(listaB.listaContactos[i]);
          }
       return temporal;
      }
     
     public int getAContacto() {
         return aContacto;
     }
     
     public void cargarListaJengibre() {
        Contacto c1200 = new Contacto("200", "Keylin Bermúdez", "Penshurt", "703450202", "keylin.bermudez@gmail.com", "07/09/2006", 0, 1);
        Contacto c1201 = new Contacto("201", "Mateo  Rojas", "Puntarenas", "703710203", "mateo.rojas@gmail.com", "05/10/2000", 2, 2);
        Contacto c1202 = new Contacto("202", "Edwin  Gomez", "San Carlos", "703150204", "edwin.gomez@gmail.com", "08/08/1980", 3, 3);
        Contacto c1203 = new Contacto("203", "Martin  Castro", "San Jose", "703780205", "martin.castro@gmail.com", "25/10/1970", 1, 0);
        Contacto c1204 = new Contacto("204", "Vilma  Ramirez", "Puerto Viejo", "712250206", "vilma.ramirez@gmail.com", "20/12/1975", 1, 1);
        Contacto c1205 = new Contacto("205", "Pablo  Espinoza", "Cahuita", "703251507", "pablo.espinoza@gmail.com", "15/03/2001", 0, 2);
        Contacto C1206 = new Contacto("1206", "Carlos", "Chepe", "1206000", "Carlos@gmail.com", "6/2/2026", 0, 0);
        Contacto C1207 = new Contacto("1207", "Marco", "Chepe", "1207000", "Marco@gmail.com", "7/2/2026", 0, 1);
        Contacto C1208 = new Contacto("1208", "Pepe", "Chepe", "1208000", "Pepe@gmail.com", "8/2/2026", 0, 2);
        Contacto C1209 = new Contacto("1209", "Juan", "Chepe", "1209000", "Juan@gmail.com", "9/2/2026", 0, 3);
        Contacto C1210 = new Contacto("1210", "Mario", "Chepe", "1210000", "Mario@gmail.com", "10/2/2026", 0, 3);
        Contacto c1211 = new Contacto("1211", "Ricardo Martinez", "Cahuita ", "12918851", "richimartinez@gamail.com", "30-12-1989", 1, 0);
        Contacto c1212 = new Contacto("1212", "Maria Perez", "Colina ", "72916851", "mariperez@gail.com", "12-7-1998", 0, 3);
        Contacto c1213 = new Contacto("1213", "Barbara Fuentes", "Cocles ", "72918551", "barbiecocles03@gamail.com", "9-10-2002", 1, 0);
        Contacto c1214 = new Contacto("1214", "Carlos Mora", "Playa Chiquita ", "70915851", "carlitosmora@gamail.com", "9-3-1988", 0, 2);
        Contacto c1215 = new Contacto("1215", "Marta Brown", "Hone Creek ", "72968751", "martabrown21@gamail.com", "1978", 1, 0);
        Contacto c1216 = new Contacto("1216", "Ana ", "Cartago ", "75235662", "anacalvo9105@gamail.com", "29/02/2000", 1, 0);
        Contacto c1217 = new Contacto("1217", "Fiama", "Herradura", "75615145", "jfiamaherrera5@gamail.com", "30/05/1999", 1, 0);
        Contacto c1218 = new Contacto("1218", "Allan", "Quepos ", "75642565", "allancarrillo95455@gamail.con", "31/06/2001", 1, 0);
        Contacto c1219 = new Contacto("1219", "Gilbert", "Grecia", "725484151", "gilbethfonseca415@gamail.con", "02/02/1989", 1, 0);
        Contacto c1220 = new Contacto("1220", "Cristian", "Limoncito ", "725451545", "cristiancarrillo941@gamail.con", "01/03/2022", 1, 0);
        Contacto c1221 = new Contacto("1221", "Pancho      ", "Cocos       ", "6154956", "Panch.Martinez@ucr.ac.cr", "05/10/2003", 1, 0);
        Contacto c1222 = new Contacto("1222", "Sara      ", "Pacuarito   ", "8521584", "Sara.davis@ucr.ac.cr", "03/01/2000", 1, 1);
        Contacto c1223 = new Contacto("1223", "Hernando     ", "Limon Colina", "62158488", "hernandoGonzalezHerrera", "21/11/2002", 1, 0);
        Contacto c1224 = new Contacto("1224", "Oscar      ", "Desamparados", "84585858", "OSCAR.FARGAS@ucr.ac.cr", "01-09-2003", 1, 1);
        Contacto c1225 = new Contacto("1225", "Juan", "batan ", "7255888", "juancarrillo9415@gamail.con", "21-08-2003", 1, 0);
        Contacto c1226 = new Contacto("1226", "Carlos", "batan ", "722658855", "carloscarrillo9415@gamail.con", "08-12-1999", 1, 0);
        Contacto c1228 = new Contacto("1228", "Wendoly      ", "Matina       ", "63074400", "wendoly.cruz@ucr.ac.cr", "23/08/2006", 3, 0);
        Contacto c1229 = new Contacto("1229", "Shendaly      ", "Goly  ", "86307725", "Sgendaly31@gmail.com", "05/31/2006", 2, 1);
        Contacto c1230 = new Contacto("1230", "Sophia      ", "Matina", "60214503", "sophiacruz03@gmail.com", "05/03/2014", 4, 0);
        Contacto c1231 = new Contacto("1231", "Wendy      ", "Managuita", "86458975", "wendycruz@gmail.com", "03-01-2002", 1, 1);
        Contacto c1232 = new Contacto("1232", "Geovanny", "Matina", "64158975", "geocruz@gmail.com", "0", 3, 0);
        agregarContacto(c1200);
        agregarContacto(c1201);
        agregarContacto(c1202);
        agregarContacto(c1203);
        agregarContacto(c1204);
        agregarContacto(c1205);
        agregarContacto(C1206);
        agregarContacto(C1207);
        agregarContacto(C1208);
        agregarContacto(C1209);
        agregarContacto(C1210);
        agregarContacto(c1211);
        agregarContacto(c1212);
        agregarContacto(c1213);
        agregarContacto(c1214);
        agregarContacto(c1215);
        agregarContacto(c1216);
        agregarContacto(c1217);
        agregarContacto(c1218);
        agregarContacto(c1219);
        agregarContacto(c1220);
        agregarContacto(c1221);
        agregarContacto(c1222);
        agregarContacto(c1223);
        agregarContacto(c1224);
        agregarContacto(c1225);
        agregarContacto(c1226);
        agregarContacto(c1228);
        agregarContacto(c1229);
        agregarContacto(c1230);
        agregarContacto(c1231);
        agregarContacto(c1232);
    }

    public void cargarListaChesky2() {
        Contacto c101 = new Contacto("101", "Adrian Grajal", "Barrio la Colina", "84076509", "Adriangm2304@gmail.com", "23/2/2004", 1, 0);
        Contacto c102 = new Contacto("102", "Tristan", "Barrio la colina", "8938-1855", "tristanmendezmayorga52@gmail.com", "09-09-2004", 0, 0);
        Contacto c103 = new Contacto("103", "Albin Liang", "Limon", "83919528", "Albin.Liang.ucr.ac.cr", "2003-09-10", 0, 0);
        Contacto c104 = new Contacto("104", "Walter Fuertes", "Zent Viejo", "87068921", "fuertesBusiness@outlook.com", "20-04-1997", 2, 0);
        Contacto c105 = new Contacto("105", "Andres", "Juan Pablo II", "87683425", "arayaagueroa@gamil.com", "30/07/2004", 1, 0);
        Contacto c106 = new Contacto("106", "Gicxel", "Noruega", "83857511", "gicxel.pastrana@ucr.ac.cr", "16/05/2004", 1, 0);
        Contacto c107 = new Contacto("107", "Keyron Ortiz", "Barrio la Colina", "86941252", "keyronortiz@gmail.com", "16/2/2004", 1, 0);
        Contacto c108 = new Contacto("108", "Breylin", "Rio Blanco", "2797-18-85", "BreilynContrerasJ@gmail.com", "18/04/2001", 2, 0);
        Contacto c109 = new Contacto("109", "John", "PuebloNuevo", "86549088", "jbermudezquiros@gamail.com", "19/02/2002", 1, 0);
        Contacto c110 = new Contacto("110", "Jorge", "Quepos", "86915732", "jorge.monterovargas@ucr.ac.cr", "13/05/1994", 4, 0);
        Contacto c111 = new Contacto("111", "Roger", "Pacuare", "86501854", "roger.calderon@ucr.ac.cr", "18/04/2003", 1, 0);
        Contacto c112 = new Contacto("112", "Yeslin", "Río Quito", "63146689", "yeslin.chinchilla@ucr.ac.cr", "22-09-2002", 4, 0);
        Contacto c113 = new Contacto("113", "LUIS", "BARRIO LAS PALMERAS", "86650012", "LUIS.RIOSSABORIO@UCR.AC.CR", "06/08/1998", 4, 0);
        Contacto c114 = new Contacto("114", "Ashton", "Siglo XX1", "85820121", "ashton.brenes@ucr.ac.cr", "25/09/2004", 4, 0);
        Contacto c115 = new Contacto("115", "Eduardo", "Envaco", "72798573", "eduardo@gmail", "25/03/1989", 0, 0);
        Contacto c116 = new Contacto("116", "Deymer", "Colina", "70119361", "deymercordero@ucr.ac.cr", "29/03/2001", 1, 0);
        Contacto c117 = new Contacto("117", "Marco", "Juan Pablo", "87257074", "marco.rojastrejos@ucr.ac.cr", "06/01/1997", 4, 0);
        Contacto c118 = new Contacto("lol", "Josué", "Bataan", "71862723", "josue.moralessuarez@ucr.ac.cr", "14/08/1998", 0, 0);
        Contacto c119 = new Contacto("119", "Rodrigo", "Liverpool", "84335402", "rodrigoru@icloud.com", "13/04/2003", 1, 0);
        Contacto c120 = new Contacto("120", "Keinth", "Bataan", "86670745", "keinth@icloud.com", "17/02/2003", 1, 0);
        Contacto c121 = new Contacto("121", "Jordy Mendoza", "Limoncito", "89413162", "jordy.mendoza@ucr.ac.cr", "26/06/2002", 0, 0);
        Contacto c122 = new Contacto("122", "Johan", "Limon", "84483466", "johanloriam@gmail.com", "09/08/2003", 3, 0);
        Contacto c123 = new Contacto("123", "Kenery", "Siquirres", "85909753", "keneryrodriguezespinoza@gmail.com", "31/01/2000", 3, 0);
        Contacto c124 = new Contacto("124", "Valery", "Limon", "86688505", "valeFran2526@gmail.com", "25/09/2003", 3, 0);
        Contacto c125 = new Contacto("125", "Laura      ", "Cocos       ", "60474342", "LauraJossete.Martinez@ucr.ac.cr", "05/03/2002", 1, 0);
        Contacto c126 = new Contacto("126", "Kenan      ", "Pacuarito   ", "85986137", "kenan.davis@ucr.ac.cr", "03/10/2002", 1, 1);
        Contacto c127 = new Contacto("127", "Henry      ", "Limon Colina", "61167541", "henryGonzalezHerrera", "21/12/2002", 1, 0);
        Contacto c128 = new Contacto("128", "Oscar      ", "Desamparados", "84046603", "OSCAR.FARGAS@ucr.ac.cr", "01-02-2003", 1, 1);
        Contacto c129 = new Contacto("129", "jean carlos", "batan ", "72918851", "jeankcarrillo9415@gamail.con", "0", 1, 0);
        agregarContacto(c101);
        agregarContacto(c102);
        agregarContacto(c103);
        agregarContacto(c104);
        agregarContacto(c105);
        agregarContacto(c106);
        agregarContacto(c107);
        agregarContacto(c108);
        agregarContacto(c109);
        agregarContacto(c110);
        agregarContacto(c111);
        agregarContacto(c112);
        agregarContacto(c113);
        agregarContacto(c114);
        agregarContacto(c115);
        agregarContacto(c116);
        agregarContacto(c117);
        agregarContacto(c118);
        agregarContacto(c119);
        agregarContacto(c120);
        agregarContacto(c121);
        agregarContacto(c122);
        agregarContacto(c123);
        agregarContacto(c124);
        agregarContacto(c125);
        agregarContacto(c126);
        agregarContacto(c127);
        agregarContacto(c128);
        agregarContacto(c129);
    }
    public void cargarListaChesky() {
        Contacto c100 = new Contacto("101", "Chesky", "Beverly", "83049605", "luis.serrano@ucr.ac.cr", "18/04/1964", 0, 0);
        Contacto c3 = new Contacto("543", "Jose Ocampo     ", "Limon         ", "87071614", "josep.ocampo@hotmail.com", "12/12/99", 0, 1);
        Contacto c4 = new Contacto("297", "Bill Anthony    ", "Juan Pablo    ", "63391792", "billvargas50@gmail.com", "01/03/97", 1, 0);
        Contacto c5 = new Contacto("396", "Eric Lopez      ", "Residencias   ", "87159516", "ericlopezb136@gmail.com", "09/09/99", 0, 0);
        Contacto c6 = new Contacto("108", "Kary            ", "Envaco        ", "61269051", "karyvega3009@hotmail.com", "30/09/99", 1, 1);
        Contacto c7 = new Contacto("876", "Kevin           ", "Guacimo       ", "61850328", "k.brenes10@hotmail.com", "27/03/2000", 0, 1);
        Contacto c8 = new Contacto("686", "Kenery Rodriguez", "Envaco        ", "85909753", "keneryrodriguezespinoza@gmail.com", "31/01/2000", 0, 0);
        Contacto c9 = new Contacto("456", "Kenny Richards  ", "Lomas de chita", "85750685", "koneilrb@hotmail.com        ", "30/07/99", 1, 0);
        Contacto c10 = new Contacto("234", "Juan            ", "Pueblo Nuevo  ", "61533956", "quiorosjuandiego96@gmail.com", "12/12/96", 0, 1);
        Contacto c11 = new Contacto("109", "Jurnieth        ", "Limoncito     ", "86575446", "jurnieth1414@gmail.com      ", "14/12/00", 2, 0);
        Contacto c12 = new Contacto("581", "Lisbeth         ", "Cuba Creek    ", "62001187", "lizzjm06@gmail.com          ", "22/08/99", 2, 1);
        Contacto c13 = new Contacto("601", "Rafael Sequeira ", "Juan pablo II ", "84785949", "Rafaelss427@gmail.com       ", "26/10/00", 1, 0);
        Contacto c14 = new Contacto("013", "Iván            ", "Limón 2000    ", "85601521", "ivan.al@outlook.com         ", "13/12/96", 1, 1);
        Contacto c15 = new Contacto("242", "Jemerson R.     ", "Siglo         ", "63011316", "jemerson0095@gmail.com      ", "16/03/99", 1, 1);
        Contacto c16 = new Contacto("123", "Axel Ulloa      ", "Pueblo Nuevo  ", "84024353", "axelu.h6399@outlook.com     ", "06/03/99", 1, 1);
        Contacto c17 = new Contacto("519", "Sofía Sánchez   ", "Roosevelt     ", "87690975", "so2699@hotmail.com          ", "26/07/99", 1, 1);
        Contacto c18 = new Contacto("351", "Silvia Rivera   ", "Colina        ", "88010421", "sr284201@gmail.com          ", "05/08/98", 1, 1);
        Contacto c19 = new Contacto("733", "HEIDY           ", "Juan Pablo    ", "83986836", "HEIDYMAIRENA513@gmail.com   ", "09/10/95", 1, 1);
        Contacto c20 = new Contacto("645", "ASHLEY          ", "ZENT          ", "72197780", "ashleythompsonfoster@gmail.com", "21/03/00", 1, 1);
        Contacto c21 = new Contacto("958", "Mariana         ", "Villa  mar#2  ", "85517112", "marianarh2307@gmail.com     ", "23/07/98", 1, 1);
        Contacto c22 = new Contacto("745", "Susana         ", "Pacuare  Nuevo  ", "84773066", "gutierrezsusi@gmail.com     ", "13/07/97", 1, 1);
        Contacto c23 = new Contacto("018", "Snyder         ", "Barrio  Quinto  ", "64216892", "aangulo993@gmail.com        ", "11/07/00", 1, 1);
        Contacto c24 = new Contacto("008", "Justin         ", "Cieneguita      ", "84021743", "justinleon1111@gmail.com    ", "11/02/00", 1, 1);
        Contacto c25 = new Contacto("663", "Christopher    ", "Cocos           ", "86674826", "JUAREZ@ucr.ac.cr            ", "20/07/99", 1, 1);
        Contacto c26 = new Contacto("279", "Madeley        ", "Juan  Pablo     ", "71202246", "madegarcia452@gmail.com     ", "30/09/00", 1, 1);
        Contacto c27 = new Contacto("958", "Mariana        ", "Villa  mar#2    ", "85517112", "marianarh2307@gmail.com     ", "23/07/98", 1, 1);
        Contacto c31 = new Contacto("958", "Mariana          ", "Villa  mar#2  ", "85517112", "marianarh2307@gmail.com     ", "23/07/98", 1, 1);
        Contacto c32 = new Contacto("297", "Bill Anthony     ", "Juan Pablo    ", "63391792", "billvargas50@gmail.com      ", "01/03/97", 1, 1);
        Contacto c33 = new Contacto("567", "Pablo Mora       ", "Hone Creek    ", "60985886", "Pablomu1999@gmail.com       ", "01/12/99", 1, 1);
        Contacto c34 = new Contacto("686", "Kenery Rodriguez ", "Colina        ", "85909753", "keneryrodriguez@gmail.com   ", "31/01/00", 1, 1);
        Contacto c35 = new Contacto("721", "Ronny Salgado    ", "B-Line        ", "61627936", "ronnygabriel26@gmail.com    ", "26/01/00", 1, 1);
        agregarContacto(c100);
        agregarContacto(c3);
        agregarContacto(c4);
        agregarContacto(c5);
        agregarContacto(c6);
        agregarContacto(c7);
        agregarContacto(c8);
        agregarContacto(c9);
        agregarContacto(c10);
        agregarContacto(c11);
        agregarContacto(c12);
        agregarContacto(c13);
        agregarContacto(c14);
        agregarContacto(c15);
        agregarContacto(c16);
        agregarContacto(c17);
        agregarContacto(c18);
        agregarContacto(c19);
        agregarContacto(c20);
        agregarContacto(c21);
        agregarContacto(c22);
        agregarContacto(c23);
        agregarContacto(c24);
        agregarContacto(c25);
        agregarContacto(c26);
        agregarContacto(c27);
        agregarContacto(c31);
        agregarContacto(c32);
        agregarContacto(c33);
        agregarContacto(c34);
        agregarContacto(c35);
    }
}

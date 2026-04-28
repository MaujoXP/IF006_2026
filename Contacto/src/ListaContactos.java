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
}

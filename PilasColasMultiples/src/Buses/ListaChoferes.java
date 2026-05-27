package Buses;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
public class ListaChoferes {
    //Atributos

    Chofer listaChoferes[];
    private int cantidadMaxima;
    private int apuntLista;
    // atributos para crear una llave 
    String llave[];
    int apuntLlave;
    int pos[];
    int estado[];

    //Constructores
    public ListaChoferes() {
        cantidadMaxima = 20;
        apuntLista = 0;
        apuntLlave = 0;
        listaChoferes = new Chofer[cantidadMaxima];
        llave = new String[cantidadMaxima];
        pos = new int[cantidadMaxima];
        estado = new int[cantidadMaxima];
    }

    public ListaChoferes(int cantidad) {
        cantidadMaxima = cantidad;
        apuntLista = 0;
        apuntLlave = 0;
        listaChoferes = new Chofer[cantidadMaxima];
        llave = new String[cantidadMaxima];
        pos = new int[cantidadMaxima];
        estado = new int[cantidadMaxima];
    }
    //getApunt

    public int getApuntLista() {
        return this.apuntLista;

    }
    //Agregar a la lista

    public void agregarLista(Chofer nuevo) {
        estado[apuntLista] = 1;
        listaChoferes[apuntLista++] = nuevo;
    }

    public void cargarLlave() {
        for (int i = 0; i < apuntLista; i++) {
            if (estado[i] == 1) {
                llave[apuntLlave++] = listaChoferes[i].getLicencia();
                pos[i] = i;
            }
        }
    }

    public String listarLlave() {
        String resultado = "Contenido de la llave \n";
        for (int i = 0; i < apuntLlave; i++) {
            resultado += "r = " + i + " " + llave[i] + " " + pos[i] + "\n";
        }
        return resultado;
    }

    public void ordenarLlave() {
        String aux;
        int auxPos;
        for (int i = 0; i < apuntLlave - 1; i++) {
            for (int j = i; j < apuntLlave; j++) {
                if (Integer.parseInt(llave[i]) > Integer.parseInt(llave[j])) {
                    aux = llave[j];
                    auxPos = pos[j];
                    llave[j] = llave[i];
                    pos[j] = pos[i];
                    llave[i] = aux;
                    pos[i] = auxPos;

                }
            }
        }
    }
    //public ListaChoferes actualizar(ListaChoferes nueva){
    //public void actualizarLista(ListaChoferes nueva){

    public void actualizarLista() {
        // ListaChoferes temp= new ListaChoferes(nueva.getCantidad());
        ListaChoferes temp = new ListaChoferes(getCantidad());
        int apunt = 0;
        for (int i = 0; i < apuntLista - 1; i++) {
            if (getEstado(i) != 2) {
                temp.listaChoferes[apunt++] = this.listaChoferes[i];
            }
        }
        this.listaChoferes = temp.listaChoferes;
        apuntLista = apunt;
        //return temp;
    }

    // buscar registro 
    public int buscar(String llave) {
        int encontrado = -1;
        for (int i = 0; i < apuntLista - 1; i++) {
            if (this.llave[i].equals(llave)) {
                encontrado = pos[i];
            }
        }
        return encontrado;
    }
    // 

    public String buscarTexto(String llave) {
        int pos = buscar(llave);
        if (pos >= 0) {
            return "Llave " + llave + " encontrada \n Registro de referencia: " + pos + "\n";
        } else {
            return "Llave : " + llave + " NO encontrada\n";
        }
    }

    //devolver registro
    public Chofer devolverRegistro(int pos) {
        return listaChoferes[pos];
    }

    //modificar registro
    public void modificar(Chofer ch, int pos) {
        listaChoferes[pos] = ch;
    }

    //desactivar
    public void desactivar(int pos) {
        estado[pos] = 0;
    }
    //activar

    public void activar(int pos) {
        estado[pos] = 1;
    }

    //eliminar
    public void eliminar(int pos) {
        estado[pos] = 2;
    }

    public int getEstado(int pos) {
        return estado[pos];
    }

    public int getCantidad() {
        return cantidadMaxima;
    }
    
    public int getLicencia(int pos){
        return Integer.parseInt(listaChoferes[pos].getLicencia());
    }

    public String estadoLetra(int pos) {
        String est[] = {"Inactivo", "Activo", "Para Eliminar", "Duplicado", "Incompleto"};
        return est[pos];
    }
    
    public ListaChoferes filtroLista1(int inicio){
    ListaChoferes temp = new ListaChoferes(apuntLista);
        
        for (int i = inicio; i < apuntLista; i++) {
            temp.agregarLista(listaChoferes[i]);
        }
        return temp; 
    }
    
    public ListaChoferes filtroLista1(int inicio, int fin){
    ListaChoferes temp = new ListaChoferes(apuntLista);
        
        for (int i = inicio; i < fin; i++) {
            temp.agregarLista(listaChoferes[i]);
        }
        return temp; 
    }

    //toString
    @Override
    public String toString() {
        String resultado = "Lista de Choferes\n\n";
        for (int i = 0; i < apuntLista; i++) {
            resultado += listaChoferes[i].toString();
        }
        return resultado;
    }// fin toString

    public String toString2() {
        String resultado = "Lista de Choferes\n\n";
        resultado += "Licencia \t\t Apellido1 \t\t Apellido2 \t\t Nombre  \tApodo \n";
        for (int i = 0; i < apuntLista; i++) {
            resultado += listaChoferes[i].toString2();
        }
        return resultado;
    }
    
}

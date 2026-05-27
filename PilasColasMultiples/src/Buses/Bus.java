package Buses;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
/*Clase Bus*/
public class Bus {
    //atributos de la clase

    private int Id;
    private String placa;
    private Asiento asientos[];
    private int capacidad;
    private boolean aire;
    private boolean tv;
    private boolean bebidas;
    private boolean baño;
    private String modelo;
    private String marca;
    private int estado;

    //metodos contructores
    public Bus(String placa, int capacidad) {
        this.Id = 100;
        this.placa = placa;
        this.capacidad = capacidad;
        this.aire = true;
        this.tv = true;
        this.bebidas = false;
        this.baño = false;
        this.modelo = "Sin definir";
        this.marca = "Sin definir";
        this.asientos = new Asiento[capacidad];//define la instancia para bus pero no crea instancia de asiento
        this.estado = 0;
        for (int i = 1; i < capacidad; i++) {
            if (i < 5) {
                this.asientos[i] = new Asiento(i, 2);
            } else {
                this.asientos[i] = new Asiento(i, 1);
            }

            if (i % 2 == 0)//que si es par
            {
                asientos[i].setUbicacion(2);
            } else {
                asientos[i].setUbicacion(1);
            }
        }
    }

    public Bus(String placa, int capacidad, boolean aire, boolean tv, boolean bebidas, boolean baño,
            String modelo, String marca) {
        this.Id = 100;
        this.placa = placa;
        this.capacidad = capacidad;
        this.aire = true;
        this.tv = true;
        this.bebidas = false;
        this.baño = false;
        this.modelo = modelo;
        this.marca = marca;
        this.asientos = new Asiento[capacidad];
        estado = 0;
    }

    public Bus() {
        this.Id = 100;
        this.placa = "Sin";
        this.capacidad = 28;
        this.aire = true;
        this.tv = true;
        this.bebidas = false;
        this.baño = false;
        this.modelo = "Sin definir";
        this.marca = "Sin definir";
        this.estado = 0;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setAire(boolean aire) {
        this.aire = aire;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setBebidas(boolean bebida) {
        this.bebidas = bebida;
    }

    public void setBaño(boolean baño) {
        this.baño = baño;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setEstado(int estadoNuevo) {
        this.estado = estadoNuevo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAsiento(int n, int numero, int tipo, int ubicacion, int estado) {
        this.asientos[n].setNumero(numero);
        this.asientos[n].setTipo(tipo);
        this.asientos[n].setUbicacion(ubicacion);
        this.asientos[n].setEstado(estado);
    }

    public void setAsiento(int n, int tipo) {
        this.asientos[n].setTipo(tipo);
    }

    public void setAsientoUbicacion(int n, int ubicacion) {
        this.asientos[n].setUbicacion(ubicacion);
    }

    public void setAsientoEstado(int n, int estado) {
        this.asientos[n].setEstado(estado);
    }

    public String getPlaca() {
        return this.placa;
    }

    public int getId() {
        return Id;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public int getEstado() {
        return this.estado;
    }

    // public String getAsientoTXt()
    // {
    //   return asientos[pos].toString();
    // }
    public String extras() {
        String sal = "";
        if (aire) {
            sal += "\nA/C: Si ";
        } else {
            sal += "\nA/C: No";
        }

        if (tv) {
            sal += "\nTV: Si ";
        } else {
            sal += "\nTV: No";
        }

        if (bebidas) {
            sal += "\nBebidas: Si ";
        } else {
            sal += "\nBebidas: No";
        }

        if (baño) {
            sal += "\nBaño: Si ";
        } else {
            sal += "\nBaño: No";
        }
        return sal;
    }

    @Override
    public String toString() {
        String sal = "";
        sal += "\n\nBus placa: " + this.placa
                + "\nMarca: " + this.marca
                + "\nModelo: " + this.modelo
                + "\nCapacidad: " + this.capacidad
                + "\nExtras: " + extras() + "\n"
                + "Lista de Asientos\n";
        return sal;
    }

    public String detalle() {
        String sal = "";
        sal += "\n" + this.placa
                + "        " + this.capacidad
                + "      " + this.estado;
        return sal;
    }
    // desplegar todo el bus completo  incluyendo los asientos 

    public String toStringCompleto() {
        String sal = toString();
        sal += listarAsientos();
        return sal;
    }
    // 

    public String listarAsientos() {
        // 0 Indefinido
        // 1 Bueno, // 2 Dañado, // 3 Reservado, // 4 Vendido, // 5 Disponible
        String sal = "Lista de asientos\n";
        for (int i = 1; i < capacidad; i++) {
            sal += asientos[i].detalle();
        }
        return sal;
    }
    // listar asientos filtrados por estado  

    public String listarAsientos(int nEstado) {
        String sal = "Lista de asientos\n";
        for (int i = 1; i < capacidad; i++) {
            if (asientos[i].getEstado() == nEstado) { // filtro por Estado
                sal += asientos[i].detalle();
            }
        }
        return sal;
    }

    public String listarAsientos(int nEstado, int inicio) {
        String sal = "Lista de asientos\n";
        for (int i = inicio; i < capacidad; i++) {
            if (asientos[i].getEstado() == nEstado) { // filtro por Estado
                sal += asientos[i].detalle();
            }
        }
        return sal;
    }
    //

    public String listarAsientos(int nEstado, int inicio, int fin) {
        String sal = "Lista de asientos\n";
        for (int i = inicio; i < fin; i++) {
            if (asientos[i].getEstado() == nEstado) { // filtro por Estado
                sal += asientos[i].detalle();
            }
        }
        return sal;
    }

    public String listarAsientosResevados() {
        return listarAsientos(3);
    }

    public String listarAsientosVendidos() {
        return listarAsientos(4);
    }

    public String listarAsientosDañados() {
        return listarAsientos(2);
    }

    public String listarAsientosDisponibles() {
        return listarAsientos(5);
    }

    public String listarAsientosDisponibles(int inicio) {
        return listarAsientos(5, inicio);
    }

    public String listarAsientosDisponibles(int inicio, int fin) {
        return listarAsientos(5, inicio, fin);
    }
    // metodos para modificar el estado de los Asientos 

    public void modificarAsientosEstado(int nEstado) {
        for (int i = 1; i < capacidad; i++) {
            asientos[i].setEstado(nEstado);
        }
    }
    // con parametros de inicio y final 
    // del inicio al ultimo 

    public void modificarAsientosEstado(int nEstado, int inicio) {
        for (int i = inicio; i < capacidad; i++) {
            asientos[i].setEstado(nEstado);
        }
    }
    // del inicio al fin  

    public void modificarAsientosEstado(int nEstado, int inicio, int fin) {
        for (int i = inicio; i < fin; i++) {
            asientos[i].setEstado(nEstado);
        }
    }

    public void modificarAsientosEstadoDisponibles() {
        modificarAsientosEstado(5);
    }
}

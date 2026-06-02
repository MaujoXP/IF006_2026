package Agencia;

public class Agencia {

    private ListaTramites listaTramites;

    private String nombreAgencia;

    private int nAgencia;

    private int posProximoTramite;

// contructores de agencia  
    public Agencia(int cantidadTramites) {

        this.listaTramites = new ListaTramites(cantidadTramites);

        this.nombreAgencia = "Desconocida";

        this.nAgencia = 77;

        this.posProximoTramite = 0;

    }

    public Agencia(int cantidadTramites, int nAge) {

        this.listaTramites = new ListaTramites(cantidadTramites);

        this.nombreAgencia = "Desconocida";

        this.nAgencia = nAge;

        this.posProximoTramite = 0;

    }

    public Agencia(int cantidadTramites, int nAge, String nombreA) {

        this.listaTramites = new ListaTramites(cantidadTramites);

        this.nombreAgencia = nombreA;

        this.nAgencia = nAge;

        this.posProximoTramite = 0;

    }

    public Agencia(Agencia nueva) {

        this.listaTramites = nueva.listaTramites;

        this.nombreAgencia = nueva.nombreAgencia;

        this.nAgencia = nueva.nAgencia;

        this.posProximoTramite = nueva.posProximoTramite;

    }

    public void agregarTramiteAgencia(Tramite nuevo) {

        this.listaTramites.agregarTramite(nuevo);

    }

    public String toString() {

        String sal = "Lista Tramites de la agencia #";

        sal += this.nAgencia + "  " + this.nombreAgencia;

        sal += "\n";

        sal += "Número\t\tFecha\t\tNúmero\t\tNúmero\t\tMonto\t\tMonto\t\t\tTipo\n";

        sal += "Sucursal\t\t \t\tTramite\t\tCliente\t\tFactura\t\tImpuestos\t\t\n";

        for (int i = 0; i < this.posProximoTramite; i++) {

            sal += this.listaTramites.toString();

        }

        sal += "\n       Fin de la lista        \n";

        return sal;

    }

}

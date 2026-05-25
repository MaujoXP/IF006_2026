package Agencia;

public class Tramite {

    private int nSucursal;

    private String fecha;

    private int nTramite;

    private int nCliente;

    private int montoFactura;

    private int montoImpuestos;

    private int tipo;

    private int vecTramite[];

    private int vecCliente[];

    public Tramite() {

        this.nSucursal = 25;

        this.fecha = "01/07/2010";

        this.nTramite = 8890;

        this.nCliente = 2001003;

        this.montoFactura = 20000;

        this.montoImpuestos = 2000;

        this.tipo = 1;

    }

    public Tramite(int nSucursal, String fecha, int nTramite, int nCliente, int montoFactura, int montoImpuestos, int tipo) {

        this.nSucursal = nSucursal;

        this.fecha = fecha;

        this.nTramite = nTramite;

        this.nCliente = nCliente;

        this.montoFactura = montoFactura;

        this.montoImpuestos = montoImpuestos;

        this.tipo = tipo;

    }

    public Tramite(Tramite nuevo) {

        this.nSucursal = nuevo.nSucursal;

        this.fecha = nuevo.fecha;

        this.nTramite = nuevo.nTramite;

        this.nCliente = nuevo.nCliente;

        this.montoFactura = nuevo.montoFactura;

        this.montoImpuestos = nuevo.montoImpuestos;

        this.tipo = nuevo.tipo;

    }

    // Metodos set  
    public void setNSucursal(int nSuc) {

        this.nSucursal = nSuc;

    }

    public void setFecha(String fecha) {

        this.fecha = fecha;

    }

    @Override
    public String toString() {
        return "Tramite{" + "nSucursal=" + nSucursal + ", fecha=" + fecha + ", nTramite=" + nTramite + ", nCliente=" + nCliente + ", montoFactura=" + montoFactura + ", montoImpuestos=" + montoImpuestos + ", tipo=" + tipo + ", vecTramite=" + vecTramite + ", vecCliente=" + vecCliente + '}';
    }
    
    public String toString2() {
        return "Tramite\n" + "nSucursal: " + nSucursal 
                + "\nFecha: " + fecha 
                + "\nnTramite: " + nTramite 
                + "\nnCliente: " + nCliente 
                + "\nmontoFactura: " + montoFactura 
                + "\nmontoImpuestos: " + montoImpuestos 
                + "\ntipo: " + tipo 
                + "\nvecTramite: " + vecTramite 
                + "\nvecCliente: " + vecCliente;
    }
    
}


import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class Main {
    public static void main(String[] args) {
        CajeroServicio cajeroServicio = new CajeroServicio();
        cajeroServicio.agregarCuenta(new CuentaAhorro("1", 70000));
        cajeroServicio.agregarCuenta(new CuentaCorriente("2", 70000));
        while(true) {
            String opcion = JOptionPane.showInputDialog("1. Consultar Saldo\n2. Depositar\n3.Retirar\n4.Salir");    
            if(opcion == null || opcion.equals("4")) {
                break;
            }
            
            String numeroCuenta = JOptionPane.showInputDialog("Ingrese numero de cuenta");
            CuentaBancaria cuenta = cajeroServicio.obtenerCuenta(numeroCuenta);
            
            if(cuenta == null ) {
                JOptionPane.showMessageDialog(null, "La cuenta no existe");
                continue;
            }
            int monto = 0;
            switch(opcion) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Su saldo es: " + cuenta.consultarSaldo());
                    break;
                case "2":
                    monto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto a depositar: "));
                    if(monto <= 0) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un monto válido");
                        break;
                    }
                    cuenta.depositar(monto);
                    break;
                case "3":
                    monto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto a retirar: "));
                    boolean exito = cuenta.retirar(monto);
                    if(!exito) {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                        break;
                    }
                    break;
            }
            
            
        }
        
    }
}

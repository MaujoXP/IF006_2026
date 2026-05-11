
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class CajeroServicio {
    private ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
    
    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }
    
    public CuentaBancaria obtenerCuenta(String numeroCuenta) {
        for(CuentaBancaria cuenta : cuentas) {
            if(cuenta.getCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }
}

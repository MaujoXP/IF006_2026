package Pila;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Mauricio León B C5G444
 */
public class Pila {

    private int pila[];
    private int apunt;
    private int TOP;

    public Pila() {        
        pila = new int[21];       
        apunt = 0;
        TOP = 20;
    }

    public Pila(int tamaño) {
        pila = new int[tamaño + 1];
        apunt = 0;
        TOP = tamaño;
    }

    public boolean pilaLlena() {
        return apunt == TOP;
    }

    public boolean pilaVacia() {
        return apunt == 0;
    }

    public void ingresarElemento(int y) {
        pila[++apunt] = y;
    }

    public int eliminarElemento() {
        return pila[apunt--];
    }

    public int getTop() {
        return TOP;
    }

    @Override
    public String toString() {
        String salida = "\n";
        for (int i = pila.length - 1; i > 0; i--) {
            salida += i + "[" + pila[i] + "]";
            if (i == pila.length - 1) {
                salida += "<==TOP";
            }
            if (i == apunt) {
                salida += "<==Apunt";
            }
            salida += "\n";
        }
        salida += "Apunt=" + apunt + "\n";
        return salida;
    }//fin del metodo toString

}

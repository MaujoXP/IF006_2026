/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidorsocket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gaby
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(5000);
        Socket cliente = servidor.accept();
        
        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
        salida.println("Hola cliente");
        cliente.close();
        servidor.close();
    }
    
}

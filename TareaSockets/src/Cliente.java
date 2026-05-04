
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Meowricio
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto); BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Servidor: " + mensaje);

                if (mensaje.startsWith("Tu turno")) {
                    System.out.print("Ingresa jugada (fila,col): ");
                    String jugada = teclado.readLine();
                    salida.println(jugada);
                }

                if (mensaje.contains("¿Desean reiniciar")) {
                    System.out.print("Respuesta (R/S): ");
                    String respuesta = teclado.readLine();
                    salida.println(respuesta);
                }
            }

        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class WhatsappNotificacion implements INotificacion{
    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando mensaje de Whatsapp: " + mensaje );
    }
}

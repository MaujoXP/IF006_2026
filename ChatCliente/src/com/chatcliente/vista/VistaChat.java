package com.chatcliente.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaChat extends JFrame {

    private JTextArea areaChat = new JTextArea();
    private JTextField campoTexto = new JTextField();
    private JButton botonEnviar = new JButton("Enviar");

    public VistaChat() {
        setTitle("Chat");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaChat.setEditable(false);

        add(new JScrollPane(areaChat), BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(campoTexto, BorderLayout.CENTER);
        panel.add(botonEnviar, BorderLayout.EAST);

        add(panel, BorderLayout.SOUTH);
    }

    public void agregarListenerEnvio(ActionListener listener) {
        botonEnviar.addActionListener(listener);
        campoTexto.addActionListener(listener);
    }

    public String obtenerMensaje() {
        return campoTexto.getText();
    }

    public void limpiarCampo() {
        campoTexto.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        areaChat.append(mensaje + "\n");
    }
}
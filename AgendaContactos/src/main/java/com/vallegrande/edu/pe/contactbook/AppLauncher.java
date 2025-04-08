package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;

public class AppLauncher {

    public static void main(String[] args) {
        // Se asegura de que la GUI se ejecute en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Se crea una instancia de la ventana principal y se hace visible
                new ContactManagerApp().setVisible(true);
            }
        });
    }
}
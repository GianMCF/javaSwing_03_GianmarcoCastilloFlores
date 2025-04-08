package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ContactInfoPanel extends JPanel {

    private static JLabel lblInfo;
    public ContactInfoPanel() {
        setLayout(new BorderLayout());
        lblInfo = new JLabel("Gestionar contactos ");
        lblInfo.setBorder(new EmptyBorder(15, 0, 15, 0));
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo,BorderLayout.CENTER);
    }
    public static void actualizarInfo(String info) {
        int totalContacts = ContactListPanel.getContactCount();
        lblInfo.setText("Total de Contactos: " + totalContacts);
    }
}

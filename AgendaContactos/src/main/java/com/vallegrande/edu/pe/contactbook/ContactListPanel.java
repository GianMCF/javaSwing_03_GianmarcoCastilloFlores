package com.vallegrande.edu.pe.contactbook;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Clase que representa el panel donde se muestra la lista de contactos
public class ContactListPanel extends JPanel {
    // Modelo de lista que almacena los contactos
    private static DefaultListModel<Contact> contactListModel;
    // Lista visual que muestra los contactos
    private JList<Contact> contactList;
    // Botones para editar y eliminar contactos
    private JButton btnEditContact;
    private JButton btnDeleteContact;


    // Constructor del panel
    public ContactListPanel() {
        // Establece el color de fondo del panel
        setBackground(new Color(255, 251, 202));
        // Usa un layout que organiza los componentes en regiones (norte, sur, centro, etc.)
        setLayout(new BorderLayout());


        // Inicializa el modelo de lista y la lista de contactos
        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        // Permite seleccionar solo un contacto a la vez
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // Inicialización de los botones
        btnEditContact = new JButton("Editar");
        btnDeleteContact = new JButton("Eliminar");


        // Acción del botón "Editar"
        btnEditContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Contact contact = contactListModel.get(selectedIndex);
                    String newName = JOptionPane.showInputDialog(null,
                            "Edite el nombre del contacto",
                            contact.getName());
                    if (newName != null && !newName.isEmpty()) {
                        contact.setName(newName);
                        contactListModel.setElementAt(contact, selectedIndex);
                    }
                }
            }
        });
        // Acción del botón "Eliminar"
        btnDeleteContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = contactList.getSelectedIndex(); // Índice del contacto seleccionado
                // Si hay un contacto seleccionado, se elimina del modelo
                if (selectedIndex != -1) {
                    contactListModel.removeElementAt(selectedIndex);
                }
            }
        });


        // Panel que contiene los botones de acción
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnEditContact);
        buttonPanel.add(btnDeleteContact);

        add(new JScrollPane(contactList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public static void addContact(Contact contact) {
        contactListModel.addElement(contact);
    }

    public static  int getContactCount() {
        return contactListModel.size();
    }

}
package com.vallegrande.edu.pe.contactbook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactInputPanel extends JPanel {

    private JTextField txtName, txtPhoneNumber, txtEmail;
    private JButton btnAddContact;

    private final Pattern namePattern = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    private final Pattern phonePattern = Pattern.compile("^\\d{10}$");
    private final Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public ContactInputPanel() {
        setBackground(new Color(212, 235, 248));
        setLayout(new GridBagLayout());

        JLabel lblName = new JLabel("Nombre: ");
        JLabel lblPhoneNumber = new JLabel("Teléfono: ");
        JLabel lblEmail = new JLabel("Correo electrónico: ");

        txtName = new JTextField(25);
        txtPhoneNumber = new JTextField(25);
        txtEmail = new JTextField(25);

        btnAddContact = new JButton("Agregar Contacto");
        btnAddContact.setPreferredSize(new Dimension(120, 30));
        btnAddContact.setEnabled(false); // Inicialmente deshabilitado

        // Agrega listeners para validar en tiempo real
        addValidationListeners();

        btnAddContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String phoneNumber = txtPhoneNumber.getText().trim();
                String email = txtEmail.getText().trim();

                // Validaciones finales (en caso se necesiten)
                if (!namePattern.matcher(name).matches()) {
                    JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras y espacios", "Error de validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!phonePattern.matcher(phoneNumber).matches()) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener 10 dígitos numéricos", "Error de validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!emailPattern.matcher(email).matches()) {
                    JOptionPane.showMessageDialog(null, "El correo electrónico no tiene un formato válido", "Error de validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ContactListPanel.addContact(new Contact(name, phoneNumber, email));
                txtName.setText("");
                txtPhoneNumber.setText("");
                txtEmail.setText("");

                // Desactiva el botón después de limpiar
                btnAddContact.setEnabled(false);
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        gbc.gridx = 0; gbc.gridy = 0; add(lblName, gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(txtName, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(lblPhoneNumber, gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(txtPhoneNumber, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 2; add(txtEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(btnAddContact, gbc);
    }

    private void addValidationListeners() {
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateFields();
            }
        };

        txtName.getDocument().addDocumentListener(listener);
        txtPhoneNumber.getDocument().addDocumentListener(listener);
        txtEmail.getDocument().addDocumentListener(listener);
    }

    private void validateFields() {
        String name = txtName.getText().trim();
        String phone = txtPhoneNumber.getText().trim();
        String email = txtEmail.getText().trim();

        boolean isValid = namePattern.matcher(name).matches()
                && phonePattern.matcher(phone).matches()
                && emailPattern.matcher(email).matches();

        btnAddContact.setEnabled(isValid);
    }
}
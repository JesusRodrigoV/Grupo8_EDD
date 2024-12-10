package com.farmacia.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.farmacia.dao.UsuarioDAO;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usuarioText;
	private JPasswordField contrasenaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[100px][218px][100px]", "[][30px][5px][30px][5px][30px][5px][30px][5px]"));
		
		JLabel label = new JLabel("");
		getContentPane().add(label, "cell 0 1,grow");
		
		JLabel lblNewLabel = new JLabel("Iniciar Sesion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel, "cell 1 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_1, "cell 0 3,grow");
		
		usuarioText = new JTextField();
		getContentPane().add(usuarioText, "cell 1 3,grow");
		usuarioText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_2, "cell 0 5,grow");
		
		contrasenaField = new JPasswordField();
		getContentPane().add(contrasenaField, "cell 1 5,grow");
		
		JLabel label_1 = new JLabel("");
		getContentPane().add(label_1, "cell 0 7,grow");
		
		JButton iniciarSesion = new JButton("Iniciar Sesion");
		iniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioDAO control = new UsuarioDAO();
				String usuario = usuarioText.getText();
				char[] password = contrasenaField.getPassword(); 
				String contra = new String(password);
				if(control.iniciarSesion(usuario, contra)) {
					dispose();
					InventarioGUI invVentana = new InventarioGUI();
					invVentana.show();
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Resultado", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		getContentPane().add(iniciarSesion, "cell 1 7,grow");
	}
}

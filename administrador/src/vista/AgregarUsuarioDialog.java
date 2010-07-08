package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorGestion;

public class AgregarUsuarioDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton aceptarButton;
	private JButton cancelarButton;

	private JLabel nombreLabel;
	private JTextField nombreTextField;

	private JLabel passwordLabel;
	private JTextField passwordTextField;

	private ControladorGestion controladorGestion;

	public AgregarUsuarioDialog(ControladorGestion controladorGestion) {
		super();
		this.setControladorGestion(controladorGestion);
		initGUI();
	}

	private void initGUI() {
		this.setTitle("Agregar Usuario");
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(340, 140);
		this.setResizable(false);

		Container contentPane = getContentPane();

		nombreLabel = new JLabel();
		nombreLabel.setText("Nombre");
		contentPane.add(nombreLabel);
		nombreLabel.setBounds(10, 10, 100, 20);

		nombreTextField = new JTextField();
		nombreTextField.setText("");
		contentPane.add(nombreTextField);
		nombreTextField.setBounds(110, 10, 200, 20);

		passwordLabel = new JLabel();
		passwordLabel.setText("Contraseña");
		contentPane.add(passwordLabel);
		passwordLabel.setBounds(10, 40, 100, 20);

		passwordTextField = new JPasswordField();
		passwordTextField.setText("");
		contentPane.add(passwordTextField);
		passwordTextField.setBounds(110, 40, 200, 20);

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = nombreTextField.getText();
				String password = passwordTextField.getText();
				try {
					getControladorGestion().agregarUsuario(usuario, password);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar el Usuario.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarUsuarioDialog.this.setVisible(false);
			}
		});

		cancelarButton = new JButton();
		cancelarButton.setText("Cancelar");
		contentPane.add(cancelarButton);
		cancelarButton.setBounds(230, 70, 80, 20);
		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarUsuarioDialog.this.setVisible(false);
			}
		});

		this.setModal(true);
		this.setVisible(true);
	}

	public ControladorGestion getControladorGestion() {
		return controladorGestion;
	}

	public void setControladorGestion(ControladorGestion controladorGestion) {
		this.controladorGestion = controladorGestion;
	}
}

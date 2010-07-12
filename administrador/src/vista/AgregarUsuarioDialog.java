package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorGestion;

public class AgregarUsuarioDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private JLabel nombreLabel;
	private JTextField nombreTextField;

	private JLabel passwordLabel;
	private JTextField passwordTextField;

	public AgregarUsuarioDialog(ControladorGestion controladorGestion) {
		super(controladorGestion);
		super.init();
	}

	protected void cargarDatos() {
	}

	@Override
	protected String getTitulo() {
		return "Agregar Usuario";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
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
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = nombreTextField.getText();
				String password = passwordTextField.getText();
				try {
					controladorGestion.agregarUsuario(usuario, password);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar el Usuario.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarUsuarioDialog.this.dispose();
			}
		};
	}
}

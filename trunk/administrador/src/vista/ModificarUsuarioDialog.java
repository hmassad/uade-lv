package vista;
//Dialog
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorGestion;

public class ModificarUsuarioDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private int idUsuario;
	private String nombreUsuario;

	private JLabel nombreLabel;
	private JLabel viejoNombreLabel;
	private JLabel nuevoNombreLabel;
	private JTextField nuevoNombreTextField;

	public ModificarUsuarioDialog(ControladorGestion controladorGestion, int idUsuario, String nombreUsuario) {
		super(controladorGestion);
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		super.init();
	}

	@Override
	protected String getTitulo() {
		return "Modificar Usuario";
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

		viejoNombreLabel = new JLabel();
		viejoNombreLabel.setText(nombreUsuario);
		contentPane.add(viejoNombreLabel);
		viejoNombreLabel.setBounds(110, 10, 200, 20);

		nuevoNombreLabel = new JLabel();
		nuevoNombreLabel.setText("Nuevo Nombre");
		contentPane.add(nuevoNombreLabel);
		nuevoNombreLabel.setBounds(10, 40, 100, 20);

		nuevoNombreTextField = new JTextField();
		nuevoNombreTextField.setText("");
		contentPane.add(nuevoNombreTextField);
		nuevoNombreTextField.setBounds(110, 40, 200, 20);
	}

	@Override
	protected void cargarDatos() throws Exception {
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controladorGestion.modificarUsuario(idUsuario, nuevoNombreTextField.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al modificar el Usuario.\n\"%s\"", e.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				ModificarUsuarioDialog.this.dispose();
			}
		};
	}

}

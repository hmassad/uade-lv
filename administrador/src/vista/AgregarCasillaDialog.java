package vista;
//Dialog
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import beans.UsuarioVO;
import controlador.ControladorGestion;

public class AgregarCasillaDialog extends BaseDialog {

	private static final long serialVersionUID = 1L;

	private JLabel direccionLabel;
	private JTextField direccionTextField;

	private JLabel usuarioLabel;
	private JComboBox usuarioComboBox;

	public AgregarCasillaDialog(ControladorGestion controladorGestion) {
		super(controladorGestion);
		super.init();
	}

	@Override
	protected String getTitulo() {
		return "Agregar Casilla";
	}

	@Override
	protected Dimension getTamaño() {
		return new Dimension(340, 140);
	}

	@Override
	protected void inicializarVentanaEspecializada() {
		Container contentPane = getContentPane();

		direccionLabel = new JLabel();
		direccionLabel.setText("Dirección");
		contentPane.add(direccionLabel);
		direccionLabel.setBounds(10, 10, 100, 20);

		direccionTextField = new JTextField();
		direccionTextField.setText("");
		contentPane.add(direccionTextField);
		direccionTextField.setBounds(110, 10, 200, 20);

		usuarioLabel = new JLabel();
		usuarioLabel.setText("Usuario");
		contentPane.add(usuarioLabel);
		usuarioLabel.setBounds(10, 40, 100, 20);

		usuarioComboBox = new JComboBox();
		contentPane.add(usuarioComboBox);
		usuarioComboBox.setBounds(110, 40, 200, 20);
	}

	@Override
	protected void cargarDatos() throws Exception {
		Collection<UsuarioVO> usuarios;
		try {
			usuarios = controladorGestion.obtenerUsuarios();
		} catch (Exception e1) {
			throw new Exception(String.format("Ocurrió un error al listar Usuarios.\n\"%s\"", e1.getMessage()));
		}
		if (usuarios.size() == 0) {
			throw new Exception("No se registran Usuarios.");
		}
		for (UsuarioVO u : usuarios) {
			usuarioComboBox.addItem(u);
		}
	}

	@Override
	protected ActionListener getAceptarActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idUsuario = ((UsuarioVO) usuarioComboBox.getSelectedItem()).getId();
				String direccion = direccionTextField.getText();
				try {
					controladorGestion.agregarCasilla(idUsuario, direccion);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al agregar la Casilla.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				AgregarCasillaDialog.this.dispose();
			}
		};
	}
}

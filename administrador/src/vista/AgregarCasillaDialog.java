package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import beans.UsuarioVO;
import controlador.ControladorGestion;

public class AgregarCasillaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private ControladorGestion controladorGestion;

	private JLabel direccionLabel;
	private JTextField direccionTextField;

	private JLabel usuarioLabel;
	private JComboBox usuarioComboBox;

	private JButton aceptarButton;
	private JButton cancelarButton;

	public AgregarCasillaDialog(ControladorGestion controladorGestion) {
		super();
		this.controladorGestion = controladorGestion;
		initGUI();
	}

	private void cargarDatos() {
		try {
			for (UsuarioVO u : controladorGestion.obtenerUsuarios()) {
				usuarioComboBox.addItem(u);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, String.format("Ocurrió un error al listar Usuarios.\n\"%s\"", e1.getMessage()), "Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
			dispose();
		}
	}

	private void initGUI() {
		this.setTitle("Agregar Casilla");
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(340, 140);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				AgregarCasillaDialog.this.dispose();
			}
		});

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
		cargarDatos();

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(new ActionListener() {

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
		});

		cancelarButton = new JButton();
		cancelarButton.setText("Cancelar");
		contentPane.add(cancelarButton);
		cancelarButton.setBounds(230, 70, 80, 20);
		cancelarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarCasillaDialog.this.setVisible(false);
			}
		});

		this.setModal(true);
		this.setVisible(true);
	}
}

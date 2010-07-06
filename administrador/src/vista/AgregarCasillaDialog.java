package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import beans.UsuarioVO;
import controlador.ControladorGestion;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AgregarCasillaDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton aceptarButton;
	private JButton cancelarButton;

	private JLabel direccionLabel;
	private JTextArea direccionTextArea;

	private ControladorGestion controladorGestion;

	private JLabel usuarioLabel;

	private JComboBox usuarioComboBox;

	public AgregarCasillaDialog(ControladorGestion controladorGestion) {
		super();
		this.setControladorGestion(controladorGestion);
		initGUI();
	}

	private void initGUI() {
		this.setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(340, 140);
		this.setResizable(false);

		Container contentPane = getContentPane();

		direccionLabel = new JLabel();
		direccionLabel.setText("Direccion");
		contentPane.add(direccionLabel);
		direccionLabel.setBounds(10, 10, 100, 20);

		direccionTextArea = new JTextArea();
		direccionTextArea.setText("");
		contentPane.add(direccionTextArea);
		direccionTextArea.setBounds(110, 10, 200, 20);

		usuarioLabel = new JLabel();
		usuarioLabel.setText("Usuario");
		contentPane.add(usuarioLabel);
		usuarioLabel.setBounds(10, 40, 100, 20);

		usuarioComboBox = new JComboBox();
		for (UsuarioVO u : controladorGestion.obtenerUsuarios()) {
			usuarioComboBox.addItem(u);
		}
		contentPane.add(usuarioComboBox);
		usuarioComboBox.setBounds(110, 40, 200, 20);

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idUsuario = ((UsuarioVO) usuarioComboBox.getSelectedItem()).getId();
				String direccion = direccionTextArea.getText();
				getControladorGestion().agregarCasilla(idUsuario, direccion);
				AgregarCasillaDialog.this.setVisible(false);
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

	public ControladorGestion getControladorGestion() {
		return controladorGestion;
	}

	public void setControladorGestion(ControladorGestion controladorGestion) {
		this.controladorGestion = controladorGestion;
	}
}

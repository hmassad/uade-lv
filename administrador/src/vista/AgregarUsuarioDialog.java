package vista;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

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
public class AgregarUsuarioDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton aceptarButton;
	private JButton cancelarButton;

	private JLabel nombreLabel;
	private JTextArea nombreTextArea;

	private JLabel passwordLabel;
	private JTextArea passwordTextArea;

	private ControladorGestion controladorGestion;

	public AgregarUsuarioDialog(ControladorGestion controladorGestion) {
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

		nombreLabel = new JLabel();
		nombreLabel.setText("Nombre");
		contentPane.add(nombreLabel);
		nombreLabel.setBounds(10, 10, 100, 20);

		nombreTextArea = new JTextArea();
		nombreTextArea.setText("");
		contentPane.add(nombreTextArea);
		nombreTextArea.setBounds(110, 10, 200, 20);

		passwordLabel = new JLabel();
		passwordLabel.setText("Contraseņa");
		contentPane.add(passwordLabel);
		passwordLabel.setBounds(10, 40, 100, 20);

		passwordTextArea = new JTextArea();
		passwordTextArea.setText("");
		contentPane.add(passwordTextArea);
		passwordTextArea.setBounds(110, 40, 200, 20);

		aceptarButton = new JButton();
		aceptarButton.setText("Aceptar");
		contentPane.add(aceptarButton);
		aceptarButton.setBounds(110, 70, 80, 20);
		aceptarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = nombreTextArea.getText();
				String password = passwordTextArea.getText();
				getControladorGestion().agregarUsuario(usuario, password);
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
